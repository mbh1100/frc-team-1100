/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.arhs.first1100.robot;

import edu.wpi.first.wpilibj.SimpleRobot;

import edu.arhs.first1100.autoctl.AutonomousSystem;
import edu.arhs.first1100.autoctl.LiftRoutine;
import edu.arhs.first1100.autoctl.NoCamNoRangeAutonomous;
import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.autoctl.SetManipulatorStateRoutine;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.arhs.first1100.diag.DiagnosticRobot;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.opctl.DriverStationDataFeeder;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class RobotMain extends SimpleRobot
{
    public DigitalInput autonomousModeSwitch;
    private static boolean autonomousWithCamera;
    private DiagnosticRobot diagRobot;
    public Compressor compressor;
    //added by Akshay
    
    public void robotInit()
    {        
        //added by Akshay
        Log.addClass(RobotMain.class, 3);
        Log.addClass(OperatorSystem.class, 4);
        Log.defcon3(this, "Robot Init");
        
        autonomousModeSwitch = new DigitalInput(13);
        autonomousWithCamera = autonomousModeSwitch.get();

        OperatorSystem.getInstance().dsPrint(4, "");
        if(true)
        {
            //Set Logging Levels
            Log.addClass(MinibotSystem.class, 4);
            Log.addClass(OperatorSystem.class, 4);
            Log.addClass(ManipulatorSystem.class, 1 );
            Log.addClass(DriveSystem.class, 1);
            Log.addClass(AutonomousSystem.class, 1);
            Log.addClass(LineSystem.class, 4);
            Log.addClass(SetManipulatorStateRoutine.class, 4);
            Log.addClass(CameraSystem.class, 1);
            Log.addClass(NoCamNoRangeAutonomous.class, 1);
            Log.addClass(LiftRoutine.class, 4);

            
            OperatorSystem.getInstance().setSleep(25);
            AutonomousSystem.getInstance().setSleep(1000);

            DriveSystem.getInstance().setSleep(50);
            ManipulatorSystem.getInstance().setSleep(50);

            CameraSystem.getInstance().setSleep(100);
            LineSystem.getInstance().setSleep(1000);

            MinibotSystem.getInstance().setSleep(500);
        }
        else
        {
            diagRobot = new DiagnosticRobot();
        }

        try
        {
            new DriverStationDataFeeder().sendToLCD("SWITCH IS" + autonomousWithCamera);
        }
        catch(Exception e)
        {
            
        }
        
        compressor = new Compressor(6, 1);
        //compressor.start();
        
        Log.defcon3(this, "+------------------------+");
        Log.defcon3(this, "| USING " + ((autonomousWithCamera) ? "CAMERA     " : "NO CAMERA  ") + "      |");
        Log.defcon3(this, "+------------------------+");
        Log.defcon3(this, "");
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO UNPLUG YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");

        OperatorSystem.getInstance().dsPrint(6, "Autonomous : "+((autonomousWithCamera) ? "" : "NO ") + "CAMERA");
        CameraSystem.getInstance().start();
    }
    
    public void autonomous()
    {
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
        DriveSystem.getInstance().resetGyro();

        
        Log.defcon3(this, "Autonomous Mode Activated");
        if(true)
        {
            OperatorSystem.getInstance().stop();
            AutonomousSystem.getInstance().start();

            DriveSystem.getInstance().start();
            ManipulatorSystem.getInstance().start();

            CameraSystem.getInstance().start();
            LineSystem.getInstance().start();

            MinibotSystem.getInstance().start();
        }
    }

    public void operatorControl()
    {
        Log.defcon3(this, "Operator Mode Activated");
        if(true)
        {
            OperatorSystem.getInstance().start();
            AutonomousSystem.getInstance().stop();
            Routine.disableRoutines();

            DriveSystem.getInstance().start();
            ManipulatorSystem.getInstance().start();

            CameraSystem.getInstance().start();
            //LineSystem.getInstance().start();
            
            MinibotSystem.getInstance().start();
        }
        else
        {
            while(isOperatorControl())
            {
                diagRobot.teleop();
            }
        }
    }
    
    public void disabled()
    {
        Log.defcon3(this, "Robot Disabled");
        if(true)
        {        
            OperatorSystem.getInstance().stop();
            AutonomousSystem.getInstance().stop();
            AutonomousSystem.getInstance().resetWin();
            Routine.disableRoutines();

            DriveSystem.getInstance().stop();
            ManipulatorSystem.getInstance().stop();

            CameraSystem.getInstance().stop();
            LineSystem.getInstance().stop();

            MinibotSystem.getInstance().stop();
        
        }
    }
    public static boolean getSwitch()
    {
        return autonomousWithCamera;
    }
}