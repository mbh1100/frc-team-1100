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
public class DriveForwardWithRangeRoutine extends Routine
{
    int range = 0;

    public DriveForwardWithRangeRoutine(int r)
    {
        super(500);
        range = r;
    }
    
    public void run()
    {
         DriveSystem ds = DriveSystem.getInstance();
         int count = 0;

         while(!isCancelled())
         {
             //if (ds.getRangeValue() < 50) ds.setI();
             if (ds.getRangeValue() < (range + 10) )
             {
                count++;
             }
             else
             {
                count = 0;
             }
             if (count > 5)
             {
                 break;
             }
             Timer.delay(0.05);
         }

         /*{
             Timer.delay(0.05);
             ds.setTankSpeed(0.5, 0.5);
         }*/
         DriveSystem.getInstance().disablePids();
         ds.setTankSpeed(0.0, 0.0);
         this.setDone();
    }

    public void start()
    {
        DriveSystem ds = DriveSystem.getInstance();
        ds.driveByCamera(range);
        super.start();
    }

    public void doCancel()
    {
        DriveSystem.getInstance().disablePids();
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
    }

}
