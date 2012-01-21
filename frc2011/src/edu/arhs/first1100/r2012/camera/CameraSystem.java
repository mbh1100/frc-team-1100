package edu.arhs.first1100.r2012.camera;

//util imports
import edu.arhs.first1100.util.Log;

//wpilib imports
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

//project imports


public class CameraSystem extends SystemBase {
    
    //Thresholds and Constants
    private final int GREEN_THRESHOLD = 1;
    private final int PARTICLE_NUMBERS = 4;
    
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
    private ParticleAnalysisReport[] pRep = null;
    
    //SubHeirarchy Objects
    private Light light = null;
    
    public CameraSystem() {
        ac = AxisCamera.getInstance();
        light = new Light(3);

        //Camera Settings
        ac.writeCompression(0);
        ac.writeBrightness(10);
        ac.writeExposureControl(AxisCamera.ExposureT.hold);
        ac.writeRotation(AxisCamera.RotationT.k0);
        ac.writeResolution(AxisCamera.ResolutionT.k160x120);

        setThreshold(GREEN_THRESHOLD);
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
                bImg = cImg.thresholdRGB(minRed, maxRed,minGreen, maxGreen, minBlue, maxBlue);
                
                pRep = bImg.getOrderedParticleAnalysisReports(PARTICLE_SIZE);
                
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
    public synchronized void setThresholdRGB(int r, int R, int g, int G, int b, int B){
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
    private synchronized void setThreshold(int t){
        switch (t){
            case GREEN_THRESHOLD:
                setThresholdRGB(80, 130, 150, 255, 50, 130);

        }
    }
}
