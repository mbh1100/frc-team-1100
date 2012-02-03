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
class CamSourceleft implements PIDSource
{
    CameraSystem cs;
    
    public double pidGet()
    {
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
    
   public CamSourceleft(CameraSystem c){
       cs = c;
   }
}

class CamOutputLeft implements PIDOutput
{
    Jaguar output;
    
    public void pidWrite(double o)
    {
        output.pidWrite(-o);
    }
    
    public CamOutputLeft(Jaguar j){
        output = j;        
    }
}

public class CameraPidLeft extends edu.wpi.first.wpilibj.//<editor-fold defaultstate="collapsed" desc="comment">
        PIDController
//</editor-fold>
{
    static private final double P = 0.004; //was 0.003
    static private final double I = 0.00085;
    static private final double D = 0.0001;

    public CameraPidLeft(CameraSystem source, Jaguar output){
        super (P,I,D, new CamSourceleft(source), new CamOutputLeft(output));
    }
}