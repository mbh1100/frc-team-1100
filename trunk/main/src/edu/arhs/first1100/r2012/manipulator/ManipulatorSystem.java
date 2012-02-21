
package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

public class ManipulatorSystem
{
    private static ManipulatorSystem instance = null;

    private Jaguar topShooterWheel;
    private Jaguar bottomShooterWheel;
    private Victor leftShooterBelt;
    private Victor rightShooterBelt;
    private Relay leadScrewTilt;
    private Victor turretRotation;
    private Victor intakeRoller;
    private Victor mainLiftBelt;
    private Relay topLiftBelt;
    private Relay neckBelt;
    private Victor outerBallRoller;
    private Victor outerBallArm;
    private Victor rampArm;

    private ManipulatorSystem()
    {
        topShooterWheel = new Jaguar(1,3);  //ok
        bottomShooterWheel = new Jaguar(2,3);  //ok
        leftShooterBelt = new Victor(2,4);   //ok
        rightShooterBelt = new Victor(1,4);  //ok
        leadScrewTilt = new Relay(1,1);  // ok
        //turretRotation = new Victor(2,3);
        intakeRoller = new Victor(2,2);  //ok
        mainLiftBelt = new Victor(1,2);//1, 2  //ok
        neckBelt = new Relay(2,1);   //ok
        //outerBallRoller = new Victor(2,5);
        //outerBallArm = new Victor(2,6);
        //rampArm = new Victor(2,7);
    }

    public static ManipulatorSystem getInstance()
    {
        if(instance == null)
        {
            synchronized(ManipulatorSystem.class)
            {
                if (instance == null)
                {
                    instance = new ManipulatorSystem();
                }
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
        topShooterWheel.set(-speed);
    }
    public void setBottomShooterWheel(double speed)
    {
        bottomShooterWheel.set(0.9*speed);
    }
    public void setLeftShooterBelt(double speed)
    {
        leftShooterBelt.set(speed);
    }
    public void setRightShooterBelt(double speed)
    {
        rightShooterBelt.set(speed);
    }
    public void setLeadScrewTilt(Relay.Value in)
    {
        leadScrewTilt.set(in);
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
    public void setTopLiftBelt(Relay.Value value)
    {
        topLiftBelt.set(value);
    }
    public void setNeckBelt(Relay.Value in)
    {
        neckBelt.set(in);
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
    public void stop()
    {
        this.bottomShooterWheel.set(0);
        this.intakeRoller.set(0);
        this.leadScrewTilt.set(Relay.Value.kOff);
        this.leftShooterBelt.set(0);
        this.mainLiftBelt.set(0);
        this.neckBelt.set(Relay.Value.kOff);
        this.outerBallArm.set(0);
        this.outerBallRoller.set(0);
        this.rampArm.set(0);
        this.rightShooterBelt.set(0);
        this.topLiftBelt.set(Relay.Value.kOff);
        this.topShooterWheel.set(0);
        this.turretRotation.set(0);
    }
}
