package edu.arhs.first1100.oopctl.handlers;

import edu.arhs.first1100.util.Log;

public class ButtonHandler {

    private String name;

    public void notHeld() {
        Log.defcon3(this, name + " Not Held");//says that the button is not being held
    }

    public void held() {
        Log.defcon3(this, name + " Held");//Says that the button is being held
    }

    public void pressed() {
        Log.defcon3(this, name + " Pressed");//Says that the button is pressed
    }

    public void released() {
        Log.defcon3(this, name + " Released"); //Says that the button has been releaced
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
