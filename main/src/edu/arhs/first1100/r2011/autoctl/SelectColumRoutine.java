package edu.arhs.first1100.r2011.autoctl;

import edu.arhs.first1100.util.Routine;

public class SelectColumRoutine extends Routine
{
    public SelectColumRoutine()
    {
        super(50);
    }

    public void tick()
    {
        // Do something with steer pids
        setDone();
    }
}