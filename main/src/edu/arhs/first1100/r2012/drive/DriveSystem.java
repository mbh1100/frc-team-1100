/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.drive;

import edu.arhs.first1100.r2011.camera.CameraSystem;
//import edu.arhs.first1100.r2012.pid.DriveLeftPID;
import edu.arhs.first1100.r2012.pid.DriveRightPID;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Team1100
 */
public class DriveSystem
{
    public Jaguar j1;
    public Jaguar j2;

    private DriveSystem()
    {
        j1 = new Jaguar(1, 1);
        j2 = new Jaguar(2, 1);
    }
    static DriveSystem instance;

    public static DriveSystem getInstance()
    {
        if(instance == null)
        {
            synchronized(DriveSystem.class)
            {
                if (instance == null)
                {
                    instance = new DriveSystem();
                }
            }
        }
        return instance;
    }

    public void driveLeft(double speed)
    {
        j2.set(-speed);
    }
    public void driveRight(double speed)
    {
        j1.set(speed);
    }
}
// new robot has Y cable for PWM so only need 2 Jaguar objects - Aditya N