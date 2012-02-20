
package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

public class ManipulatorSystem
{
    private static ManipulatorSystem instance = null;

    private Victor topShooterWheel;
    private Victor bottomShooterWheel;
    private Victor leftShooterBelt;
    private Victor rightShooterBelt;
    private Victor leadScrewTilt;
    private Victor turretRotation;
    private Victor intakeRoller;
    private Victor mainLiftBelt;
    private Victor topLiftBelt;
    private Victor outerBallRoller;
    private Victor outerBallArm;
    private Victor rampArm;

    private ManipulatorSystem()
    {
        //topShooterWheel = new Victor(1,5);
        //bottomShooterWheel = new Victor(1,6);
        //leftShooterBelt = new Victor(2,1);
        //rightShooterBelt = new Victor(2,3);
        //leadScrewTilt = new Victor(1,1);
        //turretRotation = new Victor(2,3);
        intakeRoller = new Victor(2,2);  //ok location
        mainLiftBelt = new Victor(1,2);  //ok location
        //topLiftBelt = new Victor(1,4);
        //outerBallRoller = new Victor(2,5);
        //outerBallArm = new Victor(2,6);
        //rampArm = new Victor(2,7);
    }

    public static ManipulatorSystem getInstance()
    {
        synchronized(ManipulatorSystem.class)
        {
            if (instance == null)
            {
                instance = new ManipulatorSystem();
            }
        }
        return instance;
    }

    public Victor getTurretRotation()
    {
        return turretRotation;
    }

    public void setTopShooterWheel(double speed)
    {
        topShooterWheel.set(speed);
    }
    public void setBottomShooterWheel(double speed)
    {
        bottomShooterWheel.set(speed);
    }
    public void setLeftShooterBelt(double speed)
    {
        leftShooterBelt.set(speed);
    }
    public void setRightShooterBelt(double speed)
    {
        rightShooterBelt.set(speed);
    }
    public void setLeadScrewTilt(double speed)
    {
        leadScrewTilt.set(speed);
    }
    public void setTurretRotation(double speed)
    {
        turretRotation.set(speed);
    }
    public void setIntakeRoller(double speed)
    {
        intakeRoller.set(speed);
    }
    public void setMainLiftBelt(double speed)
    {
        mainLiftBelt.set(speed);
    }
    public void setTopLiftBelt(double speed)
    {
        topLiftBelt.set(speed);
    }
    public void setOuterBallRoller(double speed)
    {
        outerBallRoller.set(speed);
    }
    public void setOuterBallArm(double speed)
    {
        outerBallArm.set(speed);
    }
    public void setRampArm(double speed)
    {
        rampArm.set(speed);
    }
}
