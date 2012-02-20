/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.oopctl.handlers;

import edu.wpi.first.wpilibj.Joystick;

public class Button
{
    private ButtonHandler bh;
    private int button_number;
    private Joystick js;
    boolean last_value;

    public Button(Joystick js, int button_number)
    {
        this.button_number = button_number;
        this.js = js;
        this.bh = new ButtonHandler();
    }

    public void update()
    {
        boolean value = js.getRawButton(button_number);


        if(value)
        {
            bh.held();
        }
        else
        {
            bh.notHeld();
        }

        if(value && !last_value)
        {
            //System.out.println(bh.getName()+ " " + value);
            bh.pressed();
        }

        if(!value && last_value)
        {
            bh.released();
        }

        last_value = value;
    }
    public void bind(ButtonHandler bh)
    {
        if(bh != null) this.bh = bh;
        else this.bh = new ButtonHandler();
    }
}
