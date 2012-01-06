package edu.arhs.first1100.minibot;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;

public class MinibotSystem extends SystemBase
{
    private static MinibotSystem instance = null;

    private final int ARM_UP_POT_VALUE = 180;
    private final int ARM_DOWN_POT_VALUE = 475;
    
    private AdvJaguar armJaguar;
    private AdvJaguar beltJaguar;
    //private DigitalInput beltBackSwitch;
    //private DigitalInput beltFrontSwitch;
    //private DigitalInput guideSwitch;
    //private DigitalInput poleSwitch;
    
    //private AnalogChannel armPOT;
    
    private boolean minibotDeployed;
    
    public MinibotSystem()
    {
        armJaguar = new AdvJaguar(6, 1, true);
        beltJaguar = new AdvJaguar(6, 2, true);
        
        //beltBackSwitch = new DigitalInput(6, 3);
        //beltFrontSwitch = new DigitalInput(6, 4);
        //guideSwitch = new DigitalInput(6, 6);
        //poleSwitch = new DigitalInput(6,7);
        
        //armPOT = new AnalogChannel(1, 2);
    }
    
    public static MinibotSystem getInstance()
    {
        if(instance == null) instance = new MinibotSystem();
        return instance;
    }

    public void tick()
    {
        //Log.defcon1(this, "Pole:  " + !poleSwitch.get());
        //Log.defcon1(this, "Guide: " + !guideSwitch.get());
        
        //Log.defcon1(this, "");
    }
    
    public void setArmSpeed(double speed)
    {
        //Log.defcon1(this, "Setting arm speed..." + !guideSwitch.get());

        /*if(speed > 0.0 && armPOT.getValue() > ARM_DOWN_POT_VALUE)
        {
            Log.defcon1(this, ".....Stopping, too low");
            armJaguar.set(0.0);
        }
        else if(speed < 0.0 && armPOT.getValue() < ARM_UP_POT_VALUE)
        {
            Log.defcon1(this, ".....Stopping, too high");
            armJaguar.set(0.0);
        }
        else
        {
            Log.defcon1(this, "....."+speed);
            armJaguar.set(speed);
        }*/

        armJaguar.set(speed/2);
    }
    
    public void setBeltSpeed(double speed)
    {
        //Log.defcon1(this, "Setting Belt speed..." + !guideSwitch.get());
        //Log.defcon1(this, "....."+speed);
        
        /*
        if(speed > 0)
        {
            beltJaguar.set(speed);
            if(!beltFrontSwitch.get())
                beltJaguar.set(0.0);
        }
        else if(speed < 0)
        {
            beltJaguar.set(speed);
            if(!beltBackSwitch.get())
                beltJaguar.set(0.0);
        }
        else
        {
            beltJaguar.set(0.0);
        }*/

        beltJaguar.set(speed);
    }

    public void stopJaguars()
    {
        beltJaguar.set(0.0);
        armJaguar.set(0.0);
    }
}