/**
 * @author team1100
 * ManipulatorSystem.java
 *
 * Controls the manipulator component of the robot.
 * 
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class ManipulatorSystem extends SystemBase
{
    private static ManipulatorSystem instance = null;
    
    public static final int STATE_DEFAULT    = 0;
    public static final int STATE_TOP_PEG    = 3;
    public static final int STATE_MIDDLE_PEG = 2;
    public static final int STATE_BOTTOM_PEG = 1;
    public static final int STATE_FLOOR      = 4;

    public static final int LIFTMUX_OPERATOR = 0;
    public static final int LIFTMUX_PID = 1;
    public static final int LIFTMUX_CAM = 2;

    public static final int ARMMUX_OPERATOR = 0;
    public static final int ARMMUX_PID = 1;
    public static final int ARMMUX_CAM = 2;
    
    private int liftMUX = LIFTMUX_OPERATOR;
    private int armMUX = ARMMUX_OPERATOR;
    
    private AdvJaguar liftJaguar;
    private Encoder liftEncoder;
    private LiftPid liftPID;

    private DigitalInput liftTopLimitSwitch;
    private DigitalInput liftBottomLimitSwitch;
    private DigitalInput armLimitSwitch;
    //private DigitalInput armBackLimitSwitch;
    
    private AdvJaguar armJaguar;
    private Encoder armEncoder;
    private ArmPid armPID;

    private LiftCamPid liftCamPID;
    private ArmCamPid armCamPID;

    private Solenoid claw;
    private Solenoid wrist;

    private AdvJaguar rollerTop;
    private AdvJaguar rollerBottom;
    
    private boolean defaultState;

    public ManipulatorSystem()
    {
        //Lift Constructers
        liftJaguar = new AdvJaguar(4, 6, true);
        
        liftEncoder = new Encoder(8, 9);
        liftEncoder.start();
        
        liftPID = new LiftPid();
        liftPID.setOutputRange(-0.5, 1.0);

        liftBottomLimitSwitch = new DigitalInput(7);
        liftTopLimitSwitch = new DigitalInput(4);
        armLimitSwitch = new DigitalInput(5);//IDK


        //Arm Constructers
        armJaguar = new AdvJaguar(4, 8, false);

        armEncoder = new Encoder(10, 11);
        armEncoder.start();
        
        armPID = new ArmPid();
        armPID.setOutputRange(-0.3, 0.3);
        
        //armBackLimitSwitch = new DigitalInput(12);
        
        rollerTop = new AdvJaguar(4, 9, false);
        rollerBottom = new AdvJaguar(4, 10, false);

        //Other
        liftCamPID = new LiftCamPid();
        liftCamPID.setOutputRange(-0.45, 1.0);

        armCamPID = new ArmCamPid();
        armCamPID.setOutputRange(-0.2, 0.2);
        armCamPID.setSetpoint(1000);


    }
    
    public static ManipulatorSystem getInstance()
    {
        if(instance == null) instance = new ManipulatorSystem();
        return instance;
    }
    
    public void tick()
    {
        /*
         * MUX Control
         *
         * All PIDS revert back to the operator state after moving, and
         * calls a notify.  Operator state = no pids enabled.
         */
        switch(liftMUX)
        {
            case LIFTMUX_OPERATOR:
                Log.defcon2(this, "LiftMux: Op");
                if(liftPID.isEnable()) liftPID.disable();
                if(liftCamPID.isEnable()) liftCamPID.disable();
                break;
                
            case LIFTMUX_PID:
                Log.defcon2(this, "LiftMux: Pid");

                if(liftCamPID.isEnable()) liftCamPID.disable();
                if(liftPID.isEnable())
                {
                    Log.defcon1(this, ("LiftPID Error : " + liftPID.getError()));
                    if(Math.abs(liftPID.getError()) <= 5.0)
                    {
                        stopLiftPIDs();
                        Log.defcon2(this, "LiftPid: TARGET REACHED");
                    }
                }
                else
                {
                    liftPID.enable();
                }
                break;

            case LIFTMUX_CAM:
                Log.defcon2(this, "LiftMux: Cam");
                if(liftPID.isEnable()) liftPID.disable();
                liftCamPID.enable();
                
                if(Math.abs(liftCamPID.getError()) <= 1.0)
                {
                    //stopLiftPIDs();
                }
                break;
        }
        
        /*
         * Arm Code
         */
        switch(armMUX)
        {
            case ARMMUX_OPERATOR:
                Log.defcon1(this, "ArmMux: Op");
                if(armPID.isEnable()) armPID.disable();
                if(armCamPID.isEnable()) armCamPID.disable();
                break;
            
            case ARMMUX_PID:
                Log.defcon2(this, "ArmMux: Pid");
                if (armCamPID.isEnable()) armCamPID.disable();

                if (armPID.isEnable())
                {
                    if (Math.abs(armPID.getError()) <= 1.0)
                    {
                        //stopArmPIDs();
                        Log.defcon2(this, "ArmPid: TARGET REACHED");
                    }
                }
                else
                {
                    armPID.enable();
                }
                break;

            case ARMMUX_CAM:
                Log.defcon2(this, "ArmMux: Cam");
                if (armPID.isEnable()) armPID.disable();

                if (armCamPID.isEnable())
                {
                    if (Math.abs(armPID.getError()) <= 1.0)
                    {
                        stopArmPIDs();
                        Log.defcon2(this, "ArmCam: TARGET REACHED");
                    }
                }
                else
                {
                    armCamPID.enable();
                }
                break;
        }
        
        if(!armLimitSwitch.get())
        {
            Log.defcon2(this, "Resetting Arm Encoder");
            if(getArmEncoder() != 0)
                resetArmEncoder();
            if(defaultState)
            {
                Log.defcon2(this, "End of default state for arm!");
                armMUX = ARMMUX_OPERATOR;
            }
        }

        if(!liftBottomLimitSwitch.get())
        {
            Log.defcon2(this, "Resetting Lift Encoder");
            if(getLiftEncoder() != 0)
                resetLiftEncoder();
            if(defaultState)
            {
                Log.defcon2(this, "End of default state for lift!");
                liftMUX = LIFTMUX_OPERATOR;
            }
        }
        
        Log.defcon2(this, "Lift Encoder : "+liftEncoder.get());
        Log.defcon2(this, "Arm Encoder  : "+armEncoder.get());
    }

    public void reset()
    {
        stopArmPIDs();
        stopLiftPIDs();
    }
    
    /*
     * Set State
     */
    public void setState(int state)
    {
        Log.defcon2(this, "Setting state to" + state);
        
        switch(state)
        {
            case STATE_DEFAULT:
                setLiftHeight(-9999);
                setArmPosition(-999);
                defaultState = true;
                // Make jags drive down to calibrate encoders?
                break;
            case STATE_TOP_PEG:
                setLiftHeight(2026);
                setArmPosition(62);
                defaultState = false;
                break;
            case STATE_MIDDLE_PEG:
                setLiftHeight(740);
                setArmPosition(62);
                defaultState = false;
                break;
            case STATE_BOTTOM_PEG:
                setLiftHeight(0);
                setArmPosition(140);
                defaultState = false;
                break;
            case STATE_FLOOR:
                setLiftHeight(620);
                setArmPosition(0);
                defaultState = false;
                break;
        }
    }
    
    /*
     * Lift Interfaces
     */
    public void setLiftSpeed(double speed)
    {
        if(!liftBottomLimitSwitch.get())
        {
            Log.defcon1(this, "!!!!!!!! Bottom limit pressed !!!!!!!!!!");
            if(speed>0.0)
            {
                liftJaguar.set(0.0);
                return;
            }
        }
        if(!liftTopLimitSwitch.get())
        {
            Log.defcon1(this, "!!!!!!!! Top limit pressed !!!!!!!!!!");
            if(speed<0.0)
            {
                liftJaguar.set(0.0);
                return;
            }
        }
        liftJaguar.set(speed);
    }
    
    public void setLiftHeight(double height)
    {
        liftMUX = LIFTMUX_PID;
        liftPID.setSetpoint(height);
        
    }

    public double getLiftSpeed()
    {
        return liftJaguar.get();
    }
    
    /*
     * PID Interfaces
     */
    public void enableLiftCamPID()
    {
        liftMUX = LIFTMUX_CAM;
    }

    public void enableArmCamPID()
    {
        armMUX = ARMMUX_CAM;
    }
    
    public int getLiftMUXState()
    {
        return liftMUX;
    }
    
    public double getLiftPIDError()
    {
        return liftPID.getError();
    }
    
    public int getArmMUXState()
    {
        return armMUX;
    }

    public double getArmPIDError()
    {
        return armPID.getError();
    }
    
    public void stopLiftPIDs()
    {
        liftMUX = LIFTMUX_OPERATOR;
    }

    public void stopArmPIDs()
    {
        armMUX = ARMMUX_OPERATOR;
    }
    
    /*
     * Arm Interface
     */
    public void setArmSpeed(double speed)
    {
        if(!armLimitSwitch.get())
        {
            Log.defcon1(this, "########### Arm limit pressed ############### (#brown)");
            if(speed < 0.0)
            {
                armJaguar.set(0.0);
                return;
            }
        }
        armJaguar.set(speed);
    }
    
    public void setArmPosition(double position)
    {
        armMUX = ARMMUX_PID;
        armPID.setSetpoint(position);
    }

    public double getArmSpeed()
    {
        return armJaguar.get();
    }
    
    /*
     * Encoders
     */
    public double getLiftEncoder()
    {
        return liftEncoder.get();
    }

    public double getArmEncoder()
    {
        return armEncoder.get();
    }

    public void resetLiftEncoder()
    {
        liftEncoder.reset();
    }

    public void resetArmEncoder()
    {
        armEncoder.reset();
    }

    public void closeClaw()
    {

    }

    public void openClaw()
    {

    }

    public void wristDown()
    {

    }

    public void wristUp()
    {
    
    }
    
    public void rollersOut()
    {
        rollerTop.set(1.0);
        rollerBottom.set(1.0);
    }

    public void rollersIn()
    {
        rollerTop.set(-1.0);
        rollerBottom.set(-1.0);
    }

    public void rollerWristUp()
    {
        rollerTop.set(-1.0);
        rollerBottom.set(1.0);
    }

    public void rollerWristDown()
    {
        rollerTop.set(1.0);
        rollerBottom.set(-1.0);
    }

    public void rollersStop()
    {
        rollerTop.set(0.0);
        rollerBottom.set(0.0);
    }

    public boolean getArmLimitSwitch()
    {
        return armLimitSwitch.get();
    }

    public boolean getLiftLimitSwitch()
    {
        return liftTopLimitSwitch.get();
    }
}
