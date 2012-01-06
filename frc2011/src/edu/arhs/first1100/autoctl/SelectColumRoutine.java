package edu.arhs.first1100.autoctl;

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