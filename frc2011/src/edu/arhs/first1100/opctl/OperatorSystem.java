 package edu.arhs.first1100.opctl;


import edu.arhs.first1100.autoctl.NoCamNoRangeEncodersRoutine;
import edu.arhs.first1100.autoctl.ScoreRoutine;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.SystemBase;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.wpi.first.wpilibj.DriverStation;

public class OperatorSystem extends SystemBase
{
    private static OperatorSystem instance = null;
    
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.
    
    private boolean xboxLeftBumperLastState = false;
    private boolean xboxRightBumperLastState = false;
    
    private boolean stopLift = false;
    private boolean stopArm = false;
    
    private ButtonBox buttonBox;
    
    private ScoreRoutine scoreRoutine;
    private NoCamNoRangeEncodersRoutine autonomousRoutine;
    
    private boolean sensitiveDrive;
    
    public OperatorSystem()
    {
        leftJoystick = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);

        xboxJoystick = new XboxJoystick(3);
        
        buttonBox = new ButtonBox();
    }

    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }


    public void tick()
    {
        /*
         * !!!! A NOTE ON JOYSTICK INVERSION !!!!
         *
         * All systems will take positive values to go forward, negative to go back
         * ex. left wheels: 1.0 forward, -1.0 backward
         *     lift: 1.0 for up, -1.0 for down
         *
         * The driver joysticks are inverted here because they return -1.0 when
         * pushed forward.
         *
         * The xbox does not need inversion because when an axis is pushed
         * forward, it returns 1.0.
         *
         * IF YOU NEED TO INVERT A MOTOR, DO SO IN THE SYSTEM IT IS DECLARED,
         * DON'T INVERT THE JOYSTICK
         *
         */
        
        processDriveControls();
        
        if(xboxJoystick.getStartButton())
        {
            processMinibotControls();
        }
        else
        {
            MinibotSystem.getInstance().stopJaguars();
            
            if(scoreRoutine == null)
            {
                processLiftControls();
                processArmControls();
                processGripperControls();
            }
        }
        
        if(scoreRoutine != null)
        {
            if(scoreRoutine.isDone())
            {
                scoreRoutine = null;
            }
        }
        
        if(autonomousRoutine != null)
        {
            if(autonomousRoutine.isDone())
            {
                autonomousRoutine = null;
            }
        }

        /*
         * Drive modes
         */
        if(leftJoystick.getRawButton(6))
            sensitiveDrive = true;
        else if(leftJoystick.getRawButton(7))
            sensitiveDrive = false;
        
        /*
         * Reset joysticks
         */
        if(leftJoystick.getRawButton(8))  leftJoystick.reset();
        if(rightJoystick.getRawButton(8)) rightJoystick.reset();
    }

    public void processMinibotControls()
    {
        /*
         * Minibot controls
         */
        MinibotSystem minis = MinibotSystem.getInstance();
        
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.20)
        {
            minis.setArmSpeed(xboxJoystick.getLeftStickY());
        }
        else
        {
            minis.setArmSpeed(0.0);
        }

        if(xboxJoystick.getBackButton())
        {
            minis.setBeltSpeed(0.30);
        }
        else
        {
            minis.setBeltSpeed(0.0);
        }
        
    }

    public void processLiftControls()
    {
        /*
         * Lift controls
         */
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.20)
        {
            Log.defcon1(this, "Setting lift speed " + xboxJoystick.getLeftStickY());
            ms.setLiftSpeed(xboxJoystick.getLeftStickY());
            
            // Will make sure lift won't drift when joystick re-enters
            // deadband
            stopLift = true;
        }
        else if(stopLift)
        {
            Log.defcon1(this, "Stopping lift within deadband");
            stopLift = false;
            ms.setLiftSpeed(0.0);
        }
    }

    public void processArmControls()
    {
        /*
         * Arm control
         */
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        
        if(Math.abs(xboxJoystick.getRightStickY()) > 0.20)
        {
            if(xboxJoystick.getRightStickY() > 0.0)
            {
                Log.defcon1(this, "Setting arm speed" + xboxJoystick.getRightStickY()/1.3);
                ms.setArmSpeed(xboxJoystick.getRightStickY()/1.3);
            }
            else
            {
                Log.defcon1(this, "Setting arm speed" + xboxJoystick.getRightStickY()/1.3);
                ms.setArmSpeed(xboxJoystick.getRightStickY()/1.3);
            }
            
            stopArm = true;
        }
        else if(stopArm)
        {
            Log.defcon1(this, "Stopping arm within deadband");
            ms.setArmSpeed(0.0);
            stopArm = false;
        }
    }

    public void processGripperControls()
    {
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        
        if(xboxJoystick.getLeftBumper())
            ms.rollerWristUp();
        
        else if(xboxJoystick.getRightBumper())
            ms.rollerWristDown();
        
        else if(xboxJoystick.getTriggers() > 0.6)
            ms.rollersOut();
        
        else if(xboxJoystick.getTriggers() < -0.6)
            ms.rollersIn();

        else if(xboxJoystick.getXButton() && scoreRoutine == null)
        {
            scoreRoutine = new ScoreRoutine();
            scoreRoutine.start();
        }

        else if(xboxJoystick.getYButton() && autonomousRoutine == null)
        {
            autonomousRoutine = new NoCamNoRangeEncodersRoutine();
            autonomousRoutine.start();
        }
        else
        {
            ms.rollersStop();
            if(autonomousRoutine != null)
                autonomousRoutine.cancel();
        }
    }
    
    public void processDriveControls()
    {
        /*
         * Drive Controls
         *
         * Trim:
         *   Each joystick has a trim axis below buttons 8-9 with a range of 0-1
         *   The drive output is multiplied by this value when setTankSpeed is called.
         *   To avoid accidental moving, the trim will only work if the axis is over 75 percent.
         */
        
        // TANK DRIVE WITH TRIM
        if(sensitiveDrive)
        {
            Log.defcon1(this, "sensitive drive");
            
            double leftValue = limit(-leftJoystick.getStickY());
            double rightValue = limit(-rightJoystick.getStickY());

            if (leftValue >= 0.0)
            {
                leftValue = (leftValue * leftValue);
            }
            else
            {
                leftValue = -(leftValue * leftValue);
            }

            if (rightValue >= 0.0)
            {
                rightValue = (rightValue * rightValue);
            }
            else
            {
                rightValue = -(rightValue * rightValue);
            }

            //setLeftRightMotorOutputs(leftValue, rightValue);
            
            DriveSystem.getInstance().setTankSpeed(
                    leftValue *getLeftTrim(),
                    rightValue*getRightTrim()
                    );
        }
        else
        {
            Log.defcon1(this, "Old drive");
            //Old Drive
            double leftSpeed = -leftJoystick.getStickY() ;
            double rightSpeed = -rightJoystick.getStickY();
            
            DriveSystem.getInstance().setTankSpeed(leftSpeed * getLeftTrim(),
                                                   rightSpeed* getRightTrim());
        }
    }

    public double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }
    
    public ButtonBox getButtonBox()
    {
        return buttonBox;
    }

    public void start()
    {
        Log.defcon2(this, "Resetting joystick centers...");
        super.start();

        rightJoystick.reset();
        leftJoystick.reset();
    }
    
    DriverStationDataFeeder dsFeeder;

   /**
    * Sends a message to the Driver Station
    */
    
    public void dsPrint(int ln, String msg)
    {
        try
        {
            dsFeeder = new DriverStationDataFeeder();

            //if you want to have line 1 on top, 6 on the bottom
            //use line as param instead of ln
            int line =  (6-ln)+1;

            //Send to line # the normal way (console style)
            dsFeeder.toLCDLine(ln, msg);

            //OR:

            //Send to bottom of the screen (line 1)
            dsFeeder.sendToLCD(msg);
        }
        catch(Exception e)
        {
            Log.defcon3(this, "dsPrint failed! :"+e.getMessage());
        }
    }

    public double getLeftTrim()
    {
        return Math.max(-((leftJoystick.getZ() /2)-0.5), 0.20);
    }

    public double getRightTrim()
    {
        return Math.max(-((rightJoystick.getZ() /2)-0.5), 0.20);
    }


}