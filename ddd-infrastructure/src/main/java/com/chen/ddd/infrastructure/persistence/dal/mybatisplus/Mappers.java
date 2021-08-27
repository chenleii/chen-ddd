package com.chen.ddd.infrastructure.persistence.dal.mybatisplus;

import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.sqlinjector.InsertIgnore;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author chen
 * @since 2020/9/4 0:22.
 */
public class Mappers {

    /**
     * 获取mapper代理类型的mapper类型
     *
     * @param proxyClass mapper代理类
     * @return mapper类型
     */
    public static Class<?> getProxyMapperClass(Class<?> proxyClass) {
        return proxyClass.getInterfaces()[0];
    }

    /**
     * 获取mapper类型的实体类型
     *
     * @param mapperClass mapper类型
     * @return 实体类型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getMapperModelClass(Class<?> mapperClass) {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(mapperClass, Mapper.class, 0);
    }

    /**
     * 计算更新数量
     *
     * @param batchResultList 批量更新结果
     * @return 受影响行数
     */
    public static int calculateUpdateCounts(List<BatchResult> batchResultList) {
        if (CollectionUtils.isNotEmpty(batchResultList)) {
            return batchResultList.stream()
                    .filter(Objects::nonNull)
                    .map(BatchResult::getUpdateCounts)
                    .filter(Objects::nonNull)
                    .flatMapToInt(Arrays::stream)
                    .sum();
        }
        return 0;
    }

    /**
     * 批量插入
     */
    public static <T> int insertBatchReturnCount(Class<?> mapperClass, Class<T> entryClass, Collection<T> entityList, int batchSize) {
        return insertBatchReturnCount(SqlHelper.getSqlStatement(mapperClass, SqlMethod.INSERT_ONE),
                mapperClass, entryClass, entityList, batchSize);
    }
    /**
     * 批量插入
     */
    public static <T> int insertIgnoreBatchReturnCount(Class<?> mapperClass, Class<T> entryClass, Collection<T> entityList, int batchSize) {
        return insertBatchReturnCount(mapperClass.getName() + "." + InsertIgnore.METHOD_NAME,
                mapperClass, entryClass, entityList, batchSize);
    }

    /**
     * 批量插入
     */
    public static <T> int insertBatchReturnCount(String sqlStatement,Class<?> mapperClass, Class<T> entryClass, Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return 0;
        }

        int updateCounts = 0;

        final SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(entryClass);
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        ) {
            final int size = entityList.size();
            int i = 1;
            for (T entry : entityList) {
                sqlSession.insert(sqlStatement, entry);
                if ((i % batchSize == 0) || i == size) {
                    List<BatchResult> batchResultList = sqlSession.flushStatements();
                    updateCounts += Mappers.calculateUpdateCounts(batchResultList);
                }
                i++;
            }
        }
        return updateCounts;
    }

    /**
     * 批量插入
     */
    public static <T> int updateBatchByIdReturnCount(Class<?> mapperClass, Class<T> entryClass, Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return 0;
        }

        int updateCounts = 0;

        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.UPDATE_BY_ID);
        final SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(entryClass);
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        ) {
            final int size = entityList.size();
            int i = 1;
            for (T entry : entityList) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, entry);
                sqlSession.update(sqlStatement, param);
                if ((i % batchSize == 0) || i == size) {
                    List<BatchResult> batchResultList = sqlSession.flushStatements();
                    updateCounts += Mappers.calculateUpdateCounts(batchResultList);
                }
                i++;
            }
        }
        return updateCounts;
    }


}
