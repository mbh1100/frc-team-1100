/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

/**
 *
 * @author Joed
 */
public class MyController extends Controller {
    private Jaguar drive;
    private Jaguar turn;
    private Joystick fourwayJS;
    private Joystick leftJS;
    private Joystick rightJS;
    private Ultrasonic us;
    private Ultrasonic us2;
    
    @Override
    public void init() {
        drive = new Jaguar(1);
        turn = new Jaguar(2);
        fourwayJS = new Joystick(0);
        leftJS = new Joystick(1);
        rightJS = new Joystick(2);
        us = new Ultrasonic(3, 4);
        us2 = new Ultrasonic(5, 6);
    }

    public void tick() {
        drive.set(leftJS.getY());
        turn.set(rightJS.getY());
//        System.out.println(us.getRange() + " - " + us2.getRange());
    }
    /*
    @Override
    public void run() {
        drive.set(1.0);
        wait(1000);
        drive.set(0.0);
        wait(1000);
        drive.set(1.0);
        wait(1000);
        drive.set(0.0);
    }
    */
}
