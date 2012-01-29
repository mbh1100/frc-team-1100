/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.Channel;

/**
 *
 * @author Joed
 */
public class Jaguar {

    protected double speed;
    protected int channel;
    protected Channel motor;

    public Jaguar(int channel) {
        motor = Simulation.getChannel(channel);
    }

    public double get() {
        return speed;
    }

    public void set(double speed) {
        this.speed = Math.min(1.0, Math.abs(speed)) * Math.signum(speed);
        motor.set(speed);
    }
}
