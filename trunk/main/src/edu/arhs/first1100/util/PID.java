package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author team1100
 */
public class PID
{
    class Input implements PIDSource
    {
        PID pid;
        public double input;
        
        public Input(PID pid)
        {
            this.pid = pid;
        }
        
        public double pidGet()
        {
            return input;
        }
    }

    class Output implements PIDOutput
    {
        PID pid;
        public double output;

        public Output(PID pid)
        {
            this.pid = pid;
        }

        public void pidWrite(double output)
        {
            this.output = output;
        }
    }
    
    private Input pidSource;
    private Output pidOutput;
    
    public void setTarget(double value)
    {
        pid.setSetpoint(value);
    }

    public void feed(double value)
    {
        pidSource.input = value;
    }
    
    public double get()
    {
        return pidOutput.output;
    }

    private PIDController pid;
    public PID(double p, double i, double d)
    {
        pidSource = new Input(this);
        pidOutput = new Output(this);
        
        pid = new PIDController(p, i, d, pidSource, pidOutput);
    }

    public void setOutputRange(double min, double max)
    {
        pid.setOutputRange(min, max);
    }
    
    public double getError()
    {
        return pid.getError();
    }
    
    public void enable()
    {
        pid.enable();
    }
    
    public void disable()
    {
        pid.disable();
    }

    public boolean isEnabled()
    {
        return pid.isEnable();
    }
}
