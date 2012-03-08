package edu.arhs.first1100.oopctl.handlers;

public class JoystickAxisHandler {

    private String name;
    private double deadBandWidth = 0.0;

    public void setHandleValue(double value) {
    }

    //change to set is need be
    public void setNewHandleValue(double value) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeadBand(double in) {
        deadBandWidth = in;
    }

    public double getDeadBand() {
        return deadBandWidth;
    }
}
