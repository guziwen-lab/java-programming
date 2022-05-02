package com.litianyi.constructor;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 1:51 PM
 */
public class ConstructorTest {

    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder();

        computerBuilder.installDisplay("显示器");
        computerBuilder.installMainUnit("主机");
        computerBuilder.installKeyboard("键盘");
        computerBuilder.installMouse("鼠标");

        Computer computer = computerBuilder.getComputer();
        System.out.println(computer);
    }
}
