package com.lagou.edu.factory;

import com.lagou.edu.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/17 6:28 PM
 */
public class TransactionProxyFactory<T> implements InvocationHandler {

    private final T t;

    public TransactionProxyFactory(T t) {
        this.t = t;
    }

    public <E extends T> T getTarget() {
        return (E) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            TransactionManager.beginTransaction();

            Object result = method.invoke(t, args);

            TransactionManager.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionManager.rollback();
            throw e;
        }
    }
}
