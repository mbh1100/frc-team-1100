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
    
    public void pidWrite(double o)
    {
        output.pidWrite(-o);
    }
    
    public RateOutput(Jaguar j){
        output = j;        
    }
}
/**
 *
 * @author Alex D. Bitch
 */
public class RatePid extends edu.wpi.first.wpilibj.PIDController{
    static private final double P = 0.05;
    static private final double I = 0.0008;
    static private final double D = 0.0001;

public RatePid(Encoder source, Jaguar output){
    super (P,I,D, new RateSource(source), new RateOutput(output));
    //robot finish
}
}


