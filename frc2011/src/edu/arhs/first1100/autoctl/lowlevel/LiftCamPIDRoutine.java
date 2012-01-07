package edu.arhs.first1100.autoctl.lowlevel;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

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