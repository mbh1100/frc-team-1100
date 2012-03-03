/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.manipulator;

import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author team1100
 */
public class RampArm extends SystemBase
{
    DigitalInput upperSwitch;
    DigitalInput lowerSwitch;

    private static final double kRampSpeed = 1.0;

    private double rampSpeed;

    private RampArm()
    {
        // check switches 10 times per second
        super(100);
        upperSwitch = new DigitalInput(1,2);
        lowerSwitch = new DigitalInput(1,1);
    }

    public boolean isUpperLimit()
    {
        return !upperSwitch.get();
    }

    public boolean isLowerLimit()
    {
        return !lowerSwitch.get();
    }

    public void moveUp()
    {
        rampSpeed = kRampSpeed;
    }

    public void moveDown()
    {
        rampSpeed = -kRampSpeed;
    }

    public void dontMove()
    {
        rampSpeed = 0;
    }

    public void stop()
    {
        rampSpeed = 0;
        super.stop();
    }

    public void tick()
    {
        if (rampSpeed > 0 && !isUpperLimit() ||
            rampSpeed < 0 && !isLowerLimit())
        {
            ManipulatorSystem.getInstance().setRampArm(-rampSpeed);
        }
        else
        {
            ManipulatorSystem.getInstance().setRampArm(0.0);
        }
    }

    private static RampArm instance = null;

    public static RampArm getInstance()
    {
        if(instance == null)
        {
            instance = new RampArm();
            instance.start();
        }

        return instance;
    }


}
