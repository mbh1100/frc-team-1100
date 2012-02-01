/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;
import edu.arhs.first1100.util.Log;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
        
/**
 *
 * @author team1100
 */
public class JoyGyro 
{
    Jaguar motor;
    Joystick jstick;
    
    public JoyGyro(Jaguar motor)
    {
        int slot = 2;
        int channel = 1;
        
        jstick = new Joystick(2);
        System.out.println("Joystick enabled");
        this.motor = motor;
    }
    
    
    public void overRide()
    {
        
        motor.set(  .03   *   (jstick.getAxis(Joystick.AxisType.kY)));       
              
    }
    public void jstickval()
    {
       System.out.println("                                            " + jstick.getAxis(Joystick.AxisType.kY));            
    }

   
    
}
