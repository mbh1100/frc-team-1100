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
    
    
    public VeloTest()
    {
        s1 = 11;
        c1 = 10;
        s2 = 1;
        c2 = 2;
        jaguar = new Jaguar(s2, c2);
        encoder = new Encoder(s1, c1);
        ratePID = new RatePid(encoder, jaguar);
        ratePID.enable();
    }
    public void reset()
    {
        ratePID.disable();
        encoder.reset();
        ratePID.enable();
    }
    
    public void findVelocity()
    {   
        ratePID.setSetpoint(.3);
        System.out.println("rate is: " + encoder.getRate() + "<~~~~~~~~~~~~~  FOR ALEX'S USE");        
        System.out.println("rate is: " + encoder.getDistance() + "<~~  FOR ALEX'S USE");        
    }
    
    public void disable()
    {
        ratePID.disable();
    }
}
