package edu.arhs.first1100.r2011.autoctl;

import edu.arhs.first1100.util.Routine;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.r2011.manipulator.ManipulatorSystem;

public class SetManipulatorStateRoutine extends Routine
{
    public SetManipulatorStateRoutine(int state)
    {
        super(50);
        ManipulatorSystem.getInstance().setState(state);
        //this.state = state;
    }
    
    public void tick()
    {
        if(ManipulatorSystem.getInstance().getLiftMUXState() == ManipulatorSystem.LIFTMUX_OPERATOR &&/*should be or*/
           Math.abs(ManipulatorSystem.getInstance().getArmPIDError()) <= 2.0)
        {
            Log.defcon2(this, "Stopping routine");
            setDone();
        }
    }
}
