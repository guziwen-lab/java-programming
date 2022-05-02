package com.litianyi.simple.factory;

import com.litianyi.simple.factory.pojo.Computer;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 5:20 PM
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        Computer computer = ComputerFactory.createComputer("apple");
        computer.start();
    }
}
