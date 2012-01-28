/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.arhs.first1100.r2012.robot;

import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.r2012.routines.Balance;
import edu.arhs.first1100.r2012.routines.VeloTest;
import edu.wpi.first.wpilibj.SimpleRobot;

import edu.arhs.first1100.r2012.camera.CameraSystem;

public class RobotMain extends SimpleRobot {
    CameraSystem c;
     Balance b; 
     //VeloTest v;
    public void robotInit() {
        //Set Loggin Levels
        Log.addClass(RobotMain.class, 3);
        Log.addClass(CameraSystem.class, 1);
        c = new CameraSystem();
        b = new Balance();
        //v = new VeloTest();
        Log.defcon3(this, "Robot Init");

                
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO UNPLUG YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");       
    }
    
    public void autonomous() 
    {
        c.start();
        Log.defcon3(this, "Autonomous Mode Activated");
        
    }

    public void operatorControl() 
    {
        Log.defcon3(this, "Operator Mode Activated");
        Log.dsLog(1, "balancing");
        b.reset();
        //v.reset();
        while(!isDisabled())
        {
            //v.findVelocity();
            b.balanceBall();
            try
            {
                Thread.sleep(10);
            }catch(Exception e){}
        }
        
    }
    
    public void disabled() 
    {
        c.stop();
        Log.defcon3(this, "Robot Disabled");
        b.disable();
    }
}