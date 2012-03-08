
package edu.arhs.first1100.r2012.routines;

import edu.arhs.first1100.r2012.drive.DriveSystem;
import edu.arhs.first1100.r2012.pid.EncoderPIDLeft;
import edu.arhs.first1100.r2012.pid.EncoderPIDRight;
import edu.wpi.first.wpilibj.Timer;
import edu.arhs.first1100.util.Routine;
/**
 *
 * @author team1100
 */
public class DriveBackRoutine extends Routine{

    private final double TARGET_ROTATION = -0.75;

    private EncoderPIDLeft enLeft;
    private EncoderPIDRight enRight;

    public DriveBackRoutine(){
        super(1000);
        enLeft = new EncoderPIDLeft();
        enRight = new EncoderPIDRight();
    }

    public void doCancel(){
        enLeft.setSetpoint(0.0);
        enRight.setSetpoint(0.0);
        enLeft.disable();
        enRight.disable();
    }

    public void run(){
        enLeft.setSetpoint(TARGET_ROTATION);
        enRight.setSetpoint(TARGET_ROTATION);
        Timer.delay(1.0);
        enLeft.disable();
        enRight.disable();
        setDone();
    }
}
