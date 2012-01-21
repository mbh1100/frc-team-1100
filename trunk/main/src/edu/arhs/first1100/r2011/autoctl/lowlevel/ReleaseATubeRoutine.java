/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.r2011.autoctl.lowlevel;

import edu.arhs.first1100.util.Routine;
import edu.arhs.first1100.r2011.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class ReleaseATubeRoutine extends Routine
{

    public ReleaseATubeRoutine()
    {
        super(100);
    }
    public void run()
    {
        ManipulatorSystem.getInstance().openClaw();
        Timer.delay(.75);
        this.setDone();
    }
}