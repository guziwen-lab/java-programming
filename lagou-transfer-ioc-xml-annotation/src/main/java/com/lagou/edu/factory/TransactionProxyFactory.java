package com.lagou.edu.factory;

import com.lagou.edu.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/17 6:28 PM
 */
@Component
public class TransactionProxyFactory implements InvocationHandler {

    @Autowired
    private TransactionManager transactionManager;

    private Object obj;

    public <E> E getTarget(Object obj) {
        this.obj = obj;
        return (E) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            transactionManager.beginTransaction();

            Object result = method.invoke(obj, args);

            transactionManager.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw e;
        }
    }
}
