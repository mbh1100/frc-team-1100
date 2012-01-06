/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
public class CamAndRangeAutonomous extends Routine
{
    public final int A_VALUE = 200;
    public CamAndRangeAutonomous()
    {
        super(50);
    }

    public void run()
    {

        DriveSystem.getInstance().setTankSpeed(0.8, 0.8);
        while(DriveSystem.getInstance().getRangeValue() > A_VALUE)
        {
            Timer.delay(.05);
        }
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
    }

}
