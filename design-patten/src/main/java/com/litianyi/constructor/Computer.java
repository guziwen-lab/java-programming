package com.litianyi.constructor;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/1 11:20 PM
 */
public class Computer {

    private String display;

    private String mainUnit;

    private String mouse;

    private String keyboard;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "display='" + display + '\'' +
                ", mainUnit='" + mainUnit + '\'' +
                ", mouse='" + mouse + '\'' +
                ", keyboard='" + keyboard + '\'' +
                '}';
    }
}
