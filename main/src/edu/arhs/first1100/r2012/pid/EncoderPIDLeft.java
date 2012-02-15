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
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

class EncoderSourceLeft implements PIDSource
{
    MotorEncoder me;

    public double pidGet()
    {
        double tmp = me.getLeft();
        System.out.println( tmp + "<~~~~~~~~~~~~ Left rate");
           return tmp;
    }
   public EncoderSourceLeft(MotorEncoder m){
       me = m;
   }
}

class EncoderOutputLeft implements PIDOutput
{
    Jaguar output1;
    Jaguar output2;
    double adding = 0.0;

    public void pidWrite(double o)
    {
        System.out.println("left o is: " + o);
        adding = adding + o;
        if(adding > 1) {adding = 1;}
        if(adding < -1) {adding = -1;}
        System.out.println("adding Left is:" + adding);

        output1.pidWrite(adding);
        output2.pidWrite(adding);
    }

    public EncoderOutputLeft(Jaguar j1,Jaguar j2){
        output1 = j1;
        output2 = j2;
    }
}
public class EncoderPIDLeft extends edu.wpi.first.wpilibj.PIDController
{
    static private final double P = 0.0004;
    static private final double I = 0.0000;
    static private final double D = 0.0000;

    public EncoderPIDLeft(MotorEncoder source, Jaguar output1, Jaguar output2){
        super (P,I,D, new EncoderSourceLeft(source), new EncoderOutputLeft(output1, output2),0.1);
    }
}