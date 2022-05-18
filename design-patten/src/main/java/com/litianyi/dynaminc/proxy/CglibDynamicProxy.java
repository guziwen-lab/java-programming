package com.litianyi.dynaminc.proxy;

import com.litianyi.dynaminc.proxy.pojo.Bob;
import com.litianyi.dynaminc.proxy.pojo.Person;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/17 3:48 PM
 */
public class CglibDynamicProxy implements MethodInterceptor {

    private final Class<? extends Person> clazz;

    public CglibDynamicProxy(Class<? extends Person> clazz) {
        this.clazz = clazz;
    }

    public Person getTarget() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (Person) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("执行前");
        Object invoke = proxy.invokeSuper(obj, args);
        System.out.println("执行后");
        return invoke;
    }
}
