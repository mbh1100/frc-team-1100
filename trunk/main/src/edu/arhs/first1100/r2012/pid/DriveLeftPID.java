/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.arhs.first1100.util.PID;


class DrivelSource implements PIDSource
{
    public double pidGet()
        {
            return 0;
        }
}
/**
 *
 * @author Connor Moroney
 */
class DrivelOutput implements PIDOutput
        {
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux

    }
}

/**
 *This is a generic pid for the left drive motors.
 * @author !Connor
 */
public class DriveLeftPID extends edu.wpi.first.wpilibj.PIDController{
    static private final double P = 0.05;
    static private final double I = 0.01;
    static private final double D = 0.0;

    /**
     * Sets up the constructor for the left drive motors.
     */
    public DriveLeftPID(){
    super (P,I,D, new DrivelSource(), new DrivelOutput());

    }
}
