/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.arhs.first1100.r2012.robot;

import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.r2012.routines.Balance;
import edu.arhs.first1100.r2012.routines.VeloTest;
import edu.arhs.first1100.r2012.routines.JoyGyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;

//import edu.arhs.first1100.r2012.camera.CameraSystem;

public class RobotMain extends SimpleRobot {
    
    static final int JOYSTICK_LEFT = 1;
    static final int JOYSTICK_RIGHT = 2;
    
    
     CameraSystem c;
     Joystick jstickTwo;
     Jaguar motor;
     Joystick jstick;
     Balance b; 
     JoyGyro j;
     double divy;
     Jaguar j1;
     Jaguar j2;
     Jaguar j3;
     Jaguar j4;
     //VeloTest v;
     
    public void robotInit() {
        //Set Loggin Levels
        Log.addClass(RobotMain.class, 3);
        Log.addClass(JoyGyro.class, 3);
        Log.addClass(CameraSystem.class, 1);
        c = new CameraSystem();
       
        jstickTwo = new Joystick(JOYSTICK_RIGHT);
        jstick = new Joystick(JOYSTICK_LEFT);
        j1 = new Jaguar(1,1);
        j2 = new Jaguar(1,2);
        j3 = new Jaguar(1,3);
        j4 = new Jaguar(1,4);
        
        //v = new VeloTest();
        Log.defcon3(this, "Robot Init");
        motor = new Jaguar(2, 1);
        b = new Balance(motor);
        j = new JoyGyro(motor);
                
        Log.defcon3(this, "+-------------------------------------+");
        Log.defcon3(this, "| IT IS NOW SAFE TO UNPLUG YOUR ROBOT |");
        Log.defcon3(this, "+-------------------------------------+");
    }
    
    public void autonomous() 
    {
        //c.start();
        Log.defcon3(this, "Autonomous Mode Activated");
        
    }

    public void operatorControl() 
    {
        Log.defcon3(this, "Operator Mode Activated");
        Log.dsLog(1, "balancing");

        //v.start();
        
        //v.reset();
        //while(!isDisabled())
        //{
        //    Timer.delay(0.5);
        //    v.findVelocity();
        //}      
        
         
        //b.reset();
        
        while(!isDisabled())
        {
            b.enable();  
             
            j1.set(jstickTwo.getAxis(Joystick.AxisType.kY));
            j3.set(jstickTwo.getAxis(Joystick.AxisType.kY));
            j2.set(-jstick.getAxis(Joystick.AxisType.kY));
            j4.set(-jstick.getAxis(Joystick.AxisType.kY));
            
            
            
            /*
            while(!isDisabled() && jstick.getTrigger(GenericHID.Hand.kLeft )== false)
            {  
                b.balanceBall();
            }
            System.out.println("Resetting");
            b.reset();
            
            b.disable();
            
            while(!isDisabled() && jstick.getTrigger(GenericHID.Hand.kLeft))
            {
               j.overRide();
               j.jstickval();
            }
            */
             
         }
                        
        }
    

    
   public void disabled()
   {
      Log.defcon3(this, "Robot Disabled");
      b.disable();
      //v.disable();
   }
} 