/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.camera;

import edu.wpi.first.wpilibj.PIDSource;
import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author team1100
 */
public class PidGetter implements PIDSource{
    CameraSystem cs;
    public PidGetter()
    {
        cs =  CameraSystem.getInstance();
    }
    public double pidGet()
    {
        ParticleAnalysisReport biggest = cs.getBiggestParticle();

        if(biggest != null)
        {
            if(biggest.center_mass_x_normalized>0)
            {
               System.out.println("biggest particle is to the right of center");
            }
            else if(biggest.center_mass_x_normalized<0)
            {
               System.out.println("biggest particle is to the left of center");
            }
            return biggest.center_mass_x_normalized;
        }
        System.out.println("There is no biggest particle");
        return 0;
    }
}