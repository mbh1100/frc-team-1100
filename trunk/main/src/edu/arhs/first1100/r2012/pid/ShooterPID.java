/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.r2012.sensors.ShooterSpin;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author team1100
 */
class ShooterPIDSource implements PIDSource {

    public double pidGet() {
        double rate = ShooterSpin.getInstance().getRPM();
        return rate;
    }
}

class ShooterPIDOutput implements PIDOutput {

    double adding;

    public void pidWrite(double o) {
        if (o > 0)
            adding = adding + o;
        else
            adding = adding + o/2;
        
        if (adding > 1) {
            adding = 1;
        }
        if (adding < 0) {
            adding = 0;
        }
        //System.out.println("shooter speed: " + adding);
        ManipulatorSystem.getInstance().setShooterSpeed(adding);
    }
}

/**
 *
 * @author
 */
public class ShooterPID extends edu.wpi.first.wpilibj.PIDController {

    static private final double P = 0.000006;
    static private final double I = 0.000000;
    static private final double D = 0.00000;

    public ShooterPID() {
        super(P, I, D, new ShooterPIDSource(), new ShooterPIDOutput());
        //enable();
    }
    
    public void setRPM(int rpm)
    {
        setSetpoint(rpm);
    }
}