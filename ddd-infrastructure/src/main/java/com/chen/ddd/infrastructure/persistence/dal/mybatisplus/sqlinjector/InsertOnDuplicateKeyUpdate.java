package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.sqlinjector;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/3 22:14
 */
public class InsertOnDuplicateKeyUpdate extends Update {

    public static final String METHOD_NAME = "insertOnDuplicateKeyUpdate";
    public static final String SQL = "<script>\nINSERT INTO %s %s VALUES %s ON DUPLICATE KEY UPDATE %s \n</script>";

    public InsertOnDuplicateKeyUpdate() {
        super(METHOD_NAME);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(null),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /** 自增主键 */
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(METHOD_NAME, tableInfo, builderAssistant);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }

        String onDuplicateKeyUpdateScript = sqlOnDuplicateKeyUpdate(true, tableInfo);
        String sql = String.format(SQL, tableInfo.getTableName(), columnScript, valuesScript, onDuplicateKeyUpdateScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, METHOD_NAME, sqlSource, keyGenerator, keyProperty, keyColumn);
    }


    /**
     * SQL ON DUPLICATE KEY UPDATE 语句
     *
     * @param logic 是否逻辑删除注入器
     * @param table 表信息
     * @return sql
     */
    protected String sqlOnDuplicateKeyUpdate(boolean logic, TableInfo table) {
        String sqlScript = getAllSqlSet(table, logic, "");
        sqlScript = SqlScriptUtils.convertTrim(sqlScript, "", "", "", ",");
        return sqlScript;
    }


    public String getAllSqlSet(TableInfo tableInfo, boolean ignoreLogicDelFiled, final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return tableInfo.getFieldList().stream().filter((i) -> {
                    if (!ignoreLogicDelFiled) {
                        return true;
                    } else {
                        return !tableInfo.isWithLogicDelete() || !i.isLogicDelete();
                    }
                }).map((i) -> getSqlSet(i, false, newPrefix))
                .filter(Objects::nonNull).collect(Collectors.joining("\n", "\n", "\n"));
    }

    public String getSqlSet(TableFieldInfo tableFieldInfo, final boolean ignoreIf, final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        String sqlSet = tableFieldInfo.getColumn() + "=";
        if (StringUtils.isNotBlank(tableFieldInfo.getUpdate())) {
            sqlSet = sqlSet + String.format(tableFieldInfo.getUpdate(), tableFieldInfo.getColumn());
        } else {
            sqlSet = sqlSet + "VALUES(" + tableFieldInfo.getColumn() + ")";
        }

        sqlSet = sqlSet + ",";
        if (ignoreIf) {
            return sqlSet;
        } else {
            if (tableFieldInfo.isWithUpdateFill()) {
                return sqlSet;
            } else {
                try {
                    String convertIfProperty = (String) MethodUtils.invokeMethod(tableFieldInfo, true, "convertIfProperty", newPrefix, tableFieldInfo.getProperty());
                    return (String) MethodUtils.invokeMethod(tableFieldInfo, true, "convertIf", sqlSet, convertIfProperty, tableFieldInfo.getUpdateStrategy());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
