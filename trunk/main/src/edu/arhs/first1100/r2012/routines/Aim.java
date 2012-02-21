/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;

import edu.wpi.first.wpilibj.Jaguar;
import edu.arhs.first1100.r2012.camera.CameraSystem;
import edu.arhs.first1100.r2012.pid.TurretPid;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
/**
 *
 * @author team1100
 */
public class Aim {
    ParticleAnalysisReport p;
    Jaguar turretMotor;
    TurretPid tp;
    public Aim(Jaguar turretMotor)
    {
        this.turretMotor = turretMotor;
        tp = new TurretPid(turretMotor);
    }
    public void aimAtTarget()
    {
       tp.enable();
    }
}