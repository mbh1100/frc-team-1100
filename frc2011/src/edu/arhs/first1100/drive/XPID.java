/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.PID;


public class XPID extends PID
{

    CameraSystem cs;
    DriveSystem ds;

    public XPID(double P, double I, double D)
    {
        super(P,I,D);
    }

    public void tick()
    {
         if (cs.getCenterX() > 0)
         {
             ds.setTankSpeed (0.4, 0.2);
             Log.defcon1(this, "Camera X-PID: camera off center : right");
         }
         if (cs.getCenterX() < 0)
         {
             ds.setTankSpeed (0.2, 0.4);
             Log.defcon1(this, "Camera X-PID: camera off center : left");
         }
         else
         {
             ds.setTankSpeed(0.0, 0.0);
             Log.defcon1(this, "Camera X-PID: camera centered");
         }
    }

}
