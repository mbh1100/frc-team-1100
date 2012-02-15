/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.OperatorControl;
import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.arhs.first1100.r2012.routines.AimTargetRoutine;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
//import edu.arhs.first1100.r2012.routines.CameraTest;
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
    //AimTargetRoutine aim;
    DriveSystem ds;
    //CameraTest ct;
    MotorEncoder me;

    double[] dataleft;
    double[] dataright;
    int index =0;
    EncoderPIDLeft driveleftcontrol;
    EncoderPIDRight driverightcontrol;

    public OperatorSystem()
    {
        jstick = new Joystick(JOYSTICK_LEFT);
        jsticktwo = new Joystick(JOYSTICK_RIGHT);
       // aim = new AimTargetRoutine(1);
        ds = DriveSystem.getInstance();
        me = new MotorEncoder();

        setSleep(10);

        //temp
        dataleft = new double[15];
        dataright = new double[15];
        index = 0;
        for(int i =0; i < 15; i++)
        {
            dataleft[i] = dataright[i] = 0;
        }

        me.start();

        driveleftcontrol = new EncoderPIDLeft(me, ds.j1, ds.j3);
        driverightcontrol = new EncoderPIDRight(me, ds.j2, ds.j4);
        driveleftcontrol.enable();
        driverightcontrol.enable();
        System.out.println("Enabled Both PIDS________________________");
    }
    public void tick()
    {
    }
}

    //Added by Ryan
