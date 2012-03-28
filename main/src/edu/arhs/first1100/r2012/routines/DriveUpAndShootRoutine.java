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
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author team1100
 */
public class DriveUpAndShootRoutine extends Routine {
    
    final int AUTO_PWR_CHANNEL = 1;
    TurretPid turret;

    public DriveUpAndShootRoutine()
    {
        super(1000);
        turret = new TurretPid();
    }

    public void run()
    {
        System.out.println("");
        // TODO: Modify Drive to use encoders
        // Drive forward for a while
        if(DriverStation.getInstance().getDigitalIn(1)){
            DriveSystem.getInstance().driveLeft(-0.3);
            DriveSystem.getInstance().driveRight(-0.3);
            //if(!turret.isEnable())turret.enable();
            //turret.setSetpoint(0.0);
            //move the lead screw up all the way
            //ManipulatorSystem.getInstance().setLeadScrewTilt(-1.0);
            // Spin up the shooter while we're driving
            ManipulatorSystem.getInstance().setShooterSpeed(DriverStation.getInstance().getAnalogIn(AUTO_PWR_CHANNEL));
            Timer.delay(3.1);
            // stop and shoot
            DriveSystem.getInstance().driveLeft(0.0);
            DriveSystem.getInstance().driveRight(0.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
            ManipulatorSystem.getInstance().setMainLiftBelt(1.0);
            ManipulatorSystem.getInstance().setShooterFeedWheels(-1.0);
            // 5 seconds should be long enough to get off a couple of shots
            // Akshay : at some time we could make it wait until the limit switch has been pressed 2 times
            Timer.delay(5.0);
            ManipulatorSystem.getInstance().setShooterSpeed(0.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kOff);
            ManipulatorSystem.getInstance().setMainLiftBelt(0.0);
            ManipulatorSystem.getInstance().setShooterFeedWheels(0.0);
            //if(turret.isEnable())turret.disable();
            ManipulatorSystem.getInstance().setTurretRotationSpeedAuto(0.0);
            // now back up for a few seconds.
            DriveSystem.getInstance().driveLeft(0.3);
            DriveSystem.getInstance().driveRight(0.3);
            Timer.delay(5.0);
            // and stop.
            DriveSystem.getInstance().driveLeft(0.0);
            DriveSystem.getInstance().driveRight(0.0);
        }
        else{
            ManipulatorSystem.getInstance().setShooterSpeed(DriverStation.getInstance().getAnalogIn(2));
            Timer.delay(4);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kForward);
            ManipulatorSystem.getInstance().setMainLiftBelt(1.0);
            ManipulatorSystem.getInstance().setShooterFeedWheels(-1.0);
            Timer.delay(10);
            ManipulatorSystem.getInstance().setShooterSpeed(0.0);
            ManipulatorSystem.getInstance().setNeckBelt(Relay.Value.kOff);
            ManipulatorSystem.getInstance().setMainLiftBelt(0.0);
            ManipulatorSystem.getInstance().setShooterFeedWheels(0.0);
            
        }
        setDone();

    }

    public void doCancel() {
        DriveSystem.getInstance().driveLeft(0);
        DriveSystem.getInstance().driveRight(0);
        ManipulatorSystem.getInstance().setShooterSpeed(0.0);
        if(turret.isEnable())turret.disable();
    }

}
