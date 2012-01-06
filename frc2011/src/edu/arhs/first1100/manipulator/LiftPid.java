

/*
 * Encoder -> Jaguar
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.CameraSystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class LiftSource implements PIDSource
{
    public double pidGet()
    {
        return ManipulatorSystem.getInstance().getLiftEncoder();
    }
}

class LiftOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        //if (Math.abs(output) < 0.1) output = 0.0; // deadband
        ManipulatorSystem.getInstance().setLiftSpeed(-output);
    }
}

public class LiftPid extends PIDController
{
    static private final double P = 0.05;
    static private final double I = 0.005;
    static private final double D = 0.0005;

    LiftPid()
    {
	super(P, I, D, new LiftSource(), new LiftOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
