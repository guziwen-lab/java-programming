package com.litianyi.simple.factory.pojo;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 5:14 PM
 */
public class LenovoComputer extends Computer {

    @Override
    public void start() {
        System.out.println("联想电脑启动");
    }
}
