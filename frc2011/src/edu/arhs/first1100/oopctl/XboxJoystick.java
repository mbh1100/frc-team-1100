
/*
Future controls:

            Xbox Controls:
    
    Left Analog:    Arm
    Right Analog:   Lift

    Right Bumper:   Pull tube in
    Left Bumper:    Push tube out
    
    A Button:       Rotate tube down
    B Button:       Rotate tube up

    X Button:       Floor state(Lift up, gripper down)
    Y Button:       Default state(Lift fully down, gripper fully up)

    Back + Start:   Deploy minibot arm(the new swing-out one)

*/

package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;
import edu.arhs.first1100.util.SystemBase;

public class XboxJoystick extends SystemBase
{
    private Button button_a;
    
    private Joystick xboxJoystick;
    
    public XboxJoystick(int channel)
    {
        super();

        xboxJoystick = new Joystick(channel);
        button_a = new Button(xboxJoystick, 1);
        
        this.start();
    }

    public void tick()
    {
        // update button a
    }

    public void bindA(ButtonHandler h)
    {
        // set the button_a's handler to h
    }
}