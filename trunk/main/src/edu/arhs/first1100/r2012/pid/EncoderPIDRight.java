/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

class EncoderSourceRight implements PIDSource
{
    MotorEncoder me;

    public double pidGet()
    {
        double tmp = me.getRight();
        return tmp;
    }

   public EncoderSourceRight()
   {
       me = MotorEncoder.getInstance();
   }
}

class EncoderOutputRight implements PIDOutput
{
    DriveSystem output;
    double adding = 0.0;

    public void pidWrite(double o)
    {
        adding = adding + o;
        if(adding > 1) {adding = 1;}
        if(adding < -1) {adding = -1;}

        output.driveRight(adding);
    }

    public EncoderOutputRight()
    {
        output = DriveSystem.getInstance();
    }
}

public class EncoderPIDRight extends edu.wpi.first.wpilibj.PIDController
{
    static private final double P = 0.004;
    static private final double I = 0.000;
    static private final double D = 0.0000;

    public EncoderPIDRight()
    {
        super (P,I,D, new EncoderSourceRight(), new EncoderOutputRight());
    }
}

