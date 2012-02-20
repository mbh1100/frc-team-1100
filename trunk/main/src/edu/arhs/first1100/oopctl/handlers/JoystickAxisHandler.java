package edu.arhs.first1100.oopctl.handlers;

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

    public void setDeadBand(double in)
    {
        deadBandWidth = in;
    }

    public double getDeadBand()
    {
        return deadBandWidth;
    }

}
