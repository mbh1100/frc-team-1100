/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.drive;

import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.arhs.first1100.r2012.pid.DriveLeftPID;
import edu.arhs.first1100.r2012.pid.DriveRightPID;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Ryan
 */
public class DriveSystem
{

    RobotDrive rD;
    Jaguar j1;
    Jaguar j2;
    Jaguar j3;
    Jaguar j4;
    DriveLeftPID dl;
    DriveRightPID dr;

    private DriveSystem()
    {
        j1 = new Jaguar(1, 1);
        j2 = new Jaguar(1, 2);
        j3 = new Jaguar(1, 3);
        j4 = new Jaguar(1, 4);
        //rD = new RobotDrive(j1, j3, j2, j4);
        dl = new DriveLeftPID();
        dr = new DriveRightPID();
    }
    static DriveSystem instance;

    public static DriveSystem getInstance()
    {
        if (instance == null)
        {
            instance = new DriveSystem();
        }
        return instance;
    }
    public void driveleft(double speed)
    {
        j1.set(speed);
        j3.set(speed);
    }
    public void driveright(double speed)
    {
        j2.set(speed);
        j4.set(speed);
    }
    public void tank(double left, double right)
    {
        rD.tankDrive(right, left);
    }
}

    //Added by Ryan