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
public class Ultrasonic {

    Channel ping;
    Channel echo;

    public Ultrasonic(int pingChannel, int echoChannel) {
        ping = Simulation.getChannel(pingChannel);
        echo = Simulation.getChannel(echoChannel);
    }

    public double getRange() {
        return echo.get();
    }
}
