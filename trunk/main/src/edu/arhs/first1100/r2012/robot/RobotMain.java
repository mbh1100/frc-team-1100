/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.arhs.first1100.r2012.robot;

import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.r2012.routines.Balance;
import edu.arhs.first1100.r2012.routines.VeloTest;
import edu.arhs.first1100.r2012.routines.JoyGyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;//import edu.arhs.first1100.r2012.routines.CameraTest;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.OperatorControl.OperatorSystem;
import edu.arhs.first1100.r2012.pid.turretpid;
import edu.arhs.first1100.r2012.robot.diagnostic.DiagnosticRobot;
import edu.arhs.first1100.r2012.routines.*;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;


public class RobotMain extends SimpleRobot
{

    CameraSystem c;
    OperatorSystem os;
    MotorEncoder me;
    Joystick jstick;
    Jaguar turretMotor;
    public void robotInit()
    {
        //Set Loggin' Levels
        Log.addClass(RobotMain.class, 3);
        Log.addClass(JoyGyro.class, 3);
        Log.addClass(CameraSystem.class, 1);
        Log.addClass(OperatorSystem.class, 1);

        turretMotor = new Jaguar(1, 1);
        jstick = new Joystick(1);
        c = new CameraSystem();
        os = new OperatorSystem();

        Log.defcon3(this, "Robot Init");
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO ENABLE YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");
    }

    public void autonomous()
    {
        Log.defcon3(this, "Autonomous Mode Activated");
        SystemBase.enableAll();

    }

    public void operatorControl()
    {
        Log.defcon3(this, "Operator Mode Activated\n\n\n\n\n\n\n\n\n");
        SystemBase.enableAll();
        turretpid tp = new turretpid(turretMotor);
        while(!isDisabled())
        {



            if(jstick.getTrigger())
            {
                tp.enable();
            }

            if(!jstick.getTrigger())
            {
                tp.disable();
                System.out.println(jstick.getX()*2);
                turretMotor.set(jstick.getX()*2);  // needs to be Y

            }

        }
        //os.start();
        //AimTargetRoutine aimer = new AimTargetRoutine(50, turretMotor);
        //aimer.start();
        //me.start();
        /*while(true){
            System.out.println(jstick.getAxis(Joystick.AxisType.kY));
            turretMotor.set(jstick.getAxis(Joystick.AxisType.kY));
        }
        *
        */
    }

    public void disabled()
    {
        Log.defcon3(this, "Robot Disabled");
        DiagnosticRobot.disable();
        SystemBase.stopAll();
    }
}

    //Added by Mike Morris
