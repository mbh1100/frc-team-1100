/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.manipulator;

import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.arhs.first1100.util.SystemBase;
/**
 *
 * @author team1100
 */
public class IntakeRoller extends SystemBase {

    
    DigitalInput upperSwitch;
    DigitalInput lowerSwitch;
    public static final double oRollerSpeed = -0.2;
    public static final double kRollerSpeed = 0.2;
    private double armSpeed;
    public static IntakeRoller instance;

    public static IntakeRoller getInstance()
    {
        if (instance == null) {
            instance = new IntakeRoller();
            instance.start();
        }
        return instance;
    }
    
    private IntakeRoller()
    {
        super(10);
        upperSwitch = new DigitalInput(2,1);
        lowerSwitch = new DigitalInput(2,2);
        armSpeed = 0;
    }
    
    public boolean isUpperLimit() {
        //upper limit switch wasn't connected at the time.
        return true;
        //return !upperSwitch.get();
    }

    public boolean isLowerLimit() {
        return !lowerSwitch.get();
    }
    
    public void setSpeed(double speed) {
        armSpeed = speed;
    }

    public void dontMove() {
        armSpeed = 0;
    }
    public void tick() {
        if (armSpeed > 0 && !isUpperLimit()
                || armSpeed < 0 && !isLowerLimit() && RampArm.getInstance().isUpperLimit()) {
            System.out.println("POWERING INTAKE ROLLER");
            ManipulatorSystem.getInstance().setRampArm(-armSpeed);
        } else {
            //System.out.println("SETTING RAMP ARM TO ZERO");
            ManipulatorSystem.getInstance().setRampArm(0.0);
        }
    }
    
    public void intake() {
        ManipulatorSystem.getInstance().setIntakeRoller(kRollerSpeed);
    }

    public void outtake() {
        ManipulatorSystem.getInstance().setIntakeRoller(oRollerSpeed);
    }
    
 
}
