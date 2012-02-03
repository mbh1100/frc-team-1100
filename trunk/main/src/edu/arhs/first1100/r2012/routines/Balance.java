/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.routines;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.arhs.first1100.r2012.pid.GyroPid;
/**
 *
 * @author team1100
 */
public class Balance 
{
    
    int s1;
    int c1;
    int s2;
    int c2;
    Gyro gyroOne;
    Gyro gyroTwo;
    Jaguar jaguar;
    GyroPid gyropid;
    
    public Balance(Jaguar jaguar)
    {
        s1 = 1;
        c1 = 1;
        s2 = 1;
        c2 = 2;
        
        gyroOne = new Gyro(s1, c1);
        gyroTwo = new Gyro(s2, c2);
        //jaguar = new Jaguar(s2, c2);
        
        gyroOne.reset();
        gyroTwo.reset();
        
        gyropid = new GyroPid(gyroOne, jaguar, gyroTwo);
        gyropid.setTolerance(0.83);
        gyropid.setOutputRange(-0.3, 0.3);
        //gyropid.enable();
    }
    public void disable()
    {
        gyropid.disable();
    }
    public void enable()
    {
        gyropid.enable();
    }
      
      public void reset()
    {
        gyropid.disable();
        gyroOne.reset();
        gyroTwo.reset();
    }
      
    public void balanceBall()
    {   
        //gyropid.setSetpoint(0.0);
        System.out.println(gyroOne.getAngle());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>" + gyroTwo.getAngle());
    }
    
    public double getAngle()
    {
        return gyroOne.getAngle();
    }
    public double getAngleTwo()
    {
        return gyroTwo.getAngle();
    }
    
    public void setSetpoint(double setPoint)
    {
        gyropid.setSetpoint(setPoint);
    }
    
    public double getError()
    {
        return gyropid.getError();
    }
  
}