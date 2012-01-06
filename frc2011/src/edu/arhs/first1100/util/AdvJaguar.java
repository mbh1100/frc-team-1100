/**
 * AdvJaguar.java
 *
 * Extends the WPI jaguar class.
 */

package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.Jaguar;

public class AdvJaguar
{
    private boolean polarity;
    private Jaguar j1;
    private Jaguar j2;
    
    /**
     * @param ch
     * @param inverted
     */
    public AdvJaguar(int slot, int ch1, int ch2, boolean inverted)
    {
        j1 = new Jaguar(slot, ch1);
        if(ch2 != 0) j2 = new Jaguar(slot, ch2);

        polarity = inverted;
    }
    
    public AdvJaguar(int slot, int ch1, boolean inverted)
    {
        this(slot, ch1, 0, inverted);
    }
    
    /**
     *
     * @param speed
     */
    public void set(double speed)
    {
        j1.set(polarity ? -speed : speed);
        if(j2 != null) j2.set( polarity ? -speed : speed);
    }

    public double get()
    {
        return j1.get();
    }
}
