/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.sensors;
import edu.wpi.first.wpilibj.Encoder;

/**
 * @author Team1100
 */
public class MotorEncoder
{
    Encoder enLeft;
    Encoder enRight;

    static MotorEncoder instance;

    public static MotorEncoder getInstance()
    {
        if (instance == null)
        {
            synchronized(MotorEncoder.class)
            {
                if (instance == null)
                {
                    instance = new MotorEncoder();
                }
            }
        }
        return instance;
    }

    private MotorEncoder()
    {
        enLeft = new Encoder(13, 14, true); // 3rd parameter is true to reverse direction
        enRight = new Encoder(11, 12, true); // 3rd parameter is false to keep direction
        enRight.setDistancePerPulse(.1);
        enLeft.setDistancePerPulse(.1);
        start();
    }

    public void start()
    {
        enLeft.start();
        enRight.start();
    }

    public double getRight()
    {
        double x = enRight.getRate();
        //used to check for NaN
        //System.out.println("RATE:   " + x);
        if(x/x == 1)
        {
            return x;
        }
        else {return 0;}
    }

    public double getLeft()
    {
        double x = enLeft.getRate();
        //used to check for NaN
        //System.out.println("RATE:   " + x);
        if(x/x == 1)
        {
            return x;
        }
        else {return 0;}
    }
}