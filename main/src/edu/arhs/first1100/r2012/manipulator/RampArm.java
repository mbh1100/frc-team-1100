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
public class RampArm extends SystemBase {

    DigitalInput upperSwitch;
    DigitalInput lowerSwitch;
    private double rampSpeed;

    private RampArm() {
        // check switches 20 times per second
        super(50);
        upperSwitch = new DigitalInput(1, 2);
        lowerSwitch = new DigitalInput(1, 1);
    }

    public boolean isFullyUndeployed() {
        return !upperSwitch.get();
    }

    public boolean isFullyDeployed() {
        return !lowerSwitch.get();
    }

    public void setSpeed(double speed) {
        rampSpeed = speed;
    }
    
    public void deploy()
    {
        setSpeed(0.5);
    }

    public void undeploy()
    {
        setSpeed(-0.5);
    }
    
    public void dontMove() {
        setSpeed(0.0);
    }

    public void stop() {
        rampSpeed = 0;
        super.stop();
    }

    public void tick() {
        // rampSpeed > 0 deploys, < 0 undeploys.
        if (rampSpeed < 0 && !isFullyUndeployed() ||
            rampSpeed > 0 && !isFullyDeployed()) {
            //System.out.println("POWERING RAMP ARM");
            ManipulatorSystem.getInstance().setRampArm(rampSpeed);
        } else {
            //System.out.println("SETTING RAMP ARM TO ZERO");
            ManipulatorSystem.getInstance().setRampArm(0.0);
        }
    }
    private static RampArm instance = null;

    public static RampArm getInstance() {
        if (instance == null) {
            instance = new RampArm();
            instance.start();
        }
        return instance;
    }
}
