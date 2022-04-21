package com.litianyi.sql.session.pojo;

import com.litianyi.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 4:22 PM
 */
public class BoundSql {
    /**
     * 解析后的sql
     */
    private String sqlText;
    /**
     * #{}里面解析出来的参数名
     */
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql() {
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappings) {
        this.sqlText = sqlText;
        this.parameterMappings = parameterMappings;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
