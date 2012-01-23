/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.arhs.first1100.r2012.robot;

import edu.arhs.first1100.util.Log;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMain extends SimpleRobot {  
    public void robotInit() {
        //Set Loggin Levels
        Log.addClass(RobotMain.class, 3);
        
        
        Log.defcon3(this, "Robot Init");

                
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO UNPLUG YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");       
    }
    
    public void autonomous() 
    {
        Log.defcon3(this, "Autonomous Mode Activated");
        
    }

    public void operatorControl() 
    {
        Log.defcon3(this, "Operator Mode Activated");
        
    }
    
    public void disabled() 
    {
        Log.defcon3(this, "Robot Disabled");
        
    }
}