package edu.arhs.first1100.oopctl;

import edu.arhs.first1100.util.SystemBase;

public class NewOperatorSystem extends SystemBase
{
    private WingMan leftJoystick;
    private WingMan rightJoystick;
    private XboxJoystick xboxGamepad;
    
    public NewOperatorSystem()
    {
        leftJoystick = new WingMan(1);
        rightJoystick = new WingMan(2);
        
        bindControls();
    }

    private static NewOperatorSystem instance = null;
    public static NewOperatorSystem getInstance()
    {
        if(instance == null) instance = new NewOperatorSystem();
        return instance;
    }

    private void bindControls()
    {
        // leftJoystick.bindY(new LiftHandler());
        // xboxGamepad.bindX(new SetManipulatorState(ManipulatorSystem.STATE_DEFAULT));
    }
}