/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;
import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.arhs.first1100.util.Routine;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
/**
 *
 * @author team1100
 */
public class AimTargetRoutine extends Routine
{
    Jaguar turretMotor;
    public AimTargetRoutine(int sleep, Jaguar turretMotor)
    {
        super(sleep);
    }

    public void tick()
    {

        /*
        ParticleAnalysisReport p = CameraSystem.getInstance().getBiggestParticle();
        if (p == null){
            System.out.println("null particle!");
            return;
        }
        System.out.println("aim tick  " + p.center_mass_x_normalized);

        turretMotor.set(-p.center_mass_x_normalized);
        */

    }
}
