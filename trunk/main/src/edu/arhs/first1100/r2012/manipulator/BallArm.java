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
public class BallArm extends SystemBase {

    
    DigitalInput upperSwitch;
    DigitalInput lowerSwitch;
    public static final double oRollerSpeed = -0.2;
    public static final double kRollerSpeed = 0.2;
    private double armSpeed;
    private double rollerSpeed;
    public static BallArm instance;

    public static BallArm getInstance()
    {
        if (instance == null) {
            instance = new BallArm();
            instance.start();
        }
        return instance;
    }
    
    private BallArm()
    {
        super(50);
        upperSwitch = new DigitalInput(1,3);
        lowerSwitch = new DigitalInput(1,4);
        armSpeed = 0;
    }
    
    public boolean isFullyUndeployed() {
        // this switch is wired normally closed, so false when no
        // actuated.
        //return upperSwitch.get();
        return true;
    }

    public boolean isFullyDeployed() {
        //return !lowerSwitch.get();
        return false;
    }
    
    public void deploy()
    {
        setArmSpeed(0.7);
    }
    
    public void undeploy()
    {
        setArmSpeed(-0.7);
    }
    
    public void enableRoller(boolean backwards)
    {
        setRollerSpeed(backwards? -0.7 : 0.7);
    }
    
    public void disableRoller()
    {
        setRollerSpeed(0.0);
    }
    
    private void setArmSpeed(double speed) {
        armSpeed = speed;
    }

    private void setRollerSpeed(double speed) {
        rollerSpeed = speed;
    }
    
    public void dontMove() {
        armSpeed = 0;
    }
    public void tick() {
        // move the arm. ArmSpeed > 0 means deploy. 
        /*if (armSpeed < 0 && !isFullyUndeployed() ||
            armSpeed > 0 && !isFullyDeployed() && RampArm.getInstance().isFullyUndeployed()) {
            //System.out.println("POWERING INTAKE ROLLER");
            ManipulatorSystem.getInstance().setOuterBallArm(armSpeed);
        } else {
            //System.out.println("SETTING RAMP ARM TO ZERO");
            ManipulatorSystem.getInstance().setOuterBallArm(0.0);
        }
        * 
        */
        //ManipulatorSystem.getInstance().setOuterBallRoller(rollerSpeed);
            
    }
}
