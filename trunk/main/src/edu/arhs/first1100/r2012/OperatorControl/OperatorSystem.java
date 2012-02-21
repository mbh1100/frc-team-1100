package edu.arhs.first1100.r2012.OperatorControl;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.oopctl.controllers.PS3Controller;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.arhs.first1100.r2012.routines.*;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
//import edu.arhs.first1100.r2012.routines.CameraTest;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.arhs.first1100.oopctl.controllers.AttackThree;
import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.wpi.first.wpilibj.Relay;


public class OperatorSystem
{
    /**
     * Drives Left Side of the Robot
     */
    class LeftAxisY extends JoystickAxisHandler
    {
        EncoderPIDLeft l;

        public void setHandleValue(double value)
        {
            if(raw_tank)
            {
                if(l.isEnable()) l.disable();
                DriveSystem.getInstance().driveLeft(value);
            }
            else
            {
                Log.defcon2(OperatorSystem.class, "ENCODER DRIVE_____________________________________________________");
                if(!l.isEnable()) l.enable();
                l.setSetpoint(value*100);
            }
        }
        public LeftAxisY()
        {
            l = new EncoderPIDLeft();
            l.setOutputRange(-0.5, 0.5);
            l.enable();
            super.setDeadBand(0.05);
        }
    }

    /**
     * Drives Right Side of the Robot
     */
    class RightAxisY extends JoystickAxisHandler
    {
        EncoderPIDRight r;

        public void setHandleValue(double value)
        {
            if(raw_tank)
            {
                if(r.isEnable()) r.disable();
                DriveSystem.getInstance().driveRight(value);
            }
            else
            {
                if(!r.isEnable())
                        r.enable();

                r.setSetpoint(value*100);
            }
        }

        public RightAxisY ()
        {
            r = new EncoderPIDRight();
            r.setOutputRange(-0.5, 0.5);
            r.enable();
            super.setDeadBand(0.05);
        }
    }

    /**
     * Toggles between Encoder PID tank drive and direct tank drive
     * Bound to Right Joystick, Button 2
     */
    class ToggleDrive extends ButtonHandler
    {
        public void pressed()
        {
            raw_tank = !raw_tank;
        }
    }

    class StandardDrive extends JoystickAxisHandler
    {
        JoystickAxisHandler l;
        JoystickAxisHandler r;

        public void setHandleValue(double value)
        {
            l.setHandleValue(-value);
            r.setHandleValue(-value);
        }

        StandardDrive()
        {
            l = new RightAxisY();
            r = new LeftAxisY();
        }
    }

    /**
     * Sets speed of top feeder belts and neck belt
     * Bound to PS3 Triangle
     */
    class ShooterBelts extends ButtonHandler
    {
        public void pressed()
        {
            ManipulatorSystem.getInstance().setLeftShooterBelt(1.0);
            ManipulatorSystem.getInstance().setRightShooterBelt(1.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
        }

        public void released()
        {
            ManipulatorSystem.getInstance().setLeftShooterBelt(0.0);
            ManipulatorSystem.getInstance().setRightShooterBelt(0.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
        }
    }

    class LeadScrewUp extends ButtonHandler
    {
        private boolean toggle = true;
        public void held()
        {
            if(toggle)
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kForward);
                toggle = !toggle;
            }
            else
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kOff);
                toggle = !toggle;
            }
        }

        public void released()
        {
        }
    }

    class LeadScrewDown extends ButtonHandler
    {
        private boolean toggle = true;

        public void held()
        {
            if(toggle)
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kReverse);
                toggle = !toggle;
            }
            else
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kOff);
                toggle = !toggle;
            }
        }
    }

    /**
     * Control LeadScrew for up/down turret aiming
     * Bound to PS3 Left Analog Y
     */
    class AnalogLeadScrew extends JoystickAxisHandler
    {

        public void setHandleValue(double value)
        {
            System.out.println("AnalogScrew "+getName());
            if(value > 0)
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kForward);
            }
            else if (value < 0)
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kReverse);
            }
            else
            {
                ManipulatorSystem.getInstance().setLeadScrewTilt(Relay.Value.kOff);
            }
        }
    }

    /**
     * Control turret rotation
     * Bound to PS3 Left Analog X
     */
    class AnalogTurretRotation extends JoystickAxisHandler
    {
        public void setHandleValue(double value)
        {
            System.out.println("AnalogTurretRot "+getName());
            ManipulatorSystem.getInstance().setTurretRotation(value);
        }
    }

    /**
     * Used for main lift belt system.
     */
    class LiftBelt extends ButtonHandler
    {
        private boolean toggle = true;
        public void pressed()
        {
            if(toggle)
            {
                // Sets value when pressed and released.
                // Makes main lift negative values when inverted and normal when normal.

                if(invert)
                {
                    ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                    //Intake roller - remove
                    ManipulatorSystem.getInstance().setIntakeRoller(-0.5);
                    toggle = !toggle;
                }
                else
                {
                    ManipulatorSystem.getInstance().setMainLiftBelt(1.0);
                    //Intake roller - remove
                    ManipulatorSystem.getInstance().setIntakeRoller(0.5);
                    toggle = !toggle;
                }
            }
            else
            {
                ManipulatorSystem.getInstance().setMainLiftBelt(0.0);
                //Intake roller - remove
                ManipulatorSystem.getInstance().setIntakeRoller(0.0);
                toggle = !toggle;
            }
        }
    }

    /**
     * Sets intake roller and main belt to pull in balls
     * Bound to PS3 Circle
     */
    class IntakeRoller extends ButtonHandler
    {
        private boolean toggle = true;
        public void pressed()
        {
            if(toggle)
            {
                if(invert)
                {
                    ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                    ManipulatorSystem.getInstance().setIntakeRoller(-0.7);
                    toggle = !toggle;
                }
                else
                {
                    ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                    ManipulatorSystem.getInstance().setIntakeRoller(0.7);
                    toggle = !toggle;
                }
            }
            else
            {
                ManipulatorSystem.getInstance().setIntakeRoller(0);
                toggle = !toggle;
            }
        }
    }

    class ManipulatorToggle extends ButtonHandler
    {
        public void held()
        {
            invert = true;
        }
        public void released()
        {
            invert = false;
        }
    }

    class TopLiftBelt extends ButtonHandler
    {
        public void pressed()
        {
            if(invert)
                ManipulatorSystem.getInstance().setTopLiftBelt(Relay.Value.kReverse);
            else
                ManipulatorSystem.getInstance().setTopLiftBelt(Relay.Value.kForward);
        }
        public void released()
        {
            ManipulatorSystem.getInstance().setTopLiftBelt(Relay.Value.kOff);
        }
    }
    class PrintRate extends ButtonHandler
    {
        public void held()
        {
            double val = MotorEncoder.getInstance().getLeft();
            System.out.println("Get left: " + val);
            val = MotorEncoder.getInstance().getRight();
            System.out.println("Get right: " + val);
        }
    }
    class ShooterSpeedUp extends ButtonHandler
    {
        public void pressed()
        {
            shootspeed += .1;
            if(shootspeed>=1) shootspeed = 1;
            if(shootspeed<=0) shootspeed = 0;
            System.out.println("shootspeed = "+shootspeed);
            ManipulatorSystem.getInstance().setTopShooterWheel(shootspeed);
            ManipulatorSystem.getInstance().setBottomShooterWheel(shootspeed);
        }
    }
    class ShooterSpeedDown extends ButtonHandler
    {
        public void pressed()
        {
            shootspeed -= .1;
            if(shootspeed>=1) shootspeed = 1;
            if(shootspeed<=0) shootspeed = 0;
            System.out.println("shootspeed = "+shootspeed);
            ManipulatorSystem.getInstance().setTopShooterWheel(shootspeed);
            ManipulatorSystem.getInstance().setBottomShooterWheel(shootspeed);
        }
    }
    class AnalogShooterSpeed extends JoystickAxisHandler
    {
        public void setNewHandleValue(double value)
        {
            shootspeed = value;
            if(shootspeed>=1.0) shootspeed = 1.0;
            if(shootspeed<=0.0) shootspeed = 0.0;
            System.out.println("shootspeed = "+shootspeed);
            ManipulatorSystem.getInstance().setTopShooterWheel(shootspeed);
            ManipulatorSystem.getInstance().setBottomShooterWheel(shootspeed);
        }
    }
    class OuterBallRoller extends ButtonHandler
    {
        private boolean toggle = true;
        public void pressed()
        {
            if(toggle)
            {
                ManipulatorSystem.getInstance().setOuterBallRoller(-.7);
                toggle = !toggle;
            }
            else
            {
                ManipulatorSystem.getInstance().setOuterBallRoller(0);
                toggle = !toggle;
            }
        }
    }
    class OuterBallArmDown extends ButtonHandler
    {
        private boolean toggle = true;
        public void pressed()
        {
            if(toggle)
            {
                ManipulatorSystem.getInstance().setOuterBallArm(-.7);
                toggle = !toggle;
            }
            else
            {
                ManipulatorSystem.getInstance().setOuterBallArm(0);
                toggle = !toggle;
            }
        }

    }

    //channels
    private final int JOYSTICK_LEFT_CHANNEL = 1;
    private final int JOYSTICK_RIGHT_CHANNEL = 2;
    private final int PLAYSTATION_CHANNEL = 3;

    //controllers
    private AttackThree left;
    private AttackThree right;
    private PS3Controller ps3;

    //stuff
    private boolean raw_tank = true;
    private boolean invert = true;
    private double shootspeed = 0;

    public OperatorSystem()
    {
        right = new AttackThree(JOYSTICK_RIGHT_CHANNEL);
        left = new AttackThree(JOYSTICK_LEFT_CHANNEL);
        ps3 = new PS3Controller(PLAYSTATION_CHANNEL);

        right.bindY(new RightAxisY());
        right.bindB2(new ToggleDrive());
        right.bindB3(new TopLiftBelt());
        right.bindB10(new PrintRate());

        left.bindY(new LeftAxisY());

        ps3.bindB_L2(new ManipulatorToggle());
        ps3.bindB_Triangle(new ShooterBelts());
        ps3.bindB_Circle(new IntakeRoller());
        ps3.bindB_X(new LiftBelt());
        ps3.bindB_L1(new ShooterSpeedUp());
        ps3.bindB_R1(new ShooterSpeedDown());
        ps3.bindB_DUp(new LeadScrewUp());
        ps3.bindB_DDown(new LeadScrewDown());
        ps3.bindA_R2(new AnalogShooterSpeed());
        ps3.bindB_Circle(new OuterBallRoller());
        ps3.bindB_Square(new OuterBallArmDown());

        //Aiming
        ps3.bindA_LeftX(new AnalogTurretRotation());
        ps3.bindA_LeftY(new AnalogLeadScrew());
    }

    public void start()
    {
        right.start();
        left.start();
        ps3.start();
    }

    public void stop()
    {
        right.stop();
        left.stop();
        ps3.stop();
        ManipulatorSystem.getInstance().stop();
    }
}
