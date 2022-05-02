package com.litianyi.dynaminc.proxy.pojo;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 6:34 PM
 */
public class Bob implements Person {

    @Override
    public void doSomething() {
        System.out.println("I am Bob");
    }
}
