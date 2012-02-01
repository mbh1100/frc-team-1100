/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;

import edu.arhs.first1100.r2012.pid.RatePid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

public class VeloTest 
{
    int s1;
    int c1;
    int s2;
    int c2;
    Jaguar jaguar;
    Encoder encoder;
    RatePid ratePID;
    edu.wpi.first.wpilibj.Joystick stick;
    
    public VeloTest()
    {
        s1 = 10;
        c1 = 11;
        s2 = 1;
        c2 = 2;
        
        stick = new edu.wpi.first.wpilibj.Joystick(1);
        jaguar = new Jaguar(s2, c2);
        encoder = new Encoder(s1, c1, true);
        ratePID = new RatePid(encoder, jaguar);
        ratePID.setSetpoint(3.0);
    }
    public void start()
    {
        encoder.start();
        ratePID.enable();
    }
    public void reset()
    {
        ratePID.disable();
        encoder.reset();
        encoder.setDistancePerPulse(0.004);
        encoder.start();
        ratePID.enable();
    }
    
    public void findVelocity()
    {
        System.out.println(encoder.getRate() + "<~~~~~~~~~~~~~~~~~~~~~~~  Rate");
        System.out.println("error------------------------> " + ratePID.getError());
        System.out.println("input=" + stick.getZ());
        ratePID.setSetpoint(stick.getZ() * 15);
    }
    
    public void disable()
    {
        ratePID.disable();
    }
}
