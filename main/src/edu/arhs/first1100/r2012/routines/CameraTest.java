/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;

import edu.arhs.first1100.r2012.pid.RatePid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.arhs.first1100.r2012.pid.CameraPidLeft;
import edu.arhs.first1100.r2012.pid.CameraPidRight;
import edu.arhs.first1100.r2012.camera.CameraSystem;
import edu.arhs.first1100.r2012.camera.PidGetter;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.arhs.first1100.r2012.routines.CamPidDistance;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.wpi.first.wpilibj.Joystick;
//import edu.arhs.first1100.r2012.pid.;

public class CameraTest {

    Joystick jstick;
    CamPidDistance pd;
    CameraPidLeft cL;
    CameraPidRight cR;
    CameraSystem cs;
    PidGetter pg;
    DriveSystem ds;
    edu.wpi.first.wpilibj.Joystick stick;

    public CameraTest(Joystick jstick) {
        this.jstick = jstick;
        ds = DriveSystem.getInstance();
        //pd = new CamPidDistance(cs);
        pg = new PidGetter();
        stick = new edu.wpi.first.wpilibj.Joystick(1);
    }

    public void cameraMove() {
        double power = jstick.getAxis(Joystick.AxisType.kY);
        double curve = -pg.pidGet();
        if (power > 0) curve = -curve;
        ds.drive(power, curve);
    }
}
