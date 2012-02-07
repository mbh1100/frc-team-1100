package edu.arhs.first1100.r2012.routines;

 
import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


class CamSourceDistance implements PIDSource
{
    CameraSystem cs;
    
public double pidGet()
    {
        ParticleAnalysisReport biggest = cs.getBiggestParticle();
        cs.tick();
        System.out.println("Height = " + biggest.boundingRectHeight);
        
        if(cs.getBiggestParticle().imageHeight > 81)
        {
            System.out.println("biggest particle is above center");
            return -1;
        }
        if(cs.getBiggestParticle().imageHeight < 75)
        {
            System.out.println("biggest particle is below center");
            return 1;
        }
        System.out.println("Biggest particle is the right distance away");
        return 0;
    }
    
   public CamSourceDistance(CameraSystem c){
       cs = c;
   }
}

class CamOutputDistance implements PIDOutput
{
    Jaguar output;
    
    public void pidWrite(double o)
    {
        output.pidWrite(-o);
    }
    
    public CamOutputDistance(Jaguar j){
        output = j;        
    }
}

public class CamPidDistance extends edu.wpi.first.wpilibj.PIDController
{
    static private final double P = 0.004; //was 0.003
    static private final double I = 0.00085;
    static private final double D = 0.0001;

    public CamPidDistance(CameraSystem source, Jaguar output){
        super (P,I,D, new CamSourceDistance(source), new CamOutputDistance(output));
    }
}