
package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogChannel;

public class ManipulatorSystem
{
    private static ManipulatorSystem instance = null;
    private BallCounter ballCounter;
    
    private AnalogChannel turretRotation;
    private double turretMaxRotation;
    
    private Jaguar topShooterWheel;
    private Jaguar bottomShooterWheel;
    private Victor leftShooterBelt;
    private Victor rightShooterBelt;
    private Relay leadScrewTilt;
    private Victor turret;
    private Victor intakeRoller;
    private Victor mainLiftBelt;
    private Relay topLiftBelt;
    private Relay neckBelt;
    private Victor outerBallRoller;
    private Victor outerBallArm;
    private Victor rampArm;

    private ManipulatorSystem()
    {
        ballCounter = new BallCounter();
        ballCounter.start();
        
        turretRotation = new AnalogChannel(1);
        turretMaxRotation = 5.0;
        
        
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

    public void setTopShooterWheel(double speed)
    {
        if(speed * 1.2 >= 1)
            topShooterWheel.set(-speed);
        else
        {
            topShooterWheel.set(-speed*1.2);
        }
    }
    public void setBottomShooterWheel(double speed)
    {
        bottomShooterWheel.set(0.5*speed);
    }
    
    /**
     * Top left shooter belt, cannot drive backwards
     * @param speed 
     */
    public void setLeftShooterBelt(double speed)
    {
        leftShooterBelt.set((speed > 0.0)?speed:0);
    }
    
    /**
     * Top left shooter belt, cannot drive backwards
     * @param speed 
     */
    public void setRightShooterBelt(double speed)
    {
        rightShooterBelt.set((speed > 0.0)?speed:0);
    }
    
    public void setLeadScrewTilt(Relay.Value in)
    {
        leadScrewTilt.set(in);
    }
    
    /**
     * sets the turret rotation and limits turret to range
     * @param speed 
     */
    public void setTurretRotation(double speed)
    {
        if(Math.abs(turretRotation.getValue()) >= turretMaxRotation)
        {
            turret.set(0.0);
        }
        else
        {
        turret.set(speed);
        }
    }
    
    /**
     * Sets the intake roller
     * Intake roller will not intake if BallCounter is counting an illegal number
     * of balls.
     * @param speed 
     */
    public void setIntakeRoller(double speed)
    {
        if(!ballCounter.canIntake())
        {
            intakeRoller.set((speed > 0)?-speed:speed);
        } 
        else 
        {
            intakeRoller.set(speed);
        }
    }
    
    public void setMainLiftBelt(double speed)
    {
        mainLiftBelt.set(speed);
    }
    
    public double getMainLiftBelt(){
        return mainLiftBelt.get();        
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
        this.topShooterWheel.set(0);
        this.bottomShooterWheel.set(0);
        this.intakeRoller.set(0);
        this.leftShooterBelt.set(0);
        this.rightShooterBelt.set(0);
        this.leadScrewTilt.set(Relay.Value.kOff);
        this.mainLiftBelt.set(0);
        this.neckBelt.set(Relay.Value.kOff);
        //this.outerBallArm.set(0);       NULL
        //this.outerBallRoller.set(0);    NULL
        //this.rampArm.set(0);            NULL
        //this.turretRotation.set(0);     NULL
    }
}
