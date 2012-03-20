package edu.arhs.first1100.oopctl.controllers;

import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickAxis {

    private JoystickAxisHandler jah;
    private Joystick.AxisType axis;
    private Joystick js;
    double last_value;
    double deadBandWidth;

    public JoystickAxis(Joystick js, Joystick.AxisType axisId) {
        this.axis = axisId;
        this.js = js;
        this.jah = new JoystickAxisHandler();
    }

    public void update() {
        double value = deadBand(js.getAxis(axis));

        jah.setHandleValue(value);

        System.out.println(value);
        if (value != last_value) {
            jah.setNewHandleValue(value);
        }

        last_value = value;
    }

    public void bind(JoystickAxisHandler h) {
        if (h != null) {
            jah = h;
        } else {
            jah = new JoystickAxisHandler();
        }
    }

    private double deadBand(double in) {
        if (Math.abs(in) < jah.getDeadBand()) {
            return 0;
        }
        return in;
    }
}
