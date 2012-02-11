/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.sensors;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author Ryan
 */
public class MotorEncoder
{
    Encoder enLeft;
    Encoder enRight;
    public MotorEncoder()
    {
        enLeft = new Encoder(11, 12);
        enRight = new Encoder(13, 14, true); // 3rd parameter is reverse direction
        enRight.setDistancePerPulse(.1);
        enLeft.setDistancePerPulse(.1);
    }

    public void start()
    {
        enLeft.start();
        enRight.start();
    }

    public double getRight()
    {
        return enRight.getRate();
    }

    public double getLeft()
    {
        return enLeft.getRate();
    }
}
