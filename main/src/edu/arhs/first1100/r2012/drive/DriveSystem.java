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
 * @author Ryan
 */
public class DriveSystem
{
    public Jaguar j1;
    public Jaguar j2;
    public Jaguar j3;
    public Jaguar j4;

    private DriveSystem()
    {
        j1 = new Jaguar(1, 1);
        j2 = new Jaguar(1, 2);
        j3 = new Jaguar(1, 3);
        j4 = new Jaguar(1, 4);
    }
    static DriveSystem instance;

    public static DriveSystem getInstance()
    {
        if (instance == null)
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
        j4.set(-speed);
    }
    public void driveRight(double speed)
    {
        j1.set(speed);
        j3.set(speed);
    }
}