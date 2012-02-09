package edu.arhs.first1100.oopctl;

import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Joystick;

public class WingManExtreme extends SystemBase {

    private int channel;
    private Joystick js;
    private JoystickAxis xaxis;
    private JoystickAxis yaxis;
    private Button a;
    private Button b;
    private Button c;
    private Button x;
    private Button y;
    private Button z;
    private Button lefttrig;
    private Button righttrig;
    private Button start;
    private Button redbutton;

    public WingManExtreme(int ch)
    {
        channel = ch;
        js = new Joystick(ch);
        xaxis = new JoystickAxis(js, 1);
        yaxis = new JoystickAxis(js, 2);
        a = new Button(js, 1);
        b = new Button(js, 2);
        c = new Button(js, 3);
        x = new Button(js, 4);
        y = new Button(js, 5);
        z = new Button(js, 6);
        lefttrig = new Button(js, 7);
        righttrig = new Button(js, 8);
        start = new Button(js, 9);
        redbutton = new Button(js, 10);
    }

    public void bindYaxis(JoystickAxisHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", Y Axis";
        h.setName(name);
        yaxis.bind(h);
    }

    public void bindXaxis(JoystickAxisHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", X Axis";
        h.setName(name);
        xaxis.bind(h);
    }

    public void bindA(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button A";
        h.setName(name);
        a.bind(h);
    }

    public void bindB(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button B";
        h.setName(name);
        b.bind(h);
    }

    public void bindC(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button C";
        h.setName(name);
        c.bind(h);
    }

    public void bindX(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button X";
        h.setName(name);
        x.bind(h);
    }

    public void bindY(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button Y";
        h.setName(name);
        y.bind(h);
    }

    public void bindZ(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button Z";
        h.setName(name);
        z.bind(h);
    }

    public void bindLefttrig(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", left trigger";
        h.setName(name);
        lefttrig.bind(h);
    }

    public void bindRighttrig(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", right trigger";
        h.setName(name);
        righttrig.bind(h);
    }

    public void bindStart(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", button Start";
        h.setName(name);
        start.bind(h);
    }

    public void bindRedbutton(ButtonHandler h)
    {
        String name = "Wingman Extreme channel " + channel + ", red button";
        h.setName(name);
        redbutton.bind(h);
    }

    public void tick()
    {
        a.update();
        b.update();
        c.update();
        x.update();
        y.update();
        z.update();
        start.update();
        redbutton.update();
        righttrig.update();
        lefttrig.update();
        xaxis.update();
        yaxis.update();
    }
}