/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

class DriverSource implements PIDSource
{
    public double pidGet()
        {
            return 0;
        }
}
/**
 *Implements pid output for the right side motors of our dear robot.
 * @author Connor Moroney
 */
class DriverOutput implements PIDOutput
        {
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux

    }
}

/**
 *Sets up the generic driving pid for the left side of the robot.
 * @author Connor Moroney
 */
public class driverpid extends edu.wpi.first.wpilibj.PIDController{
    static private final double P = 0.05;
    static private final double I = 0.01;
    static private final double D = 0.0;

    /**
     *Just your generic constructor for our pid.
     */
    public driverpid(){
    super (P,I,D, new DriverSource(), new DriverOutput());

    }
}
