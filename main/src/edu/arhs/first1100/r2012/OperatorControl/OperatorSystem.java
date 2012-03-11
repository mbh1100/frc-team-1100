package edu.arhs.first1100.r2012.OperatorControl;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.oopctl.controllers.PS3Controller;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.arhs.first1100.oopctl.controllers.AttackThree;
import edu.arhs.first1100.oopctl.controllers.XboxController;
import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.arhs.first1100.r2012.manipulator.BallCounter;
import edu.arhs.first1100.r2012.manipulator.RampArm;
import edu.arhs.first1100.r2012.manipulator.BallArm;
import edu.arhs.first1100.r2012.pid.TurretPid;
import edu.wpi.first.wpilibj.Relay;
import edu.arhs.first1100.util.DSLog;

public class OperatorSystem {
    /**
     * Drives Left Side of the Robot
     */
    class LeftAxisY extends JoystickAxisHandler {
        EncoderPIDLeft l;
        public void setHandleValue(double value) {
            if (raw_tank) {
                if (l.isEnable()) l.disable();
                DriveSystem.getInstance().driveLeft(value);
            }
            else {
                if (!l.isEnable()) l.enable();
                l.setSetpoint(value * 100);
            }
        }
        public LeftAxisY() {
            l = new EncoderPIDLeft();
            l.setOutputRange(-0.5, 0.5);
            super.setDeadBand(0.05);
        }
    }
    /**
     * Drives Right Side of the Robot
     */
    class RightAxisY extends JoystickAxisHandler {
        EncoderPIDRight r;
        public void setHandleValue(double value) {
            if (raw_tank) {
                if (r.isEnable()) r.disable();
                DriveSystem.getInstance().driveRight(value);
            }
            else {
                if (!r.isEnable()) r.enable();
                r.setSetpoint(value * 100);
            }
        }
        public RightAxisY() {
            r = new EncoderPIDRight();
            r.setOutputRange(-0.5, 0.5);
            setDeadBand(0.05);
        }
    }
    /**
     * Toggles between Encoder PID tank drive and direct tank drive Bound to
     * Right Joystick, Button 2
     */
    class ToggleDrive extends ButtonHandler {
        public void pressed() {
            raw_tank = !raw_tank;
            if(raw_tank)    DSLog.log(5, "Raw Tank");
            else            DSLog.log(5, "Encoder Drive");
        }
    }
    class StandardDrive extends JoystickAxisHandler {
        JoystickAxisHandler l;
        JoystickAxisHandler r;
        public void setHandleValue(double value) {
            l.setHandleValue(-value);
            r.setHandleValue(-value);
        }
        StandardDrive() {
            l = new RightAxisY();
            r = new LeftAxisY();
        }
    }
    /**
     * Sets speed of top feeder belts and neck belt
     */
    class ShooterBelts extends ButtonHandler {
        public void pressed() {
            if(invert){
                ManipulatorSystem.getInstance().setShooterFeedWheels(1.0);
                ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kReverse);
                ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
            }
            else{
                ManipulatorSystem.getInstance().setShooterFeedWheels(-1.0);
                ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
                ManipulatorSystem.getInstance().setMainLiftBelt(1.0);
            }
        }
        public void released() {
            ManipulatorSystem.getInstance().setShooterFeedWheels(0.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kOff);
            ManipulatorSystem.getInstance().setMainLiftBelt(0);
            
        }
    }
    class LeadScrewUp extends ButtonHandler {
        public void held() {
            ManipulatorSystem.getInstance().setLeadScrewTilt(-1.0);
        }
        public void released() {
            ManipulatorSystem.getInstance().setLeadScrewTilt(0.0);
        }
    }
    class LeadScrewDown extends ButtonHandler {
        public void held() {
            ManipulatorSystem.getInstance().setLeadScrewTilt(1.0);
        }
        public void released() {
            ManipulatorSystem.getInstance().setLeadScrewTilt(0.0);
        }
    }
    /**
     * Control LeadScrew for up/down turret aiming
     */
    class AnalogLeadScrew extends JoystickAxisHandler {
        public void setHandleValue(double value) {
            if (value > 0.1) {
                ManipulatorSystem.getInstance().setLeadScrewTilt(-1.0);
            }
            else if (value < -0.1) {
                ManipulatorSystem.getInstance().setLeadScrewTilt(1.0);
            }
            else {
                ManipulatorSystem.getInstance().setLeadScrewTilt(0.0);
            }
        }
    }
 

    class WheelieBar extends JoystickAxisHandler {
        public void setHandleValue(double value) {
            RampArm.getInstance().setSpeed(value);
        }
    }

    /**
     * Used for main lift belt system.
     */
    class LiftBelt extends ButtonHandler {
        public void pressed() {
            // Sets value when pressed and released.
            // Makes main lift negative values when inverted and normal when normal.
            if (invert) {
                ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                ManipulatorSystem.getInstance().setIntakeRoller(-0.5);
                ManipulatorSystem.getInstance().setOuterBallRollerOn();
            }
            else {
                ManipulatorSystem.getInstance().setMainLiftBelt(1.0);
                ManipulatorSystem.getInstance().setIntakeRoller(0.5);
                ManipulatorSystem.getInstance().setOuterBallRollerOn();
            }
        }
        public void released(){
            ManipulatorSystem.getInstance().setMainLiftBelt(0.0);
            ManipulatorSystem.getInstance().setIntakeRoller(0.0);
            ManipulatorSystem.getInstance().setOuterBallRollerOff();
            
        }
    }
    
    class IntakeRollerArm extends ButtonHandler {
       private boolean toggle = true;
    
        public void pressed() {
    
            if (RampArm.getInstance().isFullyUndeployed())
            {
                ManipulatorSystem.getInstance().setOuterBallArm(0.7);
            } else {
                RampArm.getInstance().undeploy();
            }
        }
        public void released() {
            ManipulatorSystem.getInstance().setOuterBallArm(0.0);
            RampArm.getInstance().dontMove();
        }
    }
    class WheelieBarButton extends ButtonHandler {
        public void pressed() {
            if (BallArm.getInstance().isFullyUndeployed())
            {
                RampArm.getInstance().deploy();
            } else {
                BallArm.getInstance().undeploy();
            }
        }
        public void released() {
            RampArm.getInstance().dontMove();
            BallArm.getInstance().dontMove();
        }
    }
    class ManipulatorToggle extends ButtonHandler {
        public void pressed() {
            invert = true;
        }
        public void released() {
            invert = false;
        }
    }

    class ShooterSpeedUp extends ButtonHandler {
        public void pressed() {
            if(shooterJump){
                shootspeed = 1.0;
            } else {
                shootspeed += .1;
            }
            if (shootspeed >= 1) {
                shootspeed = 1;
            }
            if (shootspeed <= 0.3) {
                shootspeed = 0.3;
            }
            System.out.println("shootspeed = " + shootspeed);
            ManipulatorSystem.getInstance().setShooterSpeed(shootspeed);
            DSLog.log(1, "Shooter Speed: " + String.valueOf(shootspeed));
        }
    }
    class ShooterSpeedDown extends ButtonHandler {
        public void pressed() {
            if(shooterJump){
                shootspeed = 0.0;
            } else {
                shootspeed -= .1;
            }
            if (shootspeed >= 1) {
                shootspeed = 1;
            }
            if (shootspeed < 0.3) {
                shootspeed = 0.0;
            }
            System.out.println("shootspeed = " + shootspeed);
            ManipulatorSystem.getInstance().setShooterSpeed(shootspeed);
            DSLog.log(1, "Shooter Speed: " + String.valueOf(shootspeed));
        }
    }

    /**
     * Sets the shooter speed to jump straight to 1.0 and 0.0
     */
    class ShooterSpeedSetJump extends ButtonHandler {
        public void pressed () {
            shooterJump = true;
        }

        public void released () {
            shooterJump = false;
        }

    }
    class AnalogShooterSpeed extends JoystickAxisHandler {
        public void setNewHandleValue(double value) {
            shootspeed = value;
            if (shootspeed >= 1.0) {
                shootspeed = 1.0;
            }
            if (shootspeed <= 0.0) {
                shootspeed = 0.0;
            }
            System.out.println("shootspeed = " + shootspeed);
            ManipulatorSystem.getInstance().setShooterSpeed(shootspeed);
            DSLog.log(1, "Shooter Speed: " + String.valueOf(shootspeed));
        }
    }

    class CameraPositioning extends ButtonHandler {
        TurretPid pos;
        public void pressed() {
            isTargeting = true;
            if (!pos.isEnable()) pos.enable();
            DSLog.log(2, "AUTO-TARGETING");
        }
        public void released() {
            isTargeting = false;
            if (pos.isEnable()) pos.disable();
            DSLog.log(2, "targeting disabled");
        }
        public CameraPositioning() {
            pos = new TurretPid();
            System.out.println("PID Created");
            System.out.println("BOUND");
        }
    }
    
    class PrintStuff extends ButtonHandler
    {
        public void held()
        {
            System.out.println("ramp arm undeploy limit: " + RampArm.getInstance().isFullyUndeployed());
            System.out.println("ramp arm deploy limit:   " + RampArm.getInstance().isFullyDeployed());
            System.out.println("ball arm undeploy limit: " + BallArm.getInstance().isFullyUndeployed());
            System.out.println("ball arm deploy limit:   " + BallArm.getInstance().isFullyDeployed());
            System.out.println("turret pot: " + ManipulatorSystem.getInstance().getTurretRotation());
        }
    }
    class TurretRotation extends JoystickAxisHandler {
        public void setHandleValue(double value) {
            if(!isTargeting){
                //System.out.println("turret speed: " + value);
                ManipulatorSystem.getInstance().setTurretRotationSpeed(value);
            }
        }
        public TurretRotation(){
            this.setDeadBand(.2);
        }
    }

    //channels
    private final int JOYSTICK_LEFT_CHANNEL = 1;
    private final int JOYSTICK_RIGHT_CHANNEL = 2;
    private final int PLAYSTATION_CHANNEL = 3;
    private final int XBOX_CHANNEL = 3;
    //controllers
    private AttackThree left;
    private AttackThree right;
    private PS3Controller ps3;
    private XboxController xbox;
    //stuff
    private boolean raw_tank = true;
    private boolean invert = false;
    private double shootspeed = 0.0;
    private boolean shooterJump = false;
    private boolean isTargeting = false;

    public OperatorSystem() {
        right = new AttackThree(JOYSTICK_RIGHT_CHANNEL);
        left = new AttackThree(JOYSTICK_LEFT_CHANNEL);
        xbox = new XboxController(XBOX_CHANNEL);

        right.bindY(new RightAxisY());
        right.bindB2(new ToggleDrive());

        left.bindY(new LeftAxisY());
        left.bindB2(new CameraPositioning());
        left.bindB10(new PrintStuff());

        xbox.bindAbutton(new LiftBelt());
        //xbox.bindBbutton(new IntakeRollerArm()); //Cow Catcher
        //xbox.bindXbutton(new WheelieBarButton());
        xbox.bindYbutton(new ShooterBelts());
        xbox.bindLeftBumper(new ShooterSpeedDown());
        xbox.bindRightBumper(new ShooterSpeedUp());
        xbox.bindBack(new ManipulatorToggle());
        xbox.bindStart(new ShooterSpeedSetJump());
       
        xbox.bindX(new TurretRotation());
        xbox.bindXrot(new WheelieBar());
        xbox.bindYrot(new AnalogLeadScrew());
        xbox.bindLeftJoystickClick(new CameraPositioning());
        
       
    }

    public void start() {
        right.start();
        left.start();
       // ps3.start();
        xbox.start();
    }

    public void stop() {
        right.stop();
        left.stop();
       // ps3.stop();
        xbox.stop();
        ManipulatorSystem.getInstance().stop();
    }
}
