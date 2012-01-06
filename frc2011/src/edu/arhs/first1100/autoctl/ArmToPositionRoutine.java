/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class ArmToPositionRoutine extends Routine
{
    double encoder = 0;

    public ArmToPositionRoutine(double encoder)
    {
        super(100);
        this.encoder = encoder;
    }

    public void run()
    {
        ManipulatorSystem.getInstance().setArmPosition(encoder);
        while (Math.abs(ManipulatorSystem.getInstance().getArmEncoder() - encoder) > 5)
        {
            Timer.delay(.2);
        }

        this.setDone();
    }
}