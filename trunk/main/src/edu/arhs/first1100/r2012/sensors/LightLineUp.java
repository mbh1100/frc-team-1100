/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.sensors;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 * @author team1100
 */
public class LightLineUp
{
    private DigitalInput left;
    private DigitalInput right;
    
    public LightLineUp(int leftChannel, int rightChannel)
    {
        left = new DigitalInput(leftChannel); 
        right = new DigitalInput(rightChannel);
    }   
    
    public boolean isLeftOnLine()
    {
        return !left.get();
    }
    
    public boolean isRightOnLine()
    {
        return !right.get();
    }
    public boolean areBothOnLine()
    {
        return (isLeftOnLine() && isRightOnLine());
        
    }
}
