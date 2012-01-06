/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class RangeAutonomous extends Routine
{
    private final int A_VALUE = 200; //temp

    private ArmToLimitSwitchRoutine ar = new ArmToLimitSwitchRoutine();
    private ScoreRoutine sr = new ScoreRoutine();
    private LiftRoutine lr = new LiftRoutine(9999); // drive to limit switch

    public RangeAutonomous()
    {
        super(50);
    }

    public void run()
    {
        lr.start();
        ar.start();

        DriveSystem.getInstance().setTankSpeed(0.8, 0.8);
        while(DriveSystem.getInstance().getRangeValue() > A_VALUE && !isCancelled())
        {
            Timer.delay(.05);
        }
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
        lr.waitForDone();
        ar.waitForDone();

        if(!isCancelled()) sr.start();
        sr.waitForDone();

        this.setDone();
    }

    protected void doCancel()
    {
        ar.cancel();
        sr.cancel();
        lr.cancel();
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
    }

}
