/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import edu.arhs.first1100.sim.hardware.Output;

/**
 *
 * @author Joed
 */
public class NullOutput implements Output {
    int channel;
    
    public NullOutput(int channel)
    {
        this.channel = channel;
    }
    
    public int getChannel() {
        return channel;
    }
    
    public void set(double value) {System.err.println("WARNING: NullOutput#set called"); }
}
