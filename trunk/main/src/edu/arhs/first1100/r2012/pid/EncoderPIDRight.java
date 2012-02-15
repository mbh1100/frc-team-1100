/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.arhs.first1100.r2012.sensors.MotorEncoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

class EncoderSourceRight implements PIDSource
{
    MotorEncoder me;

    public double pidGet()
    {
        double tmp = me.getRight();
        //System.out.println( tmp + "<~~~~~~~~~~~~ Right rate");
        return tmp;
    }
   public EncoderSourceRight(MotorEncoder m){
       me = m;
   }
}

class EncoderOutputRight implements PIDOutput
{
    Jaguar output1;
    Jaguar output2;
    double adding = 0.0;

    public void pidWrite(double o)
    {
        //System.out.println("Right o is: " + o);
        adding = adding + o;
        if(adding > 1) {adding = 1;}
        if(adding < -1) {adding = -1;}
        //System.out.println("adding Right is:" + adding);

        output1.pidWrite(adding);
        output2.pidWrite(adding);
    }

    public EncoderOutputRight(Jaguar j1,Jaguar j2){
        output1 = j1;
        output2 = j2;
    }
}
public class EncoderPIDRight extends edu.wpi.first.wpilibj.PIDController
{
    static private final double P = 0.0004;
    static private final double I = 0.000;
    static private final double D = 0.0000;

    public EncoderPIDRight(MotorEncoder source, Jaguar output1, Jaguar output2){
        super (P,I,D, new EncoderSourceRight(source), new EncoderOutputRight(output1, output2), 0.1);
    }
}

