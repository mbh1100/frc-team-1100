/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.GrabATubeRoutine;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

public class PickUpTubeFromFloorRoutine extends Routine
{
    public PickUpTubeFromFloorRoutine()
    {
        super(100);
    }

    public void run()
    {
        new GrabATubeRoutine().execute();
        
        new SetManipulatorStateRoutine(ManipulatorSystem.STATE_DEFAULT).execute();

        this.setDone();
    }
}
