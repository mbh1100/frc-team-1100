/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
        
/**
 *
 * @author team1100
 */
public class JoyGyro {
    
    public JoyGyro(){
        
        
    }
    
    
    public void overRide(){
        int slot = 2;
        int channel = 1;
        
        Jaguar motor = new Jaguar(slot, channel);
        Joystick joy = new Joystick(1);
        joy.getAxis(Joystick.AxisType.kY);
    }
    
    
    
    
}
