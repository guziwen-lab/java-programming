package com.litianyi.sql.session;

import com.litianyi.pojo.Configuration;
import com.litianyi.pojo.MappedStatement;
import com.litianyi.sql.session.pojo.BoundSql;
import com.litianyi.utils.GenericTokenParser;
import com.litianyi.utils.ParameterMapping;
import com.litianyi.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 3:59 PM
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception {
        // 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        // 获取sql select * from user where id = #{id} and username = #{username}
        String sql = mappedStatement.getSql();
        // 转换sql为 select * from user where id = ? and username = ?，转换的过程中，还需要对#{}里面的值进行解析存储
        BoundSql boundSql = getBoundSql(sql);

        // 获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 设置参数
        if (mappedStatement.getParameterType() != null) {
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            Class<?> parameterClass = getClassType(mappedStatement.getParameterType());
            for (int i = 0; i < parameterMappings.size(); i++) {
                String content = parameterMappings.get(i).getContent();

                Field field = parameterClass.getDeclaredField(content);
                field.setAccessible(true);
                Object filedValue = field.get(params[0]);

                preparedStatement.setObject(i + 1, filedValue);
            }
        }

        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        // 封装返回结果集
        List<E> list = new ArrayList<>();
        Class<E> resultClass = (Class<E>) getClassType(mappedStatement.getResultType());
        while (resultSet.next()) {
            E instance = resultClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);
                // 根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(instance, value);
            }
            list.add(instance);
        }

        return list;
    }

    @Override
    public Integer update(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception {

        Connection connection = configuration.getDataSource().getConnection();

        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        Class<?> parameterClass = getClassType(mappedStatement.getParameterType());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            String content = parameterMappings.get(i).getContent();

            Field field = parameterClass.getDeclaredField(content);
            field.setAccessible(true);
            Object filedValue = field.get(params[0]);

            preparedStatement.setObject(i + 1, filedValue);
        }
        // 5. 执行sql
        return preparedStatement.executeUpdate();
    }

    @Override
    public Integer delete(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();

        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        Class<?> parameterClass = getClassType(mappedStatement.getParameterType());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterClass == Integer.class) {
            preparedStatement.setObject(1, params[0]);
        } else {
            for (int i = 0; i < parameterMappings.size(); i++) {
                String content = parameterMappings.get(i).getContent();

                Field field = parameterClass.getDeclaredField(content);
                field.setAccessible(true);
                Object filedValue = field.get(params[0]);

                preparedStatement.setObject(i + 1, filedValue);
            }
        }
        // 5. 执行sql
        return preparedStatement.executeUpdate();
    }

    /**
     * 完成对#{}的解析
     * 1. 将#{}使用?代替
     * 2. 解析出#{}里面的值进行存储
     *
     * @param sql select * from user where id = #{id} and username = #{username}
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // select * from user where id = ? and username = ?
        String parseSql = genericTokenParser.parse(sql);
        // #{}里面解析出来的参数名
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        return new BoundSql(parseSql, parameterMappings);
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            return Class.forName(parameterType);
        }
        return null;
    }
}
