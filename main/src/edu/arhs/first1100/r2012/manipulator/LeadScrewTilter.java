//Used for limit switches on Lead Screw Tilt
package edu.arhs.first1100.r2012.manipulator;

import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class LeadScrewTilter extends SystemBase {
    
    DigitalInput upperswitch;
    DigitalInput lowerswitch;
    private double tiltspeed;
    
    private LeadScrewTilter() {
        
        super(50);
        upperswitch = new DigitalInput(1,2); //FIX slot and channel
        lowerswitch = new DigitalInput(1,2); //FIX slot and channel
    }
    
    public boolean ThreePoint() {
        
        return !upperswitch.get();
    }
    
    public boolean TwoPoint() {
        return !lowerswitch.get();
    }
    public void setspeed(double speed) {
        tiltspeed = speed;
        
    }
    
    public void DeployThreePoint()
    {
        setspeed(0.5);
    }
    public void DeployTwoPoint()
    {
        setspeed(-0.5);
    }
    public void DoNotMove() {
        setspeed(0.0);
        
    }
    public void Stop() {
        tiltspeed = 0;
        super.stop();
        
    }
    public void tick() {
        if(!ThreePoint() && !TwoPoint()){
               ManipulatorSystem.getInstance().setLeadScrewTilt(tiltspeed); 
        }    
        if(tiltspeed < 0 && ThreePoint() || tiltspeed > 0 && TwoPoint())
        {
               ManipulatorSystem.getInstance().setLeadScrewTilt(tiltspeed);
        }
        else{
               ManipulatorSystem.getInstance().setLeadScrewTilt(0.0);
        }
    }
    
    
    
    public static LeadScrewTilter instance = null;
    
    public static LeadScrewTilter getInstance() {
        if(instance == null) {
            instance = new LeadScrewTilter();
            instance.start();
          }
          return instance;
        
        }
    }

 
