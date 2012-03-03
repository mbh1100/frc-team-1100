/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.autonomous;

import edu.arhs.first1100.r2012.camera.CameraSystem;
import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;
import edu.arhs.first1100.r2012.pid.TurretPid;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Kinect;

/**
 *
 * @author team1100
 */
public class AutonomousSystem extends SystemBase
{
    TurretPid tp;
    private final double SPEED = .85;
    private boolean kinect;
    private static AutonomousSystem instance = null;
    public AutonomousSystem()
    {
        tp = new TurretPid();
        kinect = false;
    }
    public static AutonomousSystem getInstance()
    {
        if(instance == null)
        {
            instance = new AutonomousSystem();
        }

        return instance;
    }

    public void tick()
    {
        System.out.println("Hand: " + Kinect.getInstance().getSkeleton().GetHandRight().getY());
        System.out.println("Head: " + Kinect.getInstance().getSkeleton().GetHead().getY());
        /*if(kinect)
        {
            ManipulatorSystem.getInstance().setTopShooterWheel(SPEED);
            ManipulatorSystem.getInstance().setBottomShooterWheel(SPEED);

            if(Math.abs(CameraSystem.getInstance().getParticle().center_mass_x_normalized) < .015 && Kinect.getInstance().getSkeleton().GetHandRight().getY() > Kinect.getInstance().getSkeleton().GetHead().getY())
            {
                ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                ManipulatorSystem.getInstance().setIntakeRoller(0.7);
                ManipulatorSystem.getInstance().setOuterBallRoller(0.7);
            }
            else
            {
                ManipulatorSystem.getInstance().setMainLiftBelt(0);
                ManipulatorSystem.getInstance().setIntakeRoller(0);
                ManipulatorSystem.getInstance().setOuterBallRoller(0);
            }
        }
        else{
            tp.enable();

            ManipulatorSystem.getInstance().setTopShooterWheel(SPEED);
            ManipulatorSystem.getInstance().setBottomShooterWheel(SPEED);
            if(Math.abs(CameraSystem.getInstance().getParticle().center_mass_x_normalized) < .015)
            {
                ManipulatorSystem.getInstance().setMainLiftBelt(-1.0);
                ManipulatorSystem.getInstance().setIntakeRoller(0.7);
                ManipulatorSystem.getInstance().setOuterBallRoller(0.7);
            }
            else
            {
                ManipulatorSystem.getInstance().setMainLiftBelt(0);
                ManipulatorSystem.getInstance().setIntakeRoller(0);
                ManipulatorSystem.getInstance().setOuterBallRoller(0);
            }
        }
        *
        */
    }
}
