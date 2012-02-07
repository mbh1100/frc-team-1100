/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author team1100
 */
class CamSourceRight implements PIDSource
{
    CameraSystem cs;
    
    public double pidGet()
    {
        if(cs.getBiggestParticle().center_mass_y_normalized>0) 
        {
            System.out.println("biggest particle is above center");
        }
        if(cs.getBiggestParticle().center_mass_y_normalized<0)
        {
            System.out.println("biggest particle is below center");
        }
        if(cs.getBiggestParticle().center_mass_x_normalized>0)
        {
            System.out.println("biggest particle is to the right of center");
            return 1;
        }
        if(cs.getBiggestParticle().center_mass_x_normalized<0)
        {
            System.out.println("biggest particle is to the left of center");
            return -1;
        }
        else return 0;
    }
    
   public CamSourceRight(CameraSystem c){
       cs = c;
   }
}

class CamOutputRight implements PIDOutput
{
    Jaguar output;
    
    public void pidWrite(double o)
    {
        output.pidWrite(-o);
    }
    
    public CamOutputRight(Jaguar j){
        output = j;        
    }
}

public class CameraPidRight extends edu.wpi.first.wpilibj.//<editor-fold defaultstate="collapsed" desc="comment">
        PIDController
//</editor-fold>
{
    static private final double P = 0.004; //was 0.003
    static private final double I = 0.00085;
    static private final double D = 0.0001;

    public CameraPidRight(CameraSystem source, Jaguar output){
        super (P,I,D, new CamSourceRight(source), new CamOutputRight(output));
    }
}