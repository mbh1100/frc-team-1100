package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.util.SystemBase;

/**
 * Keeps a count of how many balls are inside the robot by using limit switches
 * on the intake and turret.
 * @author akshay
 */
public class BallCounter extends SystemBase
{
    
    private final int MAX_BALLS = 3;
    
    private DigitalInput topBallSwitch;
    private DigitalInput bottomBallSwitch;
    
    private boolean topLastState;
    private boolean bottomLastState;
    
    private int heldBalls;
    
    public BallCounter(){
        topBallSwitch = new DigitalInput(0);
        bottomBallSwitch = new DigitalInput(0);
    }
    
    /**
     * get the the count of balls in the robot
     * @return number of balls
     */
    public int getBalls()
    {
        return heldBalls;
    }
    
    public boolean canIntake()
    {
        return (heldBalls < MAX_BALLS);
    }
    
    /**
     * updates number of balls in robot
     */
    public void tick()
    {
        
        if(bottomBallSwitch.get() && !bottomLastState)
            heldBalls++;
        
        if(topBallSwitch.get() && !topLastState)
            heldBalls--;
        
        if(heldBalls < 0)
        {
            Log.defcon3(this, "Invalid Number of balls: "+heldBalls);
            heldBalls = 0;
        }      
        
        if(heldBalls > MAX_BALLS)        
            Log.defcon2(this, "Too many balls: "+heldBalls);
        
        bottomLastState = bottomBallSwitch.get();
        topLastState = topBallSwitch.get();
    }    
}
