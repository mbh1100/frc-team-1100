package edu.arhs.first1100.r2011.autoctl;

import edu.arhs.first1100.util.Routine;
import edu.arhs.first1100.r2011.line.LineSystem;

public class FollowLineRoutine extends Routine
{
    public FollowLineRoutine()
    {
        this(false);
    }
    
    public FollowLineRoutine(boolean side)
    {
        super(50);
        LineSystem.getInstance().followLine(side);
    }
    
    public void tick()
    {
        if(LineSystem.getInstance().isStopped())
        {
            setDone();
        }
    }
}