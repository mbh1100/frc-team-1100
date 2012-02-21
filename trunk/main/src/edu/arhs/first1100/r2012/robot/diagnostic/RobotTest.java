/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.robot.diagnostic;

import edu.arhs.first1100.r2011.drive.DriveSystem;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.r2012.pid.TurretPid;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author team1100
 */
public class RobotTest
{
    Jaguar turretRotation = new Jaguar(1,1);
    //TurretPid tp = new TurretPid(turretRotation);

    Joystick jstick = new Joystick(1);

    public void OperatorControl()
    {
        Log.defcon3(this, "OpCtl");
        if(jstick.getRawButton(1))
        {
            //tp.enable();
            Log.defcon3(this, "turret pid control");
        }
        else
        {
            //tp.disable();
            Log.defcon3(this, "Joystick control");
            turretRotation.set(jstick.getX());
        }

    }
}
