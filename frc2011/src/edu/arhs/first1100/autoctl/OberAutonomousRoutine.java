/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.LiftCamPIDRoutine;
import edu.arhs.first1100.autoctl.lowlevel.SteerPIDRoutine;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author team1100
 */
public class OberAutonomousRoutine extends Routine
{
    LiftRoutine lr = new LiftRoutine(1700);
    ArmToLimitSwitchRoutine ar = new ArmToLimitSwitchRoutine();
    DriveForwardWithRangeRoutine df = new DriveForwardWithRangeRoutine(35);
    ScoreRoutine sr = new ScoreRoutine();
    boolean scoreCalled = false;
    long startTime;
    
    public OberAutonomousRoutine()
    {
        super(100);
        startTime = System.currentTimeMillis();
    }
    
    public void run()
    {
        if(!isCancelled())lr.start();
        
        
        //Timer.delay(1.0);
        
        if(!isCancelled())df.start();
        
        lr.waitForDone();
        ManipulatorSystem.getInstance().enableLiftCamPID();
        
        df.waitForDone();
        
        //Timer.delay(0.2);

        ManipulatorSystem.getInstance().stopLiftPIDs();
      //  if (!isCancelled()) new LiftRoutine((int)ManipulatorSystem.getInstance().getLiftEncoder()-100).execute();

        if(!isCancelled())ar.execute();
        if(!isCancelled())sr.execute();

        ManipulatorSystem.getInstance().setArmSpeed(-0.5);
        Timer.delay(1.0);
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        
        DriveSystem.getInstance().setTankSpeed(-0.3, -0.3);
        Timer.delay(2);
        DriveSystem.getInstance().setTankSpeed( 0.0,  0.0);
        
        setDone();
    }
    
    public void doCancel()
    {
        lr.cancel();
        sr.cancel();
        ar.cancel();
        df.cancel();
    }
}
