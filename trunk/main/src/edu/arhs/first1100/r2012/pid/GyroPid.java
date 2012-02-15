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
    Gyro gyro;
    Gyro gyroTwo;

    public double pidGet()
    {
        double angle = ((gyro.getAngle() + gyroTwo.getAngle()) / 2);
        return angle;
    }
   public GyroSource(Gyro a, Gyro b){
       gyro = a;
       gyroTwo = b;
   }
}

class GyroOutput implements PIDOutput
{
    Jaguar output;

    public void pidWrite(double o)
    {
        output.pidWrite(o);
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
    static private final double P = 0.004; //was 0.003
    static private final double I = 0.00085;
    static private final double D = 0.0001;

    public GyroPid(Gyro source, Jaguar output, Gyro sourceTwo){
        super (P,I,D, new GyroSource(source, sourceTwo), new GyroOutput(output));
    }
}