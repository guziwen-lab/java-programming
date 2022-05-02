package com.litianyi.dynaminc.proxy;

import com.litianyi.dynaminc.proxy.pojo.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 6:34 PM
 */
public class JDKDynamicProxy implements InvocationHandler {

    private final Person target;

    public JDKDynamicProxy(Person person) {
        this.target = person;
    }

    public Person getTarget() {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
        return (Person) o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDKDynamicProxy do something before!");
        Object invoke = method.invoke(target, args);
        System.out.println("JDKDynamicProxy do something after!");
        return invoke;
    }
}
