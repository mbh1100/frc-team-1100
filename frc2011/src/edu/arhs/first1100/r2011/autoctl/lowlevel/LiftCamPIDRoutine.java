package edu.arhs.first1100.r2011.autoctl.lowlevel;

import edu.arhs.first1100.util.Routine;
import edu.arhs.first1100.r2011.manipulator.ManipulatorSystem;

public class LiftCamPIDRoutine extends Routine
{
    public LiftCamPIDRoutine()
    {
        super(100);
    }

    public void run()
    {
        ManipulatorSystem ms = ManipulatorSystem.getInstance();

        ms.enableLiftCamPID();
    }
}