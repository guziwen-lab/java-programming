package com.litianyi.sql.session;

import com.litianyi.pojo.Configuration;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 10:37 AM
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
