/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.r2012.pid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDController;
import edu.arhs.first1100.util.PID;
/**
 *
 * @author Connor Moroney
 */
class TurretSource implements PIDSource
{
    public double pidGet()
    {
        return 0;
    }
}

class TurretOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux

    }
}
/**
 *Sets up the apature science turret.
 * @author Connor Moroney
 */
public class turretpid extends edu.wpi.first.wpilibj.PIDController{
static private final double P = 0.05;
static private final double I = 0.01;
static private final double D = 0.0;

/**
 *Just your generic pid constructor for a motor that will run a turret on our robot.
 */
public turretpid(){
    super (P,I,D, new TurretSource(), new TurretOutput());
}
}
