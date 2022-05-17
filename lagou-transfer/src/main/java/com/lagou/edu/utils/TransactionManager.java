package com.lagou.edu.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/5/16 4:46 PM
 */
public class TransactionManager {

    private TransactionManager() {
    }

    private final static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance() {
        return transactionManager;
    }

    public static void beginTransaction() {
        try {
            ConnectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit() {
        try {
            ConnectionUtils.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback() {
        try {
            ConnectionUtils.getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
