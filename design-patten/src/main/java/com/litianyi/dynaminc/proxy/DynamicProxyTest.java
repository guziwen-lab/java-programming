package com.litianyi.dynaminc.proxy;

import com.litianyi.dynaminc.proxy.pojo.Bob;
import com.litianyi.dynaminc.proxy.pojo.Person;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/17 5:20 PM
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        // 写出代理对象的class文件
//        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//
//        JDKDynamicProxy proxy = new JDKDynamicProxy(new Bob());
//        Person target = proxy.getTarget();
//        target.doSomething();

        CglibDynamicProxy proxy = new CglibDynamicProxy(Bob.class);
        Person target = proxy.getTarget();
        target.doSomething();
    }
}
