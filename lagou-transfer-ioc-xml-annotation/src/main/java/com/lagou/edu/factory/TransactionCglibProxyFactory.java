package com.lagou.edu.factory;

import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/17 6:28 PM
 */
public class TransactionCglibProxyFactory implements MethodInterceptor {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public <E> E getTarget(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (E) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            transactionManager.beginTransaction();

            Object result = proxy.invokeSuper(obj, args);

            transactionManager.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw e;
        }
    }
}
