package com.lagou.edu.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/16 4:06 PM
 */
public class ConnectionUtils {

    private final static ConnectionUtils connectionUtils = new ConnectionUtils();

    private ConnectionUtils() {
    }

    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 从当前线程获取连接
     */
    public static Connection getConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                connection = DruidUtils.getInstance().getConnection();
                connectionThreadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
