package edu.arhs.first1100.r2012.camera;

//util imports
import edu.arhs.first1100.util.Log;
import edu.arhs.first1100.util.SystemBase;

//wpilib imports
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

//project imports


public class CameraSystem extends SystemBase
{
    
    //Thresholds and Constants
    private final int GREEN_THRESHOLD = 0;
    private final int PARTICLE_NUMBERS = 6; //number of particles to save in pRep
    private final int BRIGHTNESS = 10;
    private final int COMPRESSION = 0;
    private final double IN_LINE_THRESHOLD = .10; //Threshold to see if two particles are in line
    
    //RGB Threshold
    private int minRed = 0;
    private int maxRed = 0;
    private int minGreen = 0;
    private int maxGreen = 0;
    private int minBlue = 0;
    private int maxBlue = 0;
    
    //Image Related Objects
    private AxisCamera ac = null;
    private ColorImage cImg = null;
    private BinaryImage bImg = null;
    private ParticleAnalysisReport[] pRep = null; //ordered particleAnalysisReport
    
    //SubHeirarchy Objects
    
    public CameraSystem()
    {
        //Create Objects
        ac = AxisCamera.getInstance();

        //Default Camera Settings
        ac.writeCompression(COMPRESSION);
        ac.writeBrightness(BRIGHTNESS);
        ac.writeExposureControl(AxisCamera.ExposureT.hold);
        ac.writeRotation(AxisCamera.RotationT.k0);
        ac.writeResolution(AxisCamera.ResolutionT.k320x240);

        //Defaults
        setThreshold(GREEN_THRESHOLD);
    }
    
    public void start()
    {
        pRep = null;
        super.start();
    }
    
    /**
     * Tick
     */
    public void tick(){
        
        /**
        * Gets an image from the camera to find particles within the camera's
        * RGB threshold.
        */
        if (ac.freshImage()){
            try{
                cImg = ac.getImage();
                bImg = cImg.thresholdRGB(minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue);
                
                pRep = bImg.getOrderedParticleAnalysisReports(PARTICLE_NUMBERS);
                
                
                /**
                 * Prints out all info about all particles in pRep 
                 */
                for(int i = 0; pRep != null && i < pRep.length; i++)
                {
                    printParticleAnalysisReport(pRep[i]);
                }
                
                
                cImg.free();
                bImg.free();
            }
            catch(Exception e){
                Log.defcon3(this, e.getMessage());
            }
        }
    }
    
    /**
    * Set the threshold of colors the camera should look for.
    * All parameters must be 0-255.
    * @param r Minimum RedValue
    * @param R Maximum Red Value
    * @param g Minimum Green Value
    * @param G Maximum Green Value
    * @param b Minimum Blue Value
    * @param B Maximum Blue Value
    */
    private synchronized void setThresholdRGB(int r, int R, int g, int G, int b, int B){
        minRed   = (r >= 0 && r <= 255 && r <= R)? r : 0;
        maxRed   = (R >= 0 && R <= 255 && R >= r)? R : 255; 
        minGreen = (g >= 0 && g <= 255 && g <= G)? g : 0;
        maxGreen = (G >= 0 && G <= 255 && G >= g)? G : 255;
        minBlue  = (b >= 0 && b <= 255 && b <= B)? b : 0;
        maxBlue  = (B >= 0 && B <= 255 && B >= b)? B : 255;
    }

    /**
    * Sets a Preset Camera Threshold
    * @param t Threshold
    */
    private synchronized void setThreshold(int t)
    {
        switch (t){
            case GREEN_THRESHOLD:
                Log.defcon1(this, "Set Green Threshold");
                setThresholdRGB(80, 130, 150, 255, 50, 130);
                break;
        }
    }
    
    /**
     * @param p A ParticleAnalysisReport
     * @return Returns the direction of the particle in the x direction (positive is right, negative is left)
     */
    public synchronized double getDirectionX(ParticleAnalysisReport p)
    {
        return p.center_mass_x_normalized;
    }
    
    /**
     * @param p A ParticleAnalysisReport
     * @return Returns the direction of the particle in the y direction (positive is up, negative is down)
     */
    public synchronized double getDirectionY(ParticleAnalysisReport p)
    {
        return p.center_mass_y_normalized;
    }
    
    /**
     * @return Returns the Largest Particle or null if there is none
     */
    public synchronized ParticleAnalysisReport getLargestParticle()
    {
        if (pRep != null && pRep.length != 0)
        {
            return pRep[0];
        }
        else
        {
            return null;
        }
    }
    
    /**
     * @return Returns the Particle with the highest y-value or null if none, in pRep
     */
    public synchronized ParticleAnalysisReport getHighestParticle()
    {
        if (pRep != null && pRep.length != 0)
        {
            ParticleAnalysisReport highest = pRep[0];
            for(int i = 0; i < pRep.length; i++)
            {
                if(pRep[i].center_mass_y_normalized > highest.center_mass_y_normalized)
                {
                    highest = pRep[i];
                }
            }
            return highest;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * @return Returns the Particle with the lowest y-value or null if none, in pRep
     */
    public synchronized ParticleAnalysisReport getLowestParticle()
    {
        if (pRep != null && pRep.length != 0)
        {
            ParticleAnalysisReport lowest = pRep[0];
            for(int i = 0; i < pRep.length; i++)
            {
                if(pRep[i].center_mass_y_normalized < lowest.center_mass_y_normalized)
                {
                    lowest = pRep[i];
                }
            }
            return lowest;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * @param rep1 A ParticleAnalysisReport
     * @param rep2 A ParticleAnalysisReport
     * @return Returns whether two particles have similar x-coordinates
     */
    public synchronized boolean isParticlesInLineX(ParticleAnalysisReport rep1, ParticleAnalysisReport rep2)
    {
        return (Math.abs(rep1.center_mass_x_normalized - rep2.center_mass_x_normalized) < IN_LINE_THRESHOLD);
    }
    
    /**
     * @param rep1 A ParticleAnalysisReport
     * @param rep2 A ParticleAnalysisReport
     * @return Returns whether two particles have similar y-coordinates
     */
    public synchronized boolean isParticlesInLineY(ParticleAnalysisReport rep1, ParticleAnalysisReport rep2)
    {
        return (Math.abs(rep1.center_mass_y_normalized - rep2.center_mass_y_normalized) < IN_LINE_THRESHOLD);
    }
    
    /**
     * @param p a particleAnalysisReport
     * Prints information about a ParticleAnalysisReport
     */
    private void printParticleAnalysisReport(ParticleAnalysisReport p)
    {                    
        Log.defcon1(this,"Area: "+ p.particleArea);

        Log.defcon1(this,"Height: "+ p.boundingRectHeight);
        Log.defcon1(this,"Width: "+ p.boundingRectWidth);

        Log.defcon1(this,"Left: "+ p.boundingRectLeft);
        Log.defcon1(this,"Top: "+ p.boundingRectTop);
        Log.defcon1(this,"Right: "+ (p.boundingRectLeft + p.boundingRectWidth));
        Log.defcon1(this,"Bottom: "+ (p.boundingRectTop - p.boundingRectHeight));

        Log.defcon1(this,"Center x-Coordinate: "+ p.center_mass_x);
        Log.defcon1(this,"Center y-Coordinate: "+ p.center_mass_y);
        Log.defcon1(this,"Center x-normalized: "+ p.center_mass_x_normalized);
        Log.defcon1(this,"Center y-normalized: "+ p.center_mass_y_normalized);
    }
    
    /**
     * Sets Brightness of the Camera
     * @param b the Brightness
     */
    public void setBrightness(int b)
    {
        ac.writeBrightness(b);
    }
}
