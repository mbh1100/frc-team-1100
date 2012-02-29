
package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;

public class ManipulatorSystem
{

    private final int TURRET_MAX = 487;
    private final int TURRET_MIN = 187;

    private static ManipulatorSystem instance = null;

    private AnalogChannel turretRotation;

    private DigitalInput outerBallArmTopSwitch;
    private DigitalInput outerBallArmBottomSwitch;

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
        BallCounter.getInstance().start();

        turretRotation = new AnalogChannel(1);

        //outerBallArmTopSwitch = new DigitalInput(1, 1);
        //outerBallArmBottomSwitch = new DigitalInput(1, 1);


        topShooterWheel = new Jaguar(1,3);  //ok
        bottomShooterWheel = new Jaguar(2,3);  //ok
        leftShooterBelt = new Victor(2,4);   //ok
        rightShooterBelt = new Victor(1,4);  //ok
        leadScrewTilt = new Relay(1,1);  // ok
        turret = new Victor(1,5);  //ok
        intakeRoller = new Victor(2,2);  //ok
        mainLiftBelt = new Victor(1,2);//1, 2  //ok
        neckBelt = new Relay(2,1);   //ok
        outerBallRoller = new Victor(1,6);
        outerBallArm = new Victor(2,7);
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
            topShooterWheel.set(-speed);
    }
    public void setBottomShooterWheel(double speed)
    {
        bottomShooterWheel.set(speed);
    }

    /**
     * Top left shooter belt
     * @param speed
     */
    public void setLeftShooterBelt(double speed)
    {
        leftShooterBelt.set(speed);
    }

    /**
     * Top left shooter belt
     * @param speed
     */
    public void setRightShooterBelt(double speed)
    {
        rightShooterBelt.set(speed);
    }

    public void setLeadScrewTilt(Relay.Value in)
    {
        leadScrewTilt.set(in);
    }

    /**
     * sets the turret rotation as long as it is within the current turret range limit.
     * @param speed (speed of the turret)
     */
    public void setTurretRotationSpeed(double speed)
    {
        if(Math.abs(turretRotation.getValue()) >= TURRET_MAX)
        {
            turret.set((speed < 0)?0:speed);
        }
        else if(turretRotation.getValue() <= TURRET_MIN)
        {
            turret.set((speed > 0)?0:speed);
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
     * Counting balls was removed.
     * @param speed (Speed the roller)
     */
    public void setIntakeRoller(double speed)
    {
        if(!BallCounter.getInstance().canIntake())
        {
            intakeRoller.set(-Math.abs(speed));
        }
        else
        {
            intakeRoller.set(speed);
        }
    }

     /**
     *  Sets the speed of the main lift belt.
     */
    public void setMainLiftBelt(double speed)
    {
        mainLiftBelt.set(speed);
    }
    /**
     * @returns speed of the main lift belt.
     */
    public double getMainLiftBelt(){
        return mainLiftBelt.get();
    }
    /**
     * Does not exist.
     * @param speed to set the non-existent belt.
     */
    public void setTopLiftBelt(Relay.Value value)
    {
        topLiftBelt.set(value);
    }
    /**
     * Sets the speed of the final belt into the shooter.
     * Note: This belt is always on.
     * @param Speed to set the belt.
     */
    public void setNeckBelt(Relay.Value in)
    {
        neckBelt.set(in);
    }
    /**
     * Sets the speed of the outer roller.
     * @param speed to set the roller.
     */
    public void setOuterBallRoller(double speed)
    {
        outerBallRoller.set(speed);
    }

    public void setOuterBallArm(double speed)
    {
        /*
        if((outerBallArmTopSwitch.get() && speed < 0.0) ||
                (outerBallArmBottomSwitch.get() && speed > 0.0))
        {
            outerBallArm.set(0.0);
        }
        else
        {
            outerBallArm.set(speed);
        }
        */
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
        this.turret.set(0);
    }
}
