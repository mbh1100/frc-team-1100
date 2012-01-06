/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class ScoreRoutine extends Routine
{
    public ScoreRoutine()
    {
        super(50);
    }

    public void run()
    {
        ManipulatorSystem ms = ManipulatorSystem.getInstance();

        Log.defcon3(this, "Score Routine start");
        ms.rollersOut();
        ms.setLiftSpeed(0.8);

        Timer.delay(0.8);

        Log.defcon3(this, "Rcore Routine stop");

        ms.rollersStop();
        ms.setLiftSpeed(0.0);

        this.setDone();
    }

    public void doCancel()
    {
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
        ManipulatorSystem.getInstance().rollersStop();
    }
}
