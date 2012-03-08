package edu.arhs.first1100.r2012.robot.diagnostic;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

public class DiagnosticRobot {

    static boolean disabled = true;

    public static void operator() {
        Jaguar j1 = new Jaguar(1, 1);
        Jaguar j2 = new Jaguar(1, 2);
        Jaguar j3 = new Jaguar(1, 3);
        Jaguar j4 = new Jaguar(1, 4);

        Joystick jstickr = new Joystick(1);
        Joystick jstickl = new Joystick(2);
        disabled = false;

        while (!disabled) {
            j1.set(jstickr.getY());
            j3.set(jstickr.getY());

            j2.set(-jstickl.getY());  //needs to be inverted
            j4.set(-jstickl.getY());  //needs to be inverted
        }
    }

    public static void disable() {
        disabled = true;
    }
}
    //Added by Team1100