package edu.arhs.first1100.r2012.manipulator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.util.SystemBase;

/**
 * Keeps a count of how many balls are inside the robot by using limit switches
 * on the intake and turret.
 * @author Team1100
 */
public class BallCounter extends SystemBase
{
    static BallCounter instance;

    private final int MAX_BALLS = 3;

    private DigitalInput topBallSwitch;
    private DigitalInput bottomBallSwitch;

    private boolean topLastState;
    private boolean bottomLastState;

    private int heldBalls;

    private BallCounter()
    {
        topBallSwitch = new DigitalInput(1, 10);
        bottomBallSwitch = new DigitalInput(2, 1);
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
        return true;//(heldBalls < MAX_BALLS);
    }
    /**
     * updates number of balls in robot
     */
    public void tick()
    {


        //if the the bottomSwitch is NOT pressed AND the last state was PRESSED(true)
        if(bottomBallSwitch.get() && bottomLastState){
            //if the main lift belt is moving UP
            if(ManipulatorSystem.getInstance().getMainLiftBelt() >= 0)
                heldBalls++;
            else
                heldBalls--;
        }

        /**
         * Top feeder belt can not go backwards, so we don't need to check
         */
        //if the top is NOT pressed AND the last state was pressed
        if(topBallSwitch.get() && topLastState)
            heldBalls--;

        if(heldBalls < 0)
        {
            //Log.defcon3(this, "Invalid Number of balls: "+heldBalls);
            heldBalls = 0;
        }

        //if(heldBalls > MAX_BALLS)
            //Log.defcon2(this, "Too many balls: "+heldBalls);


        bottomLastState = bottomBallSwitch.get();
        topLastState = topBallSwitch.get();
    }
    public static BallCounter getInstance()
    {
        if(instance == null)
        {
            synchronized(BallCounter.class)
            {
                if(instance == null)
                {
                    instance = new BallCounter();
                }
            }
        }
        return instance;
    }
    public boolean getTopSwitch()
    {
        return topBallSwitch.get();
    }
    public boolean getBottomSwitch()
    {
        return bottomBallSwitch.get();
    }
}
