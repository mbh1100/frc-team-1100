package edu.arhs.first1100.oopctl;

public class JoystickAxisHandler
{
    private String name;
    private double deadBandWidth;

    public void getHandleValue(double value)
    {

    }

    public void getNewHandleValue(double value)
    {

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    protected void setDeadBand(double in)
    {
        deadBandWidth = in;
    }

    double getDeadBand()
    {
        return deadBandWidth;
    }

}
