/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class NoCamNoRangeEncodersRoutine extends Routine
{
    private final double DRIVE_TIME = 3;
    private final int  LIFT_HEIGHT = 2020;
    private final double ARM_HEIGHT = 3;
    
    private ScoreRoutine sr = new ScoreRoutine();
    private LiftRoutine lr = new LiftRoutine(LIFT_HEIGHT);
    
    public NoCamNoRangeEncodersRoutine()
    {
        super(50);

    }

    public void run()
    {
        DriveSystem.getInstance().setTankSpeed(0.5, 0.5);
        lr.start();
        //ManipulatorSystem.getInstance().setArmPosition(ARM_HEIGHT);
        Log.defcon1(this, "No cam or range driving");
        Timer.delay(DRIVE_TIME);
        Log.defcon1(this, "Done driving");
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);

        lr.waitForDone();
        if(!isCancelled()) sr.start();
        sr.waitForDone();

        this.setDone(); //added by alex at 1:21
    }

    protected void doCancel()
    {
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        lr.cancel();
        sr.cancel();
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
    }
}
