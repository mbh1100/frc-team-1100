package edu.arhs.first1100.util;

import edu.arhs.first1100.log.Log;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class SystemBase extends Thread
{
    protected int sleepTime = 100;
    
    private boolean stopThread = true;
    private boolean threadStarted = false;
    
    /**
     * Construct the system base
     * @param robot
     * @param sleep
     */
    public SystemBase() { }
    
    /**
     * Start the thread.
     */
    public void start()
    {
        stopThread = false;

        if(!threadStarted)
        {
            super.start();
            threadStarted = true;
        }
        else Log.defcon2(this, "SystemBase thread already started");
    }
    
    /**
     * Stop the thread.
     */
    public void stop()
    {
        stopThread = true;
    }

    /**
     * Run the thread.  Called by start(), do not call directly!
     * If you need to have code run at the initialization of the thread,
     * Put it in the constructor or override the rest() method
     */
    public void run()
    {
        while(true)
        {
            while(!stopThread)
            {
                /*
                try
                {*/

                //Log.defcon1(this, "Looping while stopThread is false");
                
                tick(); // User code
                
                /*}
                catch(Exception e)
                {
                    log("********************************");
                    log("  Fatal Thread Error!");
                    log(e.getMessage());
                    log(e.toString());

                    log("********************************");
                    robot.disabled();
                }*/
                
                Timer.delay(sleepTime / 1000.0);
            }
            
            while(stopThread)
            {
                //Log.defcon1(this, "waiting for stopThread to equal false");
                Timer.delay(0.1);
            }
        }
    }
    
    /**
     * Put your own code here to run.
     *
     * This method is called after every sleep cycle.
     * the delay time is stored in 'sleepTime'.
     */
    public void tick()
    { }

    /**
     * Set the amount of time that the system should sleep
     * @param int time How long the component should sleep in milliseconds
     */
    public void setSleep(int time)
    {
        sleepTime = time;
    }
    
    public synchronized void imDone()
    {
        notify();
    }
}