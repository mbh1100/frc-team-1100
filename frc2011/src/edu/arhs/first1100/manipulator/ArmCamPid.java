/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.drive.*;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class ArmCamSource implements PIDSource
{
    public double pidGet()
    {
        return CameraSystem.getInstance().getBiggestParticle().particleArea;
    }
}

class ArmCamOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // if we're horizontal and still not at the target, we're not going to
        // get closer by moving further.
        if (output < 0 && ManipulatorSystem.getInstance().getArmEncoder() > 400 ||
            output > 0 && ManipulatorSystem.getInstance().getArmEncoder() < 10)
        {
            Log.defcon2(this, "Arm Camera PID at limit.");
            ManipulatorSystem.getInstance().setArmSpeed(0.0);
        }
        Log.defcon2(this, "Using PID to tell DS to drive wheels at " + output);
        ManipulatorSystem.getInstance().setArmSpeed(output);
    }
}

public class ArmCamPid extends PIDController
{
    static private final double P = 0.1;
    static private final double I = 0.01;
    private static final double D = 0.0;
    
    ArmCamPid()
    {
	super(P, I, D, new ArmCamSource(), new ArmCamOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
