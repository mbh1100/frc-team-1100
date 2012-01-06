package edu.arhs.first1100.drive;

import com.sun.squawk.util.MathUtils;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogModule;


public class DriveSystem extends SystemBase
{
    private static DriveSystem instance = null;

    private SteerPid steerPid;
    private DriveCamPid powerPid;

    private AdvJaguar leftJaguars;
    private AdvJaguar rightJaguars;

    private double curve = 0.0;
    private double power = 0.0;

    private edu.wpi.first.wpilibj.Gyro gyro;
    private AnalogChannel range;

    public DriveSystem()
    {
        steerPid = new SteerPid();
        powerPid = new DriveCamPid();

        leftJaguars  = new AdvJaguar(4, 2, 4, false);
        rightJaguars = new AdvJaguar(4, 1, 3, true);

        gyro = new edu.wpi.first.wpilibj.Gyro(1);
        range = new AnalogChannel(2);
    }

    public static DriveSystem getInstance()
    {
        if(instance == null) instance = new DriveSystem();
        return instance;
    }

    public void tick()
    {
        /*
        if (powerPid.isEnable() && Math.abs(powerPid.getError()) < 10.0)
        {
            //Log.defcon1(this, "Stopping drive pids");
            powerPid.disable();
            steerPid.disable();
        }*/
        
        //Log.defcon1(this, "Steer PID Output: " + steerPid.get());
        //Log.defcon1(this, "Power PID Output: " + powerPid.get());
        //Log.defcon1(this, "Gyro:" + gyro.getAngle());


        //Log.defcon1(this, "Particle Size:" + CameraSystem.getInstance().getBiggestParticle().particleArea);
        //Log.defcon1(this, "");

        //Log.defcon1(this, "Left:  "+leftJaguars.get());
        //Log.defcon1(this, "Right: "+rightJaguars.get());
        //Log.defcon1(this, "");
    }

    public void steerByCamera()
    {
        steerPid.setOutputRange(-0.1, 0.1);
        steerPid.enable();
    }

    public void driveByCamera(int range)
    {
        steerByCamera();
        powerPid.setOutputRange(-0.5, 0.5);
        powerPid.setSetpoint(range);//why are we giving it a setpoint?  Shouldnt it be a camera particle size?
                                     //it is camera particle size. The pid only knows it as something
                                     // to compare to the pidGet value(). [now provided by the rangefinder].
        powerPid.enable();
    }

    public boolean getDrivePidEnabled()
    {
        return powerPid.isEnable();
    }

    public void setTankSpeed(double leftSide, double rightSide)
    {
        leftJaguars.set(leftSide);
        rightJaguars.set(rightSide);
    }

    public void disablePids()
    {
        if(steerPid.isEnable()) steerPid.reset();
        if(powerPid.isEnable()) powerPid.reset();
    }

    private void setArcadeSpeed(double outputMagnitude, double curve)
    {
        setArcadeSpeed(outputMagnitude, curve, 0.5);
    }

    private void setArcadeSpeed(double outputMagnitude, double curve, double sensitivity)
    {
        double leftOutput, rightOutput;

        if (curve < 0)
        {
            double value = MathUtils.log(-curve);
            double ratio = (value - sensitivity) / (value + sensitivity);
            if (ratio == 0)
            {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude / ratio;
            rightOutput = outputMagnitude;
        }
        else if (curve > 0)
        {
            double value = MathUtils.log(curve);
            double ratio = (value - sensitivity) / (value + sensitivity);
            if (ratio == 0)
            {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude / ratio;
        }
        else
        {
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude;
        }
        setTankSpeed(leftOutput,//*OperatorSystem.getInstance().getLeftTrim(),
                rightOutput //*OperatorSystem.getInstance().getLeftTrim()
                );
    }

    void setCurve(double curve)
    {
        Log.defcon1(this, "Setting Curve to "+curve);
        this.curve = curve;
        setArcadeSpeed(this.power, this.curve);
    }

    public void setPower(double power)
    {
        this.power = power;
        setArcadeSpeed(this.power, this.curve);
    }

    public void experimentalDrive(double speed, int a)
    {
        int angle = 90-(Math.max(-90, Math.min(90, a)));
        double ratio = Math.sin(angle);
        if (a > 0)
        {
            setTankSpeed(speed,(speed*ratio));
        }
        else if (a < 0)
        {
            setTankSpeed((speed*ratio), speed);
        }
        else
        {
            setTankSpeed(speed, speed);
        }
    }

    public int getRangeValue()
    {
        return range.getValue();
    }

    public double getGyroAngle()
    {
        return gyro.getAngle();
    }
    public void resetGyro()
    {
        gyro.reset();
    }
}