/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.r2012.pid;
import edu.arhs.first1100.r2012.camera.CameraSystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDController;
import edu.arhs.first1100.util.PID;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
/**
 *
 * @author Forrest Owen
 * @improved by Team1100
 */
class TurretSource implements PIDSource
{
    public double pidGet()
    {
        ParticleAnalysisReport p = CameraSystem.getInstance().getHighestParticle();
        if (p == null)
        {
            System.out.println("null particle!");
            return 0;
        }

        System.out.println("Setting motors to: " + -p.center_mass_x_normalized);
        return (-p.center_mass_x_normalized);
    }
}

class TurretOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux

    }
}

public class TurretPid extends edu.wpi.first.wpilibj.PIDController{

    static private final double P = 1.0;
    static private final double I = 0.01;
    static private final double D = 0.0;

/**
 *turret pid constructor
 */
public TurretPid(Jaguar turretMotor){
    super (P,I,D, new TurretSource(), turretMotor);
}}
