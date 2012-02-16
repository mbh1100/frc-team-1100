/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author team1100
 */
class RateSource implements PIDSource
{
    Encoder source;

    public double pidGet()
    {
        double rate = source.getRate();
        return rate;
    }

   public RateSource(Encoder e)
   {
       source = e;
   }
}

class RateOutput implements PIDOutput
{
    Jaguar output;
    double adding;

    public void pidWrite(double o)
    {
        adding = adding + o;
        if(adding > 1) {adding = 1;}
        if(adding < -1) {adding = -1;}
        System.out.println("adding is:" + adding);
        output.pidWrite(0);
    }

    public RateOutput(Jaguar j)
    {
        output = j;
    }
}
/**
 *
 * @author
 */
public class RatePid extends edu.wpi.first.wpilibj.PIDController{
    static private final double P = 0.0500;
    static private final double I = 0.0005;
    static private final double D = 0.000000;

public RatePid(Encoder source, Jaguar output){
    super (P,I,D, new RateSource(source), new RateOutput(output));

    this.setOutputRange(-0.1, 0.1);
    this.setInputRange(-30.0, 30.0);
    //robot finish
}
}


