package edu.arhs.first1100.oopctl.controllers;

import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
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
        
        b1  = new Button(js, 1);
        b2  = new Button(js, 2);
        b3  = new Button(js, 3);
        b4  = new Button(js, 4);
        b5  = new Button(js, 5);
        b6  = new Button(js, 6);
        b7  = new Button(js, 7);
        b8  = new Button(js, 8);
        b9  = new Button(js, 9);
        b10 = new Button(js, 10);
        b11 = new Button(js, 11);
        b12 = new Button(js, 12);
    }

    public void bindB1(ButtonHandler h)
    {
        h.setName(prefix+"button 1");
        b1.bind(h);
    }

    public void bindB2(ButtonHandler h)
    {
        h.setName(prefix+"button 2");
        b2.bind(h);
    }

    public void bindB3(ButtonHandler h)
    {
        h.setName(prefix+"button 3");
        b3.bind(h);
    }

    public void bindB4(ButtonHandler h)
    {
        h.setName(prefix+"button 4");
        b4.bind(h);
    }

    public void bindB5(ButtonHandler h)
    {
        h.setName(prefix+"button 5");
        b5.bind(h);
    }

    public void bindB6(ButtonHandler h)
    {
        h.setName(prefix+"button 6");
        b6.bind(h);
    }

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

    public void bindB9(ButtonHandler h)
    {
        h.setName(prefix+"button 9");
        b9.bind(h);
    }

    public void bindB10(ButtonHandler h)
    {
        h.setName(prefix+"button 10");
        b10.bind(h);
    }
    
    public void bindB11 (ButtonHandler h)
    {
        h.setName(prefix+"button 11");
        b11.bind(h);
    }
    
    public void bindB12 (ButtonHandler h)
    {
        h.setName(prefix+"button 12");
        b12.bind(h);
    }
    
    public void tick()
    {
        b1.update();
        b2.update();
        b3.update();
        b4.update();
        b5.update();
        b6.update();
        b7.update();
        b8.update();
        b9.update();
        b10.update();
        b11.update();
        b12.update();
    }
}
