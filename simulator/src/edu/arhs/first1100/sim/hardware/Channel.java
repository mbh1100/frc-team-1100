/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

/**
 *
 * @author Joed
 */
public class Channel {
    int channel;
    
    public Channel(int channel) {
        this.channel = channel;
    }
    public int getChannel() {
        return channel;
    }

    public double get() {
        System.err.println("WARNING: Channel#get called");
        return 0.0;
    }

    public void set(double value) {
        System.err.println("WARNING: Channel#set called");
    }
}
