package edu.arhs.first1100.r2012.OperatorControl;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.oopctl.PS3Controller;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.arhs.first1100.r2012.routines.*;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
//import edu.arhs.first1100.r2012.routines.CameraTest;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.arhs.first1100.oopctl.AttackThree;
import edu.arhs.first1100.oopctl.ButtonHandler;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.oopctl.JoystickAxisHandler;
import edu.wpi.first.wpilibj.Relay;


public class OperatorSystem
{
    class LeftAxisY extends JoystickAxisHandler
    {
        EncoderPIDLeft l;

        public void getHandleValue(double value)
        {
            if(raw_tank)
            {
                if(l.isEnable()) l.disable();
                DriveSystem.getInstance().driveLeft(value);
            }
            else
            {
                System.out.println("ENCODER DRIVE_____________________________________________________");
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

    class RightAxisY extends JoystickAxisHandler
    {
        EncoderPIDRight r;

        public void getHandleValue(double value)
        {
            //System.out.println("Raw Tank = " + raw_tank);
            if(raw_tank)
            {
                if(r.isEnable()) r.disable();
                //System.out.println("Right Value-------: " + value);
                DriveSystem.getInstance().driveRight(value);
            }
            else
            {
                if(!r.isEnable()) r.enable();
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
    class ToggleDrive extends ButtonHandler
    {
        public void pressed()
        {
            raw_tank = !raw_tank;
        }
    }
    class DriveStraight extends JoystickAxisHandler
    {
        JoystickAxisHandler l;
        JoystickAxisHandler r;

        public void getHandleValue(double value)
        {
            l.getHandleValue(value);
            r.getHandleValue(value);
        }
        DriveStraight()
        {
            l = new RightAxisY();
            r = new LeftAxisY();
        }
    }
    class PS3B1 extends ButtonHandler
    {
        public void pressed()
        {
            System.out.println("B1 Pressed");
        }
    }
    class MainLiftBelt extends ButtonHandler
    {
        public void pressed()
        {
            System.out.println("B2 Pressed");
            if(invert)
                ManipulatorSystem.getInstance().setMainLiftBelt(-.5);
            else
                ManipulatorSystem.getInstance().setMainLiftBelt(.5);
        }
        public void released()
        {
            System.out.println("B2 Released");
                ManipulatorSystem.getInstance().setMainLiftBelt(0);
        }
    }
    class IntakeRoller extends ButtonHandler
    {
        public void pressed()
        {
            System.out.println("B3 Pressed");
            if(invert)
                ManipulatorSystem.getInstance().setIntakeRoller(-0.5);
            else
                ManipulatorSystem.getInstance().setIntakeRoller(0.5);
        }
        public void released()
        {
            System.out.println("B3 Released");
            ManipulatorSystem.getInstance().setIntakeRoller(0);
        }
    }
    class ManuplitatorToggle extends ButtonHandler
    {
        public void pressed()
        {
            invert = !invert;
        }
    }
    class TopLiftBelt extends ButtonHandler
    {
        public void pressed()
        {
            if(invert)
                ManipulatorSystem.getInstance().setTopLiftBelt(-0.5);
            else
                ManipulatorSystem.getInstance().setTopLiftBelt(0.5);
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

    public OperatorSystem()
    {
        left = new AttackThree(JOYSTICK_LEFT_CHANNEL);
        right = new AttackThree(JOYSTICK_RIGHT_CHANNEL);
        ps3 = new PS3Controller(PLAYSTATION_CHANNEL);

        left.bindY(new LeftAxisY());
        right.bindY(new RightAxisY());
        right.bindB2(new ToggleDrive());
        ps3.bindB1(new PS3B1());
        left.bindB2(new IntakeRoller());
        left.bindB3(new MainLiftBelt());
        left.bindB5(new ManuplitatorToggle());
        right.bindB3(new TopLiftBelt());
        right.bindB10(new PrintRate());
    }
    public void start()
    {
        left.start();
        right.start();
        ps3.start();
    }
    public void stop()
    {
        left.stop();
        right.stop();
        ps3.stop();
    }
}