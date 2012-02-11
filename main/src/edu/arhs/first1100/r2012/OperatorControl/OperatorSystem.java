/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.OperatorControl;
import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
import edu.arhs.first1100.r2012.routines.CameraTest;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
/**
 *
 * @author Ryan
 */
public class OperatorSystem extends SystemBase{


    private final int JOYSTICK_LEFT = 1;
    private final int JOYSTICK_RIGHT = 2;


    Joystick jstick;
    Joystick jsticktwo;

    DriveSystem ds;
    CameraTest ct;
    MotorEncoder me;

    public OperatorSystem()
    {
        jstick = new Joystick(JOYSTICK_LEFT);
        jsticktwo = new Joystick(JOYSTICK_RIGHT);

        ds = DriveSystem.getInstance();
        me = new MotorEncoder();
        me.start();
    }
    public void tick()
    {
        ds.driveleft(jstick.getY());
        ds.driveright(-jsticktwo.getY());
        Log.defcon1(this, me.getLeft()+ " <Left--------Right> "+ me.getRight());

        /*if(jstick.getTop())
        {
            ct.cameraMove();
        }
         */
    }
}

    //Added by Ryan
