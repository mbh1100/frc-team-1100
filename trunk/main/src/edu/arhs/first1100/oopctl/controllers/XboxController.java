package edu.arhs.first1100.oopctl.controllers;

import edu.arhs.first1100.oopctl.handlers.ButtonHandler;
import edu.arhs.first1100.oopctl.handlers.JoystickAxisHandler;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Joystick;


/*
 * Xbox Controller for 2012
 */
public class XboxController extends SystemBase {

    private int channel;
    private Joystick js;
    private String prefix;
    private JoystickAxis x;
    private JoystickAxis y;
    private JoystickAxis z; //weird
    private JoystickAxis xrot;
    private JoystickAxis yrot;
    private Button b1; //A
    private Button b2; //B
    private Button b3; //X
    private Button b4; //Y
    private Button b5; //Left Bumper
    private Button b6; //Right Bumper
    private Button b7; //Back
    private Button b8; //Start
    private Button b9; //Click left joystick
    private Button b10; //Click right joystick

    public XboxController(int ch) {
        channel = ch;
        prefix = "Xbox[" + channel + "], ";
        js = new Joystick(channel);

        x = new JoystickAxis(js, Joystick.AxisType.kX);
        y = new JoystickAxis(js, Joystick.AxisType.kY);
        z = new JoystickAxis(js, Joystick.AxisType.kZ);
        xrot = new JoystickAxis(js, Joystick.AxisType.kThrottle);
        yrot = new JoystickAxis(js, Joystick.AxisType.kTwist);
        b1 = new Button(js, 1);
        b2 = new Button(js, 2);
        b3 = new Button(js, 3);
        b4 = new Button(js, 4);
        b5 = new Button(js, 5);
        b6 = new Button(js, 6);
        b7 = new Button(js, 7);
        b8 = new Button(js, 8);
        b9 = new Button(js, 9);
        b10 = new Button(js, 10);
    }

    public void bindX(JoystickAxisHandler h) {
        h.setName(prefix + "X-axis");
        x.bind(h);
    }

    public void bindY(JoystickAxisHandler h) {
        h.setName(prefix + "Y-axis");
        y.bind(h);
    }

    public void bindZ(JoystickAxisHandler h) {
        h.setName(prefix + "Z-axis");
        z.bind(h);
    }

    public void bindXrot(JoystickAxisHandler h) {
        h.setName(prefix + "X-rot");
        xrot.bind(h);
    }

    public void bindYrot(JoystickAxisHandler h) {
        h.setName(prefix + "Y-rot");
        yrot.bind(h);
    }

    public void bindAbutton(ButtonHandler h) {
        h.setName(prefix + "A button");
        b1.bind(h);
    }

    public void bindBbutton(ButtonHandler h) {
        h.setName(prefix + "B button");
        b2.bind(h);
    }

    public void bindXbutton(ButtonHandler h) {
        h.setName(prefix + "X button");
        b3.bind(h);
    }

    public void bindYbutton(ButtonHandler h) {
        h.setName(prefix + "Y button");
        b4.bind(h);
    }

    public void bindLeftBumper(ButtonHandler h) {
        h.setName(prefix + "Left Bumper");
        b5.bind(h);
    }

    public void bindRightBumper(ButtonHandler h) {
        h.setName(prefix + "Right Bumper");
        b6.bind(h);
    }

    public void bindBack(ButtonHandler h) {
        h.setName(prefix + "Back button");
        b7.bind(h);
    }

    public void bindStart(ButtonHandler h) {
        h.setName(prefix + "Start button");
        b8.bind(h);
    }

    public void bindLeftJoystickClick(ButtonHandler h) {
        h.setName(prefix + "Left Stick Click");
        b9.bind(h);
    }

    public void bindRightJoystickClick(ButtonHandler h) {
        h.setName(prefix + "Right Stick Click");
        b10.bind(h);
    }

    public void tick() {
        x.update();
        y.update();
        z.update();
        xrot.update();
        yrot.update();
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
    }
}
