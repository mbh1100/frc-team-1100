package edu.arhs.first1100.oopctl.controllers;

import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A Playstation3 Controller
 * @author Team1100
 */
public class PS3Controller extends SystemBase
{
    private String prefix;
    private int channel;
    private Joystick js;

    private JoystickAxis ay; //L2
    private JoystickAxis aT; //R2
    private JoystickAxis aX;  // Left analog X
    private JoystickAxis aY;  //Left analog Y

    private Button b1;  //triangle
    private Button b2;  //square
    private Button b3;  //circle
    private Button b4;  //cross
    private Button b5;  //L1
    private Button b6;  //R1
    private Button b7;  //L2
    private Button b8;  //R2
    private Button b9;  //up
    private Button b10; //right
    private Button b11; //down
    private Button b12; //left

    public PS3Controller(int ch)
    {
        channel = ch;
        prefix = "PS3["+channel+"], ";
        js = new Joystick(channel);

        aT = new JoystickAxis(js, Joystick.AxisType.kThrottle); //PS3 R2
        //ay = new JoystickAxis(js, Joystick.AxisType.kY);
        aX = new JoystickAxis(js, Joystick.AxisType.kX);
        aY = new JoystickAxis(js, Joystick.AxisType.kY);
        b1  = new Button(js, 1);
        b2  = new Button(js, 2);
        b3  = new Button(js, 3);
        b4  = new Button(js, 4);
        b5  = new Button(js, 5);
        b6  = new Button(js, 6);
        b7  = new Button(js, 7);
        //b8  = new Button(js, 8);
        b9  = new Button(js, 9);
        b10 = new Button(js, 10);
        b11 = new Button(js, 11);
        b12 = new Button(js, 12);
    }

    public void bindAT(JoystickAxisHandler h)
    {
        h.setName(prefix+"T-axis");
        aT.bind(h);
    }

    public void bindAX(JoystickAxisHandler h)
    {
        h.setName(prefix+"X-axis");
        aX.bind(h);
    }

    public void bindAY(JoystickAxisHandler h)
    {
        h.setName(prefix+"Y-axis");
        aY.bind(h);
    }

    public void bindB_Triangle(ButtonHandler h) {bindB1(h);}
    public void bindB1(ButtonHandler h)
    {
        h.setName(prefix+"button 1");
        b1.bind(h);
    }

    public void bindB_Circle(ButtonHandler h)   {bindB2(h);}
    public void bindB2(ButtonHandler h)
    {
        h.setName(prefix+"button 2");
        b2.bind(h);
    }

    public void bindB_X(ButtonHandler h)        {bindB3(h);}
    public void bindB3(ButtonHandler h)
    {
        h.setName(prefix+"button 3");
        b3.bind(h);
    }

    public void bindB_Square(ButtonHandler h)   {bindB4(h);}
    public void bindB4(ButtonHandler h)
    {
        h.setName(prefix+"button 4");
        b4.bind(h);
    }

    public void bindB_L1(ButtonHandler h)       {bindB5(h);}
    public void bindB5(ButtonHandler h)
    {
        h.setName(prefix+"button 5");
        b5.bind(h);
    }

    public void bindB_R1(ButtonHandler h)       {bindB6(h);}
    public void bindB6(ButtonHandler h)
    {
        h.setName(prefix+"button 6");
        b6.bind(h);
    }

    public void bindB_L2(ButtonHandler h)       {bindB7(h);}
    public void bindB7(ButtonHandler h)
    {
        h.setName(prefix+"button 7");
        b7.bind(h);
    }

    public void bindB8(ButtonHandler h)
    {
        h.setName(prefix+"button 8");
        b8.bind(h);
    }

    public void bindB_DUp(ButtonHandler h)      {bindB9(h);}
    public void bindB9(ButtonHandler h)
    {
        h.setName(prefix+"button 9");
        b9.bind(h);
    }

    public void bindB_DRight(ButtonHandler h)   {bindB10(h);}
    public void bindB10(ButtonHandler h)
    {
        h.setName(prefix+"button 10");
        b10.bind(h);
    }

    public void bindB_DDown(ButtonHandler h)    {bindB11(h);}
    public void bindB11 (ButtonHandler h)
    {
        h.setName(prefix+"button 11");
        b11.bind(h);
    }

    public void bindB_DLeft(ButtonHandler h)    {bindB12(h);}
    public void bindB12 (ButtonHandler h)
    {
        h.setName(prefix+"button 12");
        b12.bind(h);
    }

    //human-readable binding methods

    public void bindA_R2(JoystickAxisHandler h)
    {
        bindAT(h);
    }

    public void bindA_LeftX(JoystickAxisHandler h)
    {
        bindAX(h);
    }

    public void bindA_LeftY(JoystickAxisHandler h)
    {
        bindAY(h);
    }

    public void bindA_L2(JoystickAxisHandler h){
        //impement later
    }


    public void tick()
    {
        aT.update();

        b1.update();
        b2.update();
        b3.update();
        b4.update();
        b5.update();
        b6.update();
        b7.update();
        //b8.update();
        b9.update();
        b10.update();
        b11.update();
        b12.update();
    }
}
