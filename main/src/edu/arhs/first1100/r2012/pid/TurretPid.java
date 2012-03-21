/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.pid;

import edu.arhs.first1100.r2012.camera.CameraSystem;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.DSLog;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDController;
import edu.arhs.first1100.util.PID;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author Forrest Owen @improved by Team1100
 */
class TurretSource implements PIDSource {

    public double pidGet() {
        ParticleAnalysisReport p = CameraSystem.getInstance().getParticle();
        if (p == null) {
            return 0.0;
        }

        return p.center_mass_x_normalized;
    }
}

class TurretOutput implements PIDOutput {

    public void pidWrite(double output) {
        ManipulatorSystem.getInstance().setTurretRotationSpeedAuto(-output);
    }
}

public class TurretPid extends edu.wpi.first.wpilibj.PIDController {

    // don't touch these values. Tuned 20/mar/12. mbh
    static private double P = 0.35;
    static private double I = 0.016;
    static private double D = 0.87;

    public void updateP(double newp)
    {
       P = newp;
       this.setPID(P,I,D);
    }
    public void updateI(double newi)
    {
        I=newi;
        this.setPID(P, I, D);
    }
    public void updateD(double newd)
    {
        D=newd;
        this.setPID(P, I, D);
    }

    public TurretPid() {
        super(P, I, D, new TurretSource(), new TurretOutput());
        this.setSetpoint(0.0);
        this.setOutputRange(-1.0, 1.0);
    }
}
