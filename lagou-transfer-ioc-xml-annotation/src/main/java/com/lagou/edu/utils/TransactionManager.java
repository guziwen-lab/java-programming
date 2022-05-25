package com.lagou.edu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/5/16 4:46 PM
 */
@Component
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    public void beginTransaction() {
        try {
            connectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commit() {
        try {
            connectionUtils.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback() {
        try {
            connectionUtils.getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
