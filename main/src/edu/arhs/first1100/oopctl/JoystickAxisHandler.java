package edu.arhs.first1100.oopctl;

public class JoystickAxisHandler
{
    private String name;
    private double deadBandWidth;

    public void heresYourValue(double value)
    {

    }

    public void heresYourNewValue(double value)
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
