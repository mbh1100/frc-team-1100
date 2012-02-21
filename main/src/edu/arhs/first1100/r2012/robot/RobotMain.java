/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.arhs.first1100.r2012.robot;

import edu.arhs.first1100.r2012.robot.diagnostic.RobotTest;
import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.r2012.routines.JoyGyro;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.OperatorControl.OperatorSystem;
import edu.arhs.first1100.r2012.manipulator.BallCounter;

public class RobotMain extends SimpleRobot
{
    OperatorSystem os;

    public void robotInit()
    {
        //Set Loggin' Levels
        Log.addClass(RobotMain.class, 3);
        Log.addClass(JoyGyro.class, 4);
        Log.addClass(CameraSystem.class, 3);
        Log.addClass(OperatorSystem.class, 1);

        Log.addClass(RobotTest.class, 3);
        Log.addClass(BallCounter.class, 2);

        os = new OperatorSystem();

        Log.defcon3(this, "Robot Init");
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO ENABLE YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");
    }

    public void autonomous()
    {

        SystemBase.enableAll();
        Log.defcon3(this, "Autonomous Mode Activated");
    }


    public void operatorControl()
    {
        SystemBase.enableAll();
        os.start();
        Log.defcon3(this, "Operator Mode Activated\n\n\n");
    }

    public void disabled()
    {
        os.stop();
        Log.defcon3(this, "Robot Disabled");
        //DiagnosticRobot.disable();
        SystemBase.stopAll();
    }
}
