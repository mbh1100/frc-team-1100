/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.r2012.pid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

class GyroSource implements PIDSource
{
    Gyro source;
    
    public double pidGet()
    {
        double angle = source.getAngle();
        return angle;
    }
   public GyroSource(Gyro g){
       source = g;              
   }
}

class GyroOutput implements PIDOutput
{
    Jaguar output;
    
    public void pidWrite(double o)
    {
        output.pidWrite(-o);
    }
    
    public GyroOutput(Jaguar j){
        output = j;        
    }
}
/**
 *
 * @author Connor Moroney
 */
public class GyroPid extends edu.wpi.first.wpilibj.//<editor-fold defaultstate="collapsed" desc="comment">
        PIDController
//</editor-fold>
{
    static private final double P = 0.003; //was 3.8
    static private final double I = 0.00085;
    static private final double D = 0.0001;

public GyroPid(Gyro source, Jaguar output){
    super (P,I,D, new GyroSource(source), new GyroOutput(output));
}
}
