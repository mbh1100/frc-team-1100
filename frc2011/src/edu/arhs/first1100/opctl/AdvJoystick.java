package edu.arhs.first1100.opctl;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.Averager;

import edu.wpi.first.wpilibj.Joystick;
/**
 *the joystick
 * @author team1100
 */
public class AdvJoystick extends Joystick
{
    private Averager averagerX;
    private Averager averagerY;
    private final int SAMPLE_SIZE = 4;
    private double tareX = 0;
    private double tareY = 0;

    /**
     *says what port the joystick is on
     * @param port
     */
    public AdvJoystick(int port)
    {
        super(port);
        averagerX = new Averager(SAMPLE_SIZE);
        averagerY = new Averager(SAMPLE_SIZE);
    }
    
    /**
     *get the x axis
     * @return
     */
    public double getStickX()
    {
        double output;
        averagerX.feed(super.getRawAxis(1));
        output = Math.max(-1.0, Math.min((averagerX.get()-tareX), 1.0));
        return output;
    }
    
    /**
     *gets the y axis
     * @return
     */
    public double getStickY()
    {
        double output;
        averagerY.feed(super.getRawAxis(2));
        output = Math.max(-1.0, Math.min((averagerY.get()-tareY), 1.0));
        return output;
    }
    
    public double getZAxis()
    {
        return super.getRawAxis(3);
    }
    
    public void toggleAVG(){
        averagerX.toggle();
        averagerY.toggle();
    }

    /**
     * Zeros the joystick
     */
    public void reset()
    {
        tareX = super.getRawAxis(1);
        tareY = super.getRawAxis(2);
        Log.defcon1(this, "tareX : "+tareX);
        Log.defcon1(this, "tareY : "+tareY);
    }
}
