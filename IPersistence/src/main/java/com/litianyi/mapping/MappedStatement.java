package com.litianyi.mapping;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 4:32 PM
 */
public class MappedStatement {
    /**
     * id标识
     */
    private String id;
    /**
     * 返回值类型
     */
    private String resultType;
    /**
     * 参数类型
     */
    private String parameterType;
    /**
     * sql语句
     */
    private String sql;

    public MappedStatement() {
    }

    public MappedStatement(String id, String resultType, String parameterType, String sql) {
        this.id = id;
        this.resultType = resultType;
        this.parameterType = parameterType;
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
