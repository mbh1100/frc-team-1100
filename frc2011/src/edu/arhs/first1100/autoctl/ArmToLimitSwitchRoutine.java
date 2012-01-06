package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

public class ArmToLimitSwitchRoutine extends Routine
{
    boolean stop = false;
    
    public ArmToLimitSwitchRoutine()
    {
        super(50);
    }
    
    public void run()
    {
        ManipulatorSystem.getInstance().setArmSpeed(0.2);
        Timer.delay(3);
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        setDone();
    }
    
    /*
    public void tick()
    {
        if(!stop)
        {
            ManipulatorSystem.getInstance().setArmSpeed(0.20);
            if(!ManipulatorSystem.getInstance().getArmLimitSwitch())
            {
                stop = true;
                ManipulatorSystem.getInstance().setArmSpeed(0.0);
            }
        }
        else
        {
            ManipulatorSystem.getInstance().setArmSpeed(0.0);
        }

        if(stop)
        {
            this.setDone();
        }
    }
     *
     */

    public void doCancel()
    {
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
        stop = true;
    }
}