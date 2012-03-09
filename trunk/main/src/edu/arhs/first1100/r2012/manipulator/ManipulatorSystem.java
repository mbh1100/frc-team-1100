package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.DigitalOutput;
public class ManipulatorSystem {

    private final int TURRET_MAX = 487;
    private final int TURRET_MIN = 187;
    private static ManipulatorSystem instance = null;
    private AnalogChannel turretRotation;
    private DigitalInput outerBallArmTopSwitch;
    private DigitalInput outerBallArmBottomSwitch;
    private Jaguar topShooterWheel;
    private Jaguar bottomShooterWheel;
    private Victor shooterFeedWheels;
    private Victor leadScrewTilt;
    private Victor turret;
    private Victor intakeRoller;
    private Victor mainLiftBelt;
    private Relay neckBelt;
    private Victor outerBallRoller;
    private Victor outerBallArm;
    private Victor rampArm;
    private Relay illuminator;

    private ManipulatorSystem() {
        //BallCounter.getInstance().start();

        turretRotation = new AnalogChannel(1);

        //outerBallArmTopSwitch = new DigitalInput(1, 1);
        //outerBallArmBottomSwitch = new DigitalInput(1, 1);


        topShooterWheel = new Jaguar(1, 3);  //ok
        bottomShooterWheel = new Jaguar(2, 3);  //ok
        shooterFeedWheels = new Victor(2, 4);  //ok
        leadScrewTilt = new Victor(1, 4);  // ok
        turret = new Victor(1, 5);  //ok
        intakeRoller = new Victor(2, 2);  //ok
        mainLiftBelt = new Victor(1, 2);//1, 2  //ok
        neckBelt = new Relay(2, 1);   //ok
        outerBallRoller = new Victor(1, 6);
        outerBallArm = new Victor(2, 7);
        rampArm = new Victor(1, 7);
        illuminator = new Relay(1,1);
    }

    public static ManipulatorSystem getInstance() {
        if (instance == null) {
            synchronized (ManipulatorSystem.class) {
                if (instance == null) {
                    instance = new ManipulatorSystem();
                }
            }
        }
        return instance;
    }

    public void setShooterSpeed(double speed){
        topShooterWheel.set(speed);
        bottomShooterWheel.set(-speed);

    }

    /**
     * Top left shooter belt
     *
     * @param speed
     */
    public void setShooterFeedWheels(double speed) {
        shooterFeedWheels.set(-speed);
    }

    public void setLeadScrewTilt(double speed) {
        leadScrewTilt.set(speed);
    }

    /**
     * sets the turret rotation as long as it is within the current turret range
     * limit.
     *
     * @param speed (speed of the turret)
     */
    public void setTurretRotationSpeed(double speed) {
       /* System.out.println("turret rotation: " + turretRotation.getValue());
        if (Math.abs(turretRotation.getValue()) >= TURRET_MAX) {
            turret.set((speed < 0) ? 0 : speed);
        } else if (turretRotation.getValue() <= TURRET_MIN) {
            turret.set((speed > 0) ? 0 : speed);
        } else {
            turret.set(speed);
        }*/
        turret.set(speed/2);
    }
    
    public int getTurretRotation() {
        return turretRotation.getValue();
    }

    /**
     * Sets the intake roller Intake roller will not intake if BallCounter is
     * counting an illegal number of balls. Counting balls was removed.
     *
     * @param speed (Speed the roller)
     */
    public void setIntakeRoller(double speed) {
        /*if (!BallCounter.getInstance().canIntake()) {
            intakeRoller.set(-Math.abs(speed));
        } else {
            intakeRoller.set(speed);
        }
        * 
        */
        intakeRoller.set(-speed);
    }

    /**
     * Sets the speed of the main lift belt.
     */
    public void setMainLiftBelt(double speed) {
        mainLiftBelt.set(-speed);
    }

    /**
     * @returns speed of the main lift belt.
     */
    public double getMainLiftBelt() {
        return mainLiftBelt.get();
    }
    /**
     * Sets the speed of the final belt into the shooter. Note: This belt is
     * always on.
     *
     * @param Speed to set the belt.
     */
    public void setNeckBelt(Relay.Value in) {
        neckBelt.set(in);
    }

    /**
     * Sets the speed of the outer roller.
     *
     * @param speed to set the roller.
     */
    void setOuterBallRoller(double speed) {
        outerBallRoller.set(speed);
    }

    void setOuterBallArm(double speed) {
        /*
         * if((outerBallArmTopSwitch.get() && speed < 0.0) ||
         * (outerBallArmBottomSwitch.get() && speed > 0.0)) {
         * outerBallArm.set(0.0); } else { outerBallArm.set(speed); }
         */
        outerBallArm.set(speed);
    }

    void setRampArm(double speed) {
        rampArm.set(speed);
    }
    
    public void illuminatorOn()
    {
        illuminator.set(Relay.Value.kForward);
    }
    
    public void illuminatorOff()
    {
        illuminator.set(Relay.Value.kOff);
    }

    public void stop() {
        this.topShooterWheel.set(0);
        this.bottomShooterWheel.set(0);
        this.intakeRoller.set(0);
        this.shooterFeedWheels.set(0.0);
        this.leadScrewTilt.set(0.0);
        this.mainLiftBelt.set(0);
        this.neckBelt.set(Relay.Value.kOff);
        //this.outerBallArm.set(0);       NULL
        //this.outerBallRoller.set(0);    NULL
        this.rampArm.set(0);
        this.turret.set(0);
    }
}
