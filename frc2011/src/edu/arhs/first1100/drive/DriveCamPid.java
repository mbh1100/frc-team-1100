/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.opctl.OperatorSystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class PowerCamSource implements PIDSource
{
    public double pidGet()
    {
        Log.defcon2(DriveSystem.getInstance(), "Range is " + DriveSystem.getInstance().getRangeValue());
        return DriveSystem.getInstance().getRangeValue();
    }
}

class PowerCamOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        Log.defcon2(DriveSystem.getInstance(), "Using PID to tell DS to drive wheels at " + output);
        DriveSystem.getInstance().setPower(-output);
    }
}

public class DriveCamPid extends PIDController
{
    static private final double P = 0.01;
    static private final double I = 0.00;
    private static final double D = 0.00;
    
    DriveCamPid()
    {
	super(P, I, D, new PowerCamSource(), new PowerCamOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
