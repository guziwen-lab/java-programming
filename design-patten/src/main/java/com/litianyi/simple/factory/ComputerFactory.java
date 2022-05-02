package com.litianyi.simple.factory;

import com.litianyi.simple.factory.pojo.AppleComputer;
import com.litianyi.simple.factory.pojo.Computer;
import com.litianyi.simple.factory.pojo.LenovoComputer;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 4:46 PM
 */
public class ComputerFactory {

    public static Computer createComputer(String type) {
        Computer computer = null;

        switch (type) {
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "apple":
                computer = new AppleComputer();
                break;
        }

        return computer;
    }
}
