
import edu.arhs.first1100.r2011.camera.CameraSystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;


class CamSourceDistance implements PIDSource
{
    CameraSystem cs;
    
    public double pidGet()
    {
        if(cs.getBiggestParticle().imageHeight > 25) 
        {
            System.out.println("biggest particle is abouve center");
            return 1;
        }
        if(cs.getBiggestParticle().imageHeight < 25)
        {
            System.out.println("biggest particle is below center");
            return -1;
        }
        else return 0;
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