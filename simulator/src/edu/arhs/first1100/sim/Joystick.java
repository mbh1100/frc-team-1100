/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.JoystickSim;

/**
 *
 * @author Joed
 */
public class Joystick {

    JoystickSim sim;

    public Joystick(int port) {
        this.sim = Simulation.getJoystickSim(port);
    }

    public double getX() {
        return sim.getXValue();
    }

    public double getY() {
        return sim.getYValue();
    }
}
