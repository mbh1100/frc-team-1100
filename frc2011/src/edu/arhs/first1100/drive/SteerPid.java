/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.arhs.first1100.opctl.DriverStationDataFeeder;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * @author markbh
 */
class SteerSource implements PIDSource
{
    public double pidGet()
    {
        Log.defcon1(this, "pigGet():" + CameraSystem.getInstance().getCenterX());
        return CameraSystem.getInstance().getCenterX();
    }
}

class SteerOutput implements PIDOutput
{
    DriverStationDataFeeder dsdf = new DriverStationDataFeeder();

    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        if (!RobotMain.getSwitch())
        {
            DriveSystem.getInstance().setCurve(0);
        }
        else
        {
            //DriveSystem.getInstance().setCurve(0.0);
            DriveSystem.getInstance().setCurve(-output);
        }
        dsdf.sendToLCD("driving PID:" + output);
    }
}

public class SteerPid extends PIDController
{
    static private final double P = 1;
    static private final double I = 0.0;
    static private final double D = 0.0;

    public SteerPid()
    {
	super(P, I, D, new SteerSource(), new SteerOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
