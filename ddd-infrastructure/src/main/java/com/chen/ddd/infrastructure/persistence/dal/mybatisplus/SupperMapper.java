package com.chen.ddd.infrastructure.persistence.dal.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.interfaces.Join;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * mapper超类
 * <p>
 * 增加获取第一条记录方法
 * 增加自定义分页方法（支持实体类字段名转数据库字段名）
 * 增加排它锁、共享锁查询方法
 * 增加批量新增、批量删除方法
 * <p>
 * 使用批量方法注意：
 * 数据库实体类的泛型参数为接口的第一个泛型
 *
 * @author chen
 * @since 2018/11/3 22:10.
 */
public interface SupperMapper<T> extends BaseMapper<T> {

    /**
     * 共享锁
     */
    String LAST_SQL_LOCK_IN_SHARE_MODE = "LOCK IN SHARE MODE";

    /**
     * 排它锁
     */
    String LAST_SQL_FOR_UPDATE = "FOR UPDATE";

    /**
     * 默认批量大小
     */
    int DEFAULT_BATCH_SIZE = 1000;

    /**
     * 查询是否存在记录
     *
     * @param queryWrapper 条件
     * @return 是/否
     */
    default boolean exist(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return selectCount(queryWrapper) > 0;
    }

    /**
     * 查询是否存在记录
     *
     * @param id id
     * @return 是/否
     */
    default boolean existById(Serializable id) {
        return selectById(id) != null;
    }

    /**
     * 查询所有记录
     *
     * @return 所有记录
     */
    default List<T> selectAll() {
        return selectList(null);
    }

    /**
     * 查删除所有记录
     *
     * @return 所有记录
     */
    default int deleteAll() {
        return delete(null);
    }


    /**
     * 查询第一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 第一条记录
     */
    default T selectFirstOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        IPage<T> iPage = selectPage(new Page<>(1, 1, false), queryWrapper);
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            return iPage.getRecords().get(0);
        }
        return null;
    }

    /**
     * 查询一条记录并加排它锁
     * <p>
     * lastSql会被覆盖
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 记录
     */
    default T selectOneForUpdate(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        if (queryWrapper == null) {
            queryWrapper = Wrappers.<T>lambdaQuery();
        }
        if (!(queryWrapper instanceof Join)) {
            throw new MybatisPlusException(" not support.");
        }
        ((Join<?>) queryWrapper).last(LAST_SQL_FOR_UPDATE);
        return selectOne(queryWrapper);
    }


    /**
     * 查询记录列表并加排它锁
     * <p>
     * lastSql会被覆盖
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 记录列表
     */
    default List<T> selectListForUpdate(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        if (queryWrapper == null) {
            queryWrapper = Wrappers.<T>lambdaQuery();
        }
        if (!(queryWrapper instanceof Join)) {
            throw new MybatisPlusException(" not support.");
        }
        ((Join<?>) queryWrapper).last(LAST_SQL_FOR_UPDATE);
        return selectList(queryWrapper);
    }

    /**
     * 查询一条记录并加共享锁
     * <p>
     * lastSql会被覆盖
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 记录
     */
    default T selectOneLockInShareMode(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        if (queryWrapper == null) {
            queryWrapper = Wrappers.<T>lambdaQuery();
        }
        if (!(queryWrapper instanceof Join)) {
            throw new MybatisPlusException(" not support.");
        }
        ((Join<?>) queryWrapper).last(LAST_SQL_LOCK_IN_SHARE_MODE);
        return selectOne(queryWrapper);
    }

    /**
     * 查询记录列表并加共享锁
     * <p>
     * lastSql会被覆盖
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 记录列表
     */
    default List<T> selectListLockInShareMode(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        if (queryWrapper == null) {
            queryWrapper = Wrappers.<T>lambdaQuery();
        }
        if (!(queryWrapper instanceof Join)) {
            throw new MybatisPlusException(" not support.");
        }
        ((Join<?>) queryWrapper).last(LAST_SQL_LOCK_IN_SHARE_MODE);
        return selectList(queryWrapper);
    }


    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @param batchSize  插入批次数量
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    default int insertBatch(List<T> entityList, int batchSize) {
        Class<?> mapperClass = Mappers.getProxyMapperClass(this.getClass());
        Class<T> entryClass = Mappers.getMapperModelClass(mapperClass);

        return Mappers.insertBatchReturnCount(mapperClass, entryClass, entityList, batchSize);
    }

    /**
     * 批量插入
     * <p>
     * 冲突忽略
     *
     * @param entityList 实体列表
     * @param batchSize  插入批次数量
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    default int insertIgnoreBatch(List<T> entityList, int batchSize) {
        Class<?> mapperClass = Mappers.getProxyMapperClass(this.getClass());
        Class<T> entryClass = Mappers.getMapperModelClass(mapperClass);

        return Mappers.insertIgnoreBatchReturnCount(mapperClass, entryClass, entityList, batchSize);
    }

    /**
     * 批量插入
     * <p>
     * 冲突更新
     *
     * @param entityList 实体列表
     * @param batchSize  插入批次数量
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    default int insertOnDuplicateKeyUpdateBatch(List<T> entityList, int batchSize) {
        Class<?> mapperClass = Mappers.getProxyMapperClass(this.getClass());
        Class<T> entryClass = Mappers.getMapperModelClass(mapperClass);

        return Mappers.insertOnDuplicateKeyUpdateBatchReturnCount(mapperClass, entryClass, entityList, batchSize);
    }

    /**
     * 批量修改
     *
     * @param entityList 实体列表
     * @param batchSize  修改批次数量
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    default int updateBatchById(List<T> entityList, int batchSize) {
        Class<?> mapperClass = Mappers.getProxyMapperClass(this.getClass());
        Class<T> entryClass = Mappers.getMapperModelClass(mapperClass);

        return Mappers.updateBatchByIdReturnCount(mapperClass, entryClass, entityList, batchSize);
    }


    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return 受影响行数
     */
    default int insertBatch(List<T> entityList) {
        return insertBatch(entityList, DEFAULT_BATCH_SIZE);
    }
    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return 受影响行数
     */
    default int insertIgnoreBatch(List<T> entityList) {
        return insertIgnoreBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    default int insertOnDuplicateKeyUpdateBatch(List<T> entityList) {
        return insertOnDuplicateKeyUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量修改
     *
     * @param entityList 实体列表
     * @return 受影响行数
     */
    default int updateBatchById(List<T> entityList) {
        return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
    }


    /**
     * 插入一条记录
     * <p>
     * 冲突忽略
     *
     * @param entity entity
     * @return 行数
     */
    int insertIgnore(T entity);

    /**
     * 插入一条记录
     * <p>
     * 冲突更新
     *
     * @param entity entity
     * @return 行数
     */
    int insertOnDuplicateKeyUpdate(T entity);
}
