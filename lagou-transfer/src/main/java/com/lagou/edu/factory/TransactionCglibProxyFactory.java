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
public class TransactionCglibProxyFactory<T> implements MethodInterceptor {

    private final Class<? extends T> clazz;

    public TransactionCglibProxyFactory(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    public <E extends T> T getTarget() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (E) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            TransactionManager.beginTransaction();

            Object result = proxy.invokeSuper(obj, args);

            TransactionManager.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionManager.rollback();
            throw e;
        }
    }
}
