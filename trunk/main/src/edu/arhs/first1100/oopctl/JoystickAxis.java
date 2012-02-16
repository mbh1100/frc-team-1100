package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickAxis
{
    private JoystickAxisHandler jah;
    private int axis;
    private Joystick js;
    double last_value;
    double deadBandWidth;

    public JoystickAxis(Joystick js, int axisId)
    {
        this.axis = axisId;
        this.js = js;
        this.jah = new JoystickAxisHandler();
    }

    public void update()
    {
        double value = deadBand(js.getRawAxis(axis));

        jah.heresYourValue(value);
        if (value != last_value)
        {
            jah.heresYourNewValue(value);
        }

        last_value = value;
    }

    public void bind(JoystickAxisHandler h)
    {
        if (h != null)
            jah = h;
        else
            jah = new JoystickAxisHandler();
    }

    private double deadBand(double in)
    {
        if(Math.abs(in) < jah.getDeadBand())
        {
            return 0;
        }
        return in;
    }
}
