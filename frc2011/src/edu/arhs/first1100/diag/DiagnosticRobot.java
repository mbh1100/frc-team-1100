package edu.arhs.first1100.diag;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;

/**
 * @author team1100
 */
public class DiagnosticRobot
{    
    //Channel numbers
    final int LEFT_JOYSTICK_CHANNEL = 1;
    final int RIGHT_JOYSTICK_CHANNEL = 2;

    //Drive System
    final int RIGHT_JAG_1 = 1;
    final int RIGHT_JAG_2 = 3;
    final int LEFT_JAG_1 = 2;
    final int LEFT_JAG_2 = 4;

    //Manipulator Jags
    final int ARM_JAG = 8;
    final int LIFT_JAG = 6;

    //Solenoids
    final int WRIST = 2;
    final int CLAW = 1;

    //Encoders
    final int LIFT_ENCODER_A = 8;
    final int LIFT_ENCODER_B = 9;
    final int ARM_ENCODER_A = 10;
    final int ARM_ENCODER_B = 11;

    //XBOX controller channels
    final int XBOX_CONTROLLER_CHANNEL = 3;
    final int XBOX_LEFT_STICK_Y = 2;
    final int XBOX_RIGHT_STICK_Y = 5;
    final int XBOX_LEFT_BUMBER = 5;
    final int XBOX_RIGHT_BUMBER = 6;

    //Minbot
    final int MINIBOT_SIDECAR = 6;
    final int MINIBOT_ARM_VICTOR = 1;
    final int MINIBOT_BELT_VICTOR = 2;
    final int MINIBOT_TOGGLE_BUTTON = 6;
    final int MINIBOT_DEPLOY_AXIS = 1;
    
    Joystick left, right, xbox;
    Jaguar r1, r2, l1, l2, armJag, liftJag;
    Solenoid wrist, claw;
    Victor mbArm, mbBelt;
    Encoder liftEncoder, armEncoder;
    boolean lastWristState, lastClawState;
    boolean minibotActive = false;

    public DiagnosticRobot()
    {        
        //Control init
        left = new Joystick(LEFT_JOYSTICK_CHANNEL);
        right = new Joystick(RIGHT_JOYSTICK_CHANNEL);
        xbox = new Joystick(XBOX_CONTROLLER_CHANNEL);

        //Drive init
        r1 = new Jaguar(RIGHT_JAG_1);
        r2 = new Jaguar(RIGHT_JAG_2);
        l1 = new Jaguar(LEFT_JAG_1);
        l2 = new Jaguar(LEFT_JAG_2);

        //Manipulator init
        armJag = new Jaguar(ARM_JAG);
        liftJag = new Jaguar(LIFT_JAG);
        armEncoder = new Encoder(4, ARM_ENCODER_A, 4, ARM_ENCODER_B);
        liftEncoder = new Encoder(4, LIFT_ENCODER_A, 4, LIFT_ENCODER_B);

        wrist = new Solenoid(WRIST);
        claw = new Solenoid(CLAW);
        lastWristState = wrist.get();
        lastClawState = claw.get();

        //Minibot init
        mbArm = new Victor(MINIBOT_SIDECAR, MINIBOT_ARM_VICTOR);
        mbBelt = new Victor(MINIBOT_SIDECAR, MINIBOT_BELT_VICTOR);
    }

    public void teleop()
    {
        minibotActive = xbox.getRawButton(8);
        
        //Driving
        if(minibotActive)
        {
            System.out.println("MINIBOT MODE ON");
            mbArm.set(xbox.getRawAxis(XBOX_LEFT_STICK_Y));
            mbBelt.set(xbox.getRawAxis(XBOX_RIGHT_STICK_Y));
        }
        else
        {
            //Stop Minibot
            mbArm.set(0.0);
            mbBelt.set(0.0);

            //Manip
            liftJag.set(-xbox.getRawAxis(XBOX_RIGHT_STICK_Y));
            armJag.set(xbox.getRawAxis(XBOX_LEFT_STICK_Y)/4);
        }

        //Drive/
        l1.set(left.getY());
        l2.set(left.getY());
        r1.set(right.getY());
        r2.set(right.getY());
        
        //Claw Toggle
        if(xbox.getRawButton(XBOX_LEFT_BUMBER) && !lastClawState)
        {
            claw.set(!claw.get());
            lastClawState = true;
        }
        else if(!xbox.getRawButton(XBOX_LEFT_BUMBER))
        {
            lastClawState = false;
        }

        //Wrist Toggle
        if(xbox.getRawButton(XBOX_RIGHT_BUMBER) && !lastWristState)
        {
            wrist.set(!wrist.get());
            lastWristState = true;
        }
        else if(!xbox.getRawButton(XBOX_RIGHT_BUMBER))
        {
            lastWristState = false;
        }
        
        System.out.println("Lift Encoder:" + liftEncoder.get());
        System.out.println("Arm Encoder: " + armEncoder.get());
        System.out.println();
    }
}
