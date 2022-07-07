package com.lagou.edu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/16 4:06 PM
 */
@Component
public class ConnectionUtils {

    @Autowired
    private DataSource dataSource;

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 从当前线程获取连接
     */
    public Connection getConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
