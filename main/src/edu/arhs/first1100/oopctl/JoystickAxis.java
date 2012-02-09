package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickAxis
{

    private JoystickAxisHandler jah;
    private int axis;
    private Joystick js;
    double last_value;

    public JoystickAxis(Joystick js, int axisId)
    {
        this.axis = axisId;
        this.js = js;
    }

    public void update()
    {
        double value = js.getRawAxis(axis);

        jah.heresYourValue(value);
        if (value != last_value)
        {
            jah.heresYourNewValue(value);
        }

        last_value = value;
    }

    public void bind(JoystickAxisHandler h)
    {
        jah = h;
    }
}
