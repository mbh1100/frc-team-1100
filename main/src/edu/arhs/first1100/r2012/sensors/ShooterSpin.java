/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.sensors;

import edu.wpi.first.wpilibj.Counter;

/**
 *
 * @author frc
 */
public class ShooterSpin {
    
    edu.wpi.first.wpilibj.Counter counter;
    static ShooterSpin instance;
    
    private ShooterSpin()
    {
        counter = new Counter(2,2);
        counter.start();
    }
    
    public static ShooterSpin getInstance()
    {
        if (instance == null) {
            synchronized (ShooterSpin.class) {
                if (instance == null) {
                    instance = new ShooterSpin();
                }
            }
        }
        return instance;
    }
    
    public double getRPM()
    {
        double tmp = counter.getPeriod();
        if (Double.isInfinite(tmp))
        {
            return 0;
        }
        return 30.0/counter.getPeriod();
    }
    
}
