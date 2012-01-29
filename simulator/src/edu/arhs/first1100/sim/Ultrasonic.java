/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.Output;
import edu.arhs.first1100.sim.hardware.Input;

/**
 *
 * @author Joed
 */
public class Ultrasonic {
    Output ping;
    Input echo;
    
    public Ultrasonic(int pingChannel, int echoChannel)
    {
        ping = Simulation.getOutput(pingChannel);
        echo = Simulation.getInput(echoChannel);
    }
    
    public double getRange()
    {
        return echo.get();
    }
}
