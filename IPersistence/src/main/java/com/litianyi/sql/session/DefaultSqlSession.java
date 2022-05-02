package com.litianyi.sql.session;

import com.litianyi.pojo.Configuration;
import com.litianyi.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 10:44 AM
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        if (mappedStatement == null) {
            throw new RuntimeException("没有该sql");
        }
        try {
            return executor.query(configuration, mappedStatement, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) {
        List<T> o = this.selectList(statementId, params);
        if (o != null && o.size() == 1) {
            return o.get(0);
        }
        throw new RuntimeException("查询结果为空或返回结果多余一个");
    }

    public Integer insert(String statement, Object... params) {
        return update(statement, params);
    }

    public Integer update(String statement, Object... params) {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statement);
        if (mappedStatement == null) {
            throw new RuntimeException("没有该sql");
        }
        try {
            return executor.update(configuration, mappedStatement, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer delete(String statement, Object... params) {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statement);
        if (mappedStatement == null) {
            throw new RuntimeException("没有该sql");
        }
        try {
            return executor.delete(configuration, mappedStatement, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public <T> T getMapper(Class<? extends T> mapperInterface) {
        // JDK 动态代理，为dao接口生成代理对象，并返回
        Object proxyInstance = Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface}, new InvocationHandler() {
                    /**
                     * 执行 JDBC 代码，根据不同情况调用 selectList 或 selectOne
                     *
                     * @param proxy  代理对象的引用
                     * @param method 代理对象调用的方法
                     * @param args   代理对象调用方法时，传递的实际参数
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //namespace.id = 接口全限定名.方法名
                        String className = method.getDeclaringClass().getName();
                        String methodName = method.getName();
                        String statementId = className + "." + methodName;

                        // 获取被调用方法的返回值类型
                        Type genericReturnType = method.getGenericReturnType();
                        // 判断是否进行了 泛型类型参数化
                        if (genericReturnType instanceof ParameterizedType) {
                            return selectList(statementId, args);
                        }

                        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
                        String sql = mappedStatement.getSql();
                        if (sql.startsWith("update") || sql.startsWith("insert")) {
                            return update(statementId, args);
                        }
                        if (sql.startsWith("delete")) {
                            return delete(statementId, args);
                        }

                        return selectOne(statementId, args);
                    }
                });
        return (T) proxyInstance;
    }
}
