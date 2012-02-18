package edu.arhs.first1100.r2012.OperatorControl;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.arhs.first1100.r2012.routines.AimTargetRoutine;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
//import edu.arhs.first1100.r2012.routines.CameraTest;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.arhs.first1100.oopctl.AttackThree;
import edu.arhs.first1100.oopctl.ButtonHandler;
import edu.arhs.first1100.oopctl.JoystickAxisHandler;


public class OperatorSystem
{
    class LeftAxisY extends JoystickAxisHandler
    {
        EncoderPIDLeft l;

        public void heresYourValue(double value)
        {
            if(raw_tank)
            {
                if(l.isEnable()) l.disable();
                DriveSystem.getInstance().driveLeft(value);
            }
            else
            {
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

        public void heresYourValue(double value)
        {
            System.out.println("Raw Tank = " + raw_tank);
            if(raw_tank)
            {
                if(r.isEnable()) r.disable();
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
    class DriveStraight extends JoystickAxisHandler
    {
        JoystickAxisHandler l;
        JoystickAxisHandler r;

        public void heresYourValue(double value)
        {
            l.heresYourValue(value);
            r.heresYourValue(value);
        }
        DriveStraight()
        {
            l = new RightAxisY();
            r = new LeftAxisY();
        }
    }
    class RightB2 extends ButtonHandler
    {
        public void pressed()
        {
            raw_tank = !raw_tank;
        }
    }

    private final int JOYSTICK_LEFT_CHANNEL = 1;
    private final int JOYSTICK_RIGHT_CHANNEL = 2;

    private AttackThree left;
    private AttackThree right;

    private boolean raw_tank = false;

    public OperatorSystem()
    {
        left = new AttackThree(JOYSTICK_LEFT_CHANNEL);
        right = new AttackThree(JOYSTICK_RIGHT_CHANNEL);

        left.bindY(new LeftAxisY());
        right.bindY(new RightAxisY());
        right.bindB2(new RightB2());
    }
    public void start()
    {
        left.start();
        right.start();
    }
    public void stop()
    {
        left.stop();
        right.stop();
    }
}