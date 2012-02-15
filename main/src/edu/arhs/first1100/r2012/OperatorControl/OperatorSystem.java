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

class LeftAxisY extends JoystickAxisHandler
{
    public void heresYourValue(double value)
    {
        DriveSystem.getInstance().driveleft(value);
    }
}
class RightAxisY extends JoystickAxisHandler
{
    public void heresYourValue(double value)
    {
        DriveSystem.getInstance().driveright(value);
    }
}
public class OperatorSystem
{
    private final int JOYSTICK_LEFT_CHANNEL = 1;
    private final int JOYSTICK_RIGHT_CHANNEL = 2;

    private AttackThree left;
    private AttackThree right;

    public OperatorSystem()
    {
        left = new AttackThree(JOYSTICK_LEFT_CHANNEL);
        right = new AttackThree(JOYSTICK_RIGHT_CHANNEL);

        left.bindY(new LeftAxisY());
        right.bindY(new RightAxisY());
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