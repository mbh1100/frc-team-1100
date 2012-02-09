package edu.arhs.first1100.oopctl;

public class JoystickAxis
{
   private JoystickAxisHandler jah;
   private int axis;
   private Joystick js;
   boolean last_value;
    
    public JoystickAxis(Joystick js, int axisId)
    {
        this.axis = axisId;
        this.js = js;
    }

    public void update()
    {
        boolean value = js.getRawAxis(axis);

        if (value != last_value)
        {
            jah.heresYourValue(value);
        }

        last_value = value;
    }

    public void bind(ButtonHandler bh)
    {
        this.bh = bh;
    }

}
