/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl.lowlevel;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class WristUpRoutine extends Routine
{

    public WristUpRoutine()
    {
        super(100);
    }
    public void run()
    {
        ManipulatorSystem.getInstance().wristUp();
        Timer.delay(1);
        this.setDone();
    }
}
