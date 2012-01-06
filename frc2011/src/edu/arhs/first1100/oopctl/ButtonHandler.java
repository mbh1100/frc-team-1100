package edu.arhs.first1100.oopctl;
import edu.arhs.first1100.log.Log;

public class ButtonHandler
{
    private String name;
    
    public void notHeld()
    {
        Log.defcon3(this, name + " Button Not Held");//says that the button is not being held
    }

    public void held()
    {
        Log.defcon3(this, name + " Button Held");//Says that the button is being held
    }

    public void pressed()
    {
        Log.defcon3(this, name + " Button Pressed");//Says that the button is pressed
    }

    public void released()
    {
        Log.defcon3(this, name + " Button Released"); //Says that the button has been releaced
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
}
