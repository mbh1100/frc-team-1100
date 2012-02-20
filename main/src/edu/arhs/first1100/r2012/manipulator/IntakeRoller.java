/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.manipulator;

import edu.arhs.first1100.r2012.manipulator.ManipulatorSystem;

/**
 *
 * @author team1100
 */
public class IntakeRoller {

    public static final double oRollerSpeed = -0.2;
    public static final double kRollerSpeed = 0.2;

    public void intake() {
        ManipulatorSystem.getInstance().setIntakeRoller(kRollerSpeed);
    }

    public void outtake() {
        ManipulatorSystem.getInstance().setIntakeRoller(oRollerSpeed);
    }
}
