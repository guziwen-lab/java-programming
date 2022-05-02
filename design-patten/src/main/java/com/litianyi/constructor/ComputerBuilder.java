package com.litianyi.constructor;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/2 1:48 PM
 */
public class ComputerBuilder {

    private final Computer computer = new Computer();

    public void installDisplay(String display) {
        computer.setDisplay(display);
    }

    public void installMainUnit(String mainUnit) {
        computer.setMainUnit(mainUnit);
    }

    public void installMouse(String mouse) {
        computer.setMouse(mouse);
    }

    public void installKeyboard(String keyboard) {
        computer.setKeyboard(keyboard);
    }

    public Computer getComputer() {
        return computer;
    }
}
