/*
 * To win at life, press Crtl + W
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.OperatorControl;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Team1100
 */
public class PS3Button
{
    Joystick  js;
    int button_number;
    public PS3Button(Joystick js, int button_number)
    {
        this.button_number = button_number;
        this.js = js;
    }
    public boolean getState()
    {
        return  js.getRawButton(button_number);
    }
}
