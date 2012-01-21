/**
 * Averager.java
 *
 * An Averager gives the average value of an array of doubles that you
 * can feed one at a time.  With each feed, the oldest value is dropped.
 * 
 */

package edu.arhs.first1100.util;

public class Averager
{
    private double avg = 0; //average
    private int magnitude = 1;
    private double data[];
    private int index;
    private boolean avgOff = false;
    private int size;

    
    /**
     * Make a new Averager object.
     * An Averager gives the average value of an array of doubles that you
     * can feed one at a time.  With each feed, the oldest value is dropped.
     * @param sampleSize The size of the array
     */
    public Averager(int sampleSize)
    {
        this(sampleSize, 0.0);
    }
    
     /**
     * Make a new Averager object.
     * An Averager gives the average value of an array of doubles that you
     * can feed one at a time.  With each feed, the oldest value is dropped.
     *
     * @param sampleSize The size of the array
     * @param starting The array will be filled with this value instead of 0.0
     */
    public Averager(int sampleSize, double starting)
    {
        size = sampleSize;
        avg = starting;
        data = new double[size];
        for (int i = 0; i < size; i++) data[i] = starting;
    }

    /**
     * Add a value to the array.  Oldest value is dropped.
     * @param value
     */
    public void feed(double value)
    {
        index = (index+1)%data.length;
        avg = 0;
        data[index] = value;
        for (int i = 0; i < data.length; i++) avg += data[i]/data.length;
    }
    
    /**
     * Return the average of the values in the array
     * @return the average
     */
    public double get()
    {
        return avg;
    }

    /**
     * Toggles whether the Averager is on or off.
     */
    public void toggle()
    {
        avgOff = !avgOff;
        if (avgOff)
        {
            data = new double[1];
            data[0] = avg;
        }
        else
        {
            data = new double[size];
            for (int i = 0; i<size;i++) data[i] = avg;
        }
    }
}

