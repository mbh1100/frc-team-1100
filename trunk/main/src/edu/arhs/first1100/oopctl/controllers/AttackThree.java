package edu.arhs.first1100.oopctl.controllers;

import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Joystick;

public class AttackThree extends SystemBase{

    private String prefix;

    private int channel;
    private Joystick js;
    private JoystickAxis x;
    private JoystickAxis y;
    private JoystickAxis z;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;

    public AttackThree (int ch){

        channel = ch;
        prefix = "Attack3["+channel+"], ";

        js  = new Joystick(ch);

        x  = new JoystickAxis(js, Joystick.AxisType.kX);
        y  = new JoystickAxis(js, Joystick.AxisType.kY);
        z  = new JoystickAxis(js, Joystick.AxisType.kZ);

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
    }

    public void bindX(JoystickAxisHandler h)
    {
        String name = "Attack 3 " +channel+ ", X Axis";
        h.setName(prefix+"X-axis");
        x.bind(h);
    }

    public void bindY(JoystickAxisHandler h)
    {
        String name = "Attack 3 " + channel + ", Y Axis";
        h.setName(name);
        y.bind(h);
    }

    public void bindZ(JoystickAxisHandler h)
    {
        String name = "Attack 3 " + channel + ", X Axis";
        h.setName(name);
        z.bind(h);
    }

    public void bindB1(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 1";
        h.setName(name);
        b1.bind(h);
    }

    public void bindB2(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 2";
        h.setName(name);
        b2.bind(h);
    }

    public void bindB3(ButtonHandler h)
    {
        String name = "Attack3 " + channel + ", button 3";
        h.setName(name);
        b3.bind(h);
    }

    public void bindB4(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 4";
        h.setName(name);
        b4.bind(h);
    }

    public void bindB5(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 5";
        h.setName(name);
        b5.bind(h);
    }

    public void bindB6(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 6";
        h.setName(name);
        b6.bind(h);
    }

    public void bindB7(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 7";
        h.setName(name);
        b7.bind(h);
    }

    public void bindB8(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 8";
        h.setName(name);
        b8.bind(h);
    }

    public void bindB9(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 9";
        h.setName(name);
        b9.bind(h);
    }

    public void bindB10(ButtonHandler h)
    {
        String name = "Attack 3 " + channel + ", button 10";
        h.setName(name);
        b10.bind(h);
    }

    public void bindB11 (ButtonHandler h)
    {
        String name = "Attack 3 " + channel+ "' button 11";
        h.setName(name);
        b11.bind(h);
    }

    public void tick()
    {
        x.update();
        y.update();
        z.update();
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
    }
}

