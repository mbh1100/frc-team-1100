/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

/**
 *
 * @author team1100
 */
public class ArmRoutine extends Routine
{
    private int height;

    public ArmRoutine(int height)
    {
        super(50);
        this.height = height;
    }

    public void start()
    {
        super.start();
        ManipulatorSystem.getInstance().setLiftHeight(height);
        Log.defcon2(this, "Routine started");
    }

    public void tick()
    {
        Log.defcon1(this, "tick");

        if(!ManipulatorSystem.getInstance().getLiftLimitSwitch())
        {
            Log.defcon1(this, "switch hit, setdone()");
            ManipulatorSystem.getInstance().setLiftSpeed(0.0);
            setDone();
        }
        else if (ManipulatorSystem.getInstance().getLiftMUXState() != ManipulatorSystem.LIFTMUX_PID)
        {
            Log.defcon1(this, "manip state not PID, setdone()");
            setDone();
        }

    }

    public void doCancel()
    {
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
    }

}

