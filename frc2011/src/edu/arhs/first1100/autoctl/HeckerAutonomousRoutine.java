package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class HeckerAutonomousRoutine extends Routine
{

    static final double MIDDLE_PEG_HEIGHT = 720;
    static final double TOP_PEG_HEIGHT = 2000;

    HeckerAutonomousRoutine()
    {
        super(100);
    }

    SetManipulatorStateRoutine smsr;
    
    public void run()
    {
        OperatorSystem.getInstance().dsPrint(6, "!!!AUTONOMOUS MODE RUNNING!!!");

        OperatorSystem op = OperatorSystem.getInstance();
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        DriveSystem ds = DriveSystem.getInstance();
        LineSystem ls = LineSystem.getInstance();
        
        // tube is already grabbed, wrist is down, lift is down, arm is withdrawn

        // put the wrist up
        ms.wristUp();
        
        //new WristUpRoutine().start(); // it should be done long before we care

        OperatorSystem.getInstance().dsPrint(6, "Setting state to default");
        
        // calibrate the lift and arm (shouldn't take long, we should already be here)
        smsr = new SetManipulatorStateRoutine(ManipulatorSystem.STATE_DEFAULT);
        smsr.execute();
        OperatorSystem.getInstance().dsPrint(6, "done.");
        
        /*
        ms.setState(ms.STATE_DEFAULT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR ||
               ms.getArmMUXState() != ms.ARMMUX_OPERATOR)
        {
            Timer.delay(0.1);
        }*/
        
        Timer.delay(0.5);

        OperatorSystem.getInstance().dsPrint(6, "Setting state to top peg");
        // raise the lift to the middle and wait for it to get there.
        smsr = new SetManipulatorStateRoutine(ManipulatorSystem.STATE_TOP_PEG);
        smsr.execute();
        ms.setArmPosition(ms.getArmEncoder());
        OperatorSystem.getInstance().dsPrint(6, "Done.");
        
        /*
        ms.setLiftHeight(MIDDLE_PEG_HEIGHT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }*/

        double speed = DriverStation.getInstance().getAnalogIn(2)/5;
        
        OperatorSystem.getInstance().dsPrint(6, "Driving w/no tracking (Look out!)");
        ds.setTankSpeed(speed, speed);
        Timer.delay(4);
        ds.setTankSpeed(0.0, 0.0);
        
        // enable y-axis steering and z-axis power, get near the peg.
        OperatorSystem.getInstance().dsPrint(6, "Using Z and X PID");
        //ds.driveByCamera();

        OperatorSystem.getInstance().dsPrint(6, "Using Z and X PID/Waiting for the timeout");
        // stop either when the line system sees the 'T', or when the z-axis is done,
        // or if 5 seconds elapses. If we timeout, don't continue.
        int i = 0;
        while (i < 20*5) //Each tick is 50 ms.
        {
            ++i;
            if (ls.getLeft() && ls.getRight() && ls.getCenter())
            {
                Log.defcon1(this, "reached end of line");
                break;
            }
            if (!ds.getDrivePidEnabled())
            {
                Log.defcon1(this, "drive pid reached target");
                break;
            }
        }
        if (i >= 20*5)
        {
            Log.defcon1(this, "timeout looking for goal. Abandoning scoring attempt.");
            OperatorSystem.getInstance().dsPrint(6, "ERROR: Tracking timeout.");
            return;
        }
        
        // raise the lift to the top peg and wait for it to get there
        /*  ALREADY RAISED
        ms.setLiftHeight(TOP_PEG_HEIGHT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }*/

        ds.setTankSpeed(0.0, 0.0);
        OperatorSystem.getInstance().dsPrint(6, "TIMEOUT NEVER REACHED(THAT'S GOOD!)");
        
        // enable lift x-axis tracking
        ms.enableLiftCamPID();
        
        // enable arm z-axis tracking
        ms.enableArmCamPID();

        OperatorSystem.getInstance().dsPrint(6, "MOVING FORWARD TO SCORE TUBE");
        // Nudge forward to score tube
        ds.setTankSpeed(0.5, 0.5);
        Timer.delay(0.5);
        ds.setTankSpeed(0.0, 0.0);
        
        OperatorSystem.getInstance().dsPrint(6, "OPENING CLAW");
        // open claw
        ms.openClaw();
        
        // withdraw arm, wait until done
        /*
        ms.setArmPosition(0.0);
        while (ms.getArmMUXState() != ms.ARMMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // position lift to 0, wait until done
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }*/
        Timer.delay(2);
        
        OperatorSystem.getInstance().dsPrint(6, "BACKING UP");
        ds.setTankSpeed(-0.5, -0.5);
        Timer.delay(2);
        ms.setState(ManipulatorSystem.STATE_DEFAULT);
        Timer.delay(1);
        ds.setTankSpeed(0.0, 0.0);
        
        OperatorSystem.getInstance().dsPrint(6, "DONE WITH AUTONOMOUS!");
        
        // with
        setDone();
    }

    public void stop()
    {
        smsr.setDone();
        Timer.delay(0.1);
        smsr = null;
        
        super.stop();
    }
}
