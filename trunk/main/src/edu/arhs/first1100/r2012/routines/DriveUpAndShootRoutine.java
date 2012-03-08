/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.Routine;
import edu.arhs.first1100.r2012.pid.TurretPid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class DriveUpAndShootRoutine extends Routine {

    TurretPid turret;

    public DriveUpAndShootRoutine()
    {
        super(1000);
        turret = new TurretPid();
    }

    public void run()
    {
        // TODO: Modify Drive to use encoders
        DriveSystem.getInstance().driveLeft(0.3);
        DriveSystem.getInstance().driveRight(0.3);
        if(!turret.isEnable())turret.enable();
        turret.setSetpoint(0.0);
        ManipulatorSystem.getInstance().setShooterSpeed(0.4);
        Timer.delay(3.1);
        DriveSystem.getInstance().driveLeft(0.0);
        DriveSystem.getInstance().driveRight(0.0);
        ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
        ManipulatorSystem.getInstance().setLeftShooterBelt(-1.0);
        Timer.delay(10.0);
        ManipulatorSystem.getInstance().setShooterSpeed(0.0);
        ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kOff);
        ManipulatorSystem.getInstance().setLeftShooterBelt(0.0);
        if(turret.isEnable())turret.disable();
        setDone();
    }

    public void doCancel() {
        DriveSystem.getInstance().driveLeft(0);
        DriveSystem.getInstance().driveRight(0);
        ManipulatorSystem.getInstance().setShooterSpeed(0.0);
        if(turret.isEnable())turret.disable();
    }

}
