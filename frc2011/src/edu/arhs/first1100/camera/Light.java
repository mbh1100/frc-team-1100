package edu.arhs.first1100.camera;

import edu.arhs.first1100.log.Log;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.Timer;
import java.util.TimerTask;

/**
 *sets up the lights and turns on the light when the camera is turned on
 * @author team1100
 */
public class Light
{
    Solenoid solenoid;
    boolean state = false;
    java.util.Timer timer;
    
    /**
     *declares the timeout of the light
     */
    class Timeout extends TimerTask
    {
        Light light;
        
        public Timeout(Light light)
        {
            this.light = light;
        }
       /**
        *says what to do when the light state is false
        */
        public void run()
        {
            light.off();
        }
        
    }

    /**
     *says what chanel the light is on
     * @param ch
     */
    public Light(int ch)
    {
        solenoid = new Solenoid(ch);
    }

    /**
     *toggles the light on and off
     */
    public void toggle()
    {
        state = !state;
        Log.defcon2(this, "Light Toggled");
        solenoid.set(state);
    }

    /**
     * paramiters to turn the light on for
     */
    public void on()
    {
        if(timer != null)
            timer.cancel();
        
        state = true;
        solenoid.set(state);
    }

    /**
     * peramiters to turn the light off for
     */
    public void off()
    {
        state = false;
        solenoid.set(state);
    }
    
    /**
     *what to do if the light is left on for a great leingh of time
     */
    public void onForAWhile()
    {
        on();
        timer = new Timer();
        timer.schedule(new Timeout(this), 2000);
    }
}

