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
    private final int WHITE_THRESHOLD = 1;
    private final int BLUE_THRESHOLD  = 2;
    private final int PARTICLE_NUMBERS = 6; //number of particles to save in pRep
    private final int BRIGHTNESS = 50;
    private final int COMPRESSION = 0;
    private final double IN_LINE_THRESHOLD = .20; //Threshold to see if two particles are in line
    private final int SIZE_THRESHOLD = 100;
    private final double WHITESPACE_THRESHOLD = .55;

    //RGB Threshold
    private int minRed = 0;
    private int maxRed = 15;
    private int minGreen = 180;
    private int maxGreen = 255;
    private int minBlue = 245;
    private int maxBlue = 255;

    //Image Related Objects
    private AxisCamera ac = null;
    private ColorImage cImg = null;
    private BinaryImage bImg = null;
    private ParticleAnalysisReport[] pRep = null; //ordered particleAnalysisReport

    private static CameraSystem instance;
    //SubHeirarchy Objects

    private CameraSystem()
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
        setThreshold(BLUE_THRESHOLD);

        setSleep(50);
    }

    public void start()
    {
        pRep = null;
        super.start();
    }
    public static CameraSystem getInstance()
    {
        if(instance == null)
        {
            instance = new CameraSystem();
            instance.start();
        }

        return instance;
    }

    /**
     * Tick
     */
    public void tick()
    {

        /**
        * Gets an image from the camera to find particles within the camera's
        * RGB threshold.
        */
        if (ac.freshImage()){
            try{
                cImg = ac.getImage();
                bImg = cImg.thresholdRGB(minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue);

                pRep = bImg.getOrderedParticleAnalysisReports();


                //find number of particles in thresholds
                int filter_number = 0;
                for(int i = 0; i < pRep.length; i++)
                {
                    if(isBigEnough(pRep[i]) && (getWhiteSpace(pRep[i]) < WHITESPACE_THRESHOLD)) filter_number++;
                }

                //new array with particles in threshold
                ParticleAnalysisReport[] filter;
                if(filter_number != 0) filter = new ParticleAnalysisReport[filter_number];
                else filter = null;

                int index = 0;
                for(int i = 0; i < pRep.length; i++)
                {
                    if(isBigEnough(pRep[i]) && (getWhiteSpace(pRep[i]) < WHITESPACE_THRESHOLD))
                    {
                        filter[index] = pRep[i];
                        index++;
                    }
                }

                if(filter == null);
                else if(filter.length == 0)
                {
                    Log.defcon1(this, "No Particles");
                }
                else if(filter.length == 1)
                {
                    Log.defcon1(this, "1 Particles");
                    if(filter[0].center_mass_x_normalized >0)
                    {
                        Log.defcon1(this, "Turn left");
                    }
                    else Log.defcon1(this, "Turn Right");
                }
                else
                {
                    ParticleAnalysisReport highest = this.getHighestParticle(filter);
                    if(highest.center_mass_x_normalized >0)
                    {
                        Log.defcon1(this, "Turn left");
                    }
                    else Log.defcon1(this, "Turn Right");

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
    private synchronized void setThresholdRGB(int r, int R, int g, int G, int b, int B)
    {
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
            case WHITE_THRESHOLD:
                setThresholdRGB(210,255,210,255,210,255);
                break;
            case BLUE_THRESHOLD:
                setThresholdRGB(0,170,0,255,180,255);
                break;
        }
    }

    /**
     * @param p A ParticleAnalysisReport
     * @return Returns the direction of the particle in the x direction (positive is right, negative is left)
     */
    public double getDirectionX(ParticleAnalysisReport p)
    {
        return p.center_mass_x_normalized;
    }

    /**
     * @param p A ParticleAnalysisReport
     * @return Returns the direction of the particle in the y direction (positive is down, negative is up)
     */
    public double getDirectionY(ParticleAnalysisReport p)
    {
        return p.center_mass_y_normalized;
    }

    /**
     * @return Returns the Largest Particle or null if there is none
     */
    public ParticleAnalysisReport getLargestParticle()
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
     * @return Returns the Particle with the lowest y-value or null if none, in pRep
     * y value is greatest at the top and decreasing going down
     */
    public ParticleAnalysisReport getHighestParticle(){
        return getHighestParticle(pRep);
    }
    public ParticleAnalysisReport getHighestParticle(ParticleAnalysisReport[] p)
    {
        if (p != null && p.length != 0)
        {
            ParticleAnalysisReport highest = p[0];
            for(int i = 0; i < p.length; i++)
            {
                if(p[i].center_mass_y_normalized < highest.center_mass_y_normalized)
                {
                    highest = p[i];
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
     * y value is greatest at the top and decreasing going down
     */
    public ParticleAnalysisReport getLowestParticle(ParticleAnalysisReport[] p)
    {
        if (p != null && p.length != 0)
        {
            ParticleAnalysisReport lowest = p[0];
            for(int i = 0; i < p.length; i++)
            {
                if(p[i].center_mass_y_normalized > lowest.center_mass_y_normalized)
                {
                    lowest = p[i];
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
    public boolean isParticlesInLineX(ParticleAnalysisReport rep1, ParticleAnalysisReport rep2)
    {
        return (Math.abs(rep1.center_mass_x_normalized - rep2.center_mass_x_normalized) < IN_LINE_THRESHOLD);
    }

    /**
     * @param rep1 A ParticleAnalysisReport
     * @param rep2 A ParticleAnalysisReport
     * @return Returns whether two particles have similar y-coordinates
     */
    public boolean isParticlesInLineY(ParticleAnalysisReport rep1, ParticleAnalysisReport rep2)
    {
        return (Math.abs(rep1.center_mass_y_normalized - rep2.center_mass_y_normalized) < IN_LINE_THRESHOLD);
    }

    /**
     * @param p a particleAnalysisReport
     * Prints information about a ParticleAnalysisReport
     */
    public void printParticleAnalysisReport(ParticleAnalysisReport p)
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

        Log.defcon1(this,"Whitespace Method: " + getWhiteSpace(p));
        Log.defcon1(this,"Center of Mass Difference: " + getCOMDistance(p)/getCornerDistance(p));

        Log.defcon1(this,"");
        Log.defcon1(this,"");
    }

    /**
     * Sets Brightness of the Camera
     * @param b the Brightness
     */
    public void setBrightness(int b)
    {
        ac.writeBrightness(b);
    }



    public double getWhiteSpace(ParticleAnalysisReport p)
    {
        double area = p.particleArea*4.0/Math.PI;
        double prop = area/(p.boundingRectHeight*p.boundingRectWidth);
        return prop;
    }
    public double getXCOMDifference(ParticleAnalysisReport p)
    {
        double difofX = (p.center_mass_x-(p.boundingRectLeft+p.boundingRectWidth/2));
        return difofX;
    }
    /**
     * positive is down with 0 at top of picture
     * @param p
     * @return
     */
    public double getYCOMDifference(ParticleAnalysisReport p)
    {
        double difofY = (p.center_mass_y-(p.boundingRectTop+p.boundingRectHeight/2));
        return difofY;
    }
    public double getCOMDistance(ParticleAnalysisReport p)
    {
        double distance = Math.sqrt(getYCOMDifference(p)*getYCOMDifference(p) + getXCOMDifference(p)*getXCOMDifference(p));
        return distance;
    }
    public double getCornerDistance(ParticleAnalysisReport p)
    {
        double distance = Math.sqrt(p.boundingRectHeight*p.boundingRectHeight+p.boundingRectWidth*p.boundingRectWidth);
        return distance;
    }
    public boolean isBigEnough(ParticleAnalysisReport p)
    {
        boolean bigenough = p.particleArea >= SIZE_THRESHOLD;
        return bigenough;
    }
}
