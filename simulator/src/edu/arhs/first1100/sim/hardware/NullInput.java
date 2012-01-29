/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import edu.arhs.first1100.sim.hardware.Input;

/**
 *
 * @author Joed
 */
public class NullInput implements Input {
    int channel;
    
    public NullInput(int channel)
    {
        this.channel = channel;
    }
    
    public int getChannel() {
        return channel;
    }
    
    public double get() { System.err.println("WARNING: NullInput#get called"); return 0.0; }

}
