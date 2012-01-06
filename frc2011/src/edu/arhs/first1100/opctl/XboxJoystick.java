package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;
/**
 *what the xbox controller does
 * @author team1100
 */
public class XboxJoystick extends AdvJoystick
{
    /**
     *what functions the joystick axises control
     */
    private Averager lsX;
    private Averager lsY;
    private Averager rsX;
    private Averager rsY;

    private int sampleSize = 4;
/**
 *what chanel the xbox joystick works with
 * @param ch
 */
    public XboxJoystick(int ch)
    {
        super(ch);
        lsX = new Averager(sampleSize);
        lsY = new Averager(sampleSize);
        rsX = new Averager(sampleSize);
        rsY = new Averager(sampleSize);
    }

    /**
     * @return
     */
    public double getLeftStickX()
    {
        lsX.feed(super.getRawAxis(1));
        return lsX.get();
    }

    /**
     * @return
     */
    public double getLeftStickY()
    {
        lsY.feed(super.getRawAxis(2));
        return lsY.get();
    }

    /**
     * @return
     */
    public double getRightStickX()
    {
        rsX.feed(super.getRawAxis(4));
        return rsX.get();
    }
    
    /**
     * @return
     */
    public double getRightStickY()
    {
        rsY.feed(super.getRawAxis(5));
        return rsY.get();
    }

    /**
     * @return
     */
    public double getDpad()
    {
        return super.getRawAxis(6);
    }

    /*
     * NOTE: Triggers are not independant.
     * 1.0 to 0 is get right trigger, 1.0 to 0 is get left trigger.
     * both triggers can't be read at once as both triggers down or up = 0.0
     */
     
    /**
     * @return right trigger state (0.0 - 1.0)
     */
    public double getRightTrigger()
    {
        return Math.max(super.getRawAxis(3), 0);
    }
    
    /**
     * @return left trigger state (0.0 - 1.0)
     */
    public double getLeftTrigger()
    {
        return -Math.min(super.getRawAxis(3), 0);
    }
    
    /*
     * NOTE: Triggers are not independant.
     * 1.0 is right trigger, -1.0 is left trigger.
     * both triggers can't be read at once as both triggers down or up = 0.0
     */
     
    /**
     * @return the state of both triggers
     */
    public double getTriggers()
    {
        return super.getRawAxis(3);
    }

    /**
     * @return state of the x button
     */
    public boolean getXButton()
    {
        return super.getRawButton(3);
    }

    /**
     * @return state of the y button
     */
    public boolean getYButton()
    {
        return super.getRawButton(4);
    }

    /**
     * @return state of the a button
     */
    public boolean getAButton()
    {
        return super.getRawButton(1);
    }

    /**
     * @return state of the b button
     */
    public boolean getBButton()
    {

        return super.getRawButton(2);
    }

    /**
     * @return state of the left bumper
     */
    public boolean getLeftBumper()
    {
        return super.getRawButton(5);
    }

    /**
     * @return state of the right bumper
     */
    public boolean getRightBumper()
    {
        return super.getRawButton(6);
    }

    /**
     * @return state of the back button
     */
    public boolean getBackButton()
    {
        return super.getRawButton(7);
    }

    /**
     * @return state of the start button
     */
    public boolean getStartButton()
    {
        return super.getRawButton(8);
    }
}
