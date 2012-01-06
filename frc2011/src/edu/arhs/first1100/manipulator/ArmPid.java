/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class ArmSource implements PIDSource
{
    public double pidGet()
    {
        return ManipulatorSystem.getInstance().getArmEncoder();
    }
}

class ArmOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        ManipulatorSystem.getInstance().setArmSpeed(output);
    }
}

public class ArmPid extends PIDController
{
    static private final double P = 0.05;
    static private final double I = 0.01;
    static private final double D = 0.0;

    ArmPid()
    {
	super(P, I, D, new ArmSource(), new ArmOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
