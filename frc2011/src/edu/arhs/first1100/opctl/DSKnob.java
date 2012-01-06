package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;

/**
 *
 * @author mark
 */
public class DSKnob
{
    private int m_channel;
    private double m_initVal = 0;
    private double m_lastVal = 0;
    private DriverStation m_ds;
    private boolean m_initialized = false;
    
    /**
     * Represents a knob on the DriverStation
     * @param channel PSoC analogIn channel for this knob.
     */
    public DSKnob(int channel)
    {
        m_ds = DriverStation.getInstance();
        m_channel = channel;
        reset();
    }
    
    /**
     * set the initial value of the knob to the current value.
     */
    public void reset()
    {
        m_initVal = getCurrentValue();
    }
    /**
     * Get the current value of the knob.
     * @return the current value of the knob.
     */
    public double getCurrentValue()
    {
        try
        {
            double val = m_ds.getEnhancedIO().getAnalogIn(m_channel);
            if (val > 0 && val < 5)
            {
                // update m_lastVal. We always return m_lastVal.
                m_lastVal = val;
                if (!m_initialized)
                {
                    m_initialized = true;
                    m_initVal = val;
                }
            }
        }
        catch (EnhancedIOException e)
        {
            //System.out.println("oops");
        }
        return m_lastVal;
    }

    /**
     * Get the initial value of the knob
     * @return the value of the knob when it was constructed or reset
     */
    public double getInitialValue()
    {
        return m_initVal;
    }

    /**
     * Get the change in the knob setting since it was constructed or reset
     * @return the knob value difference.
     */
    public double getDeltaValue()
    {
        // make sure we don't give odd values if we initialize between
        // getCurrentValue and getInitialValue.
        boolean isInit = m_initialized;
        double rval = getCurrentValue() - getInitialValue();
        return isInit? rval : 0;
    }
}