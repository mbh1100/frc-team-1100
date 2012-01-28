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
    Gyro gyro;
    Jaguar jaguar;
    GyroPid gyropid;
    
    public Balance()
    {
        s1 = 1;
        c1 = 1;
        s2 = 2;
        c2 = 1;
        
        gyro = new Gyro(s1, c1);
        jaguar = new Jaguar(s2, c2);
        gyro.reset();
        gyropid = new GyroPid(gyro, jaguar);
        gyropid.setTolerance(0.83);
        gyropid.setOutputRange(-0.3, 0.3);
        gyropid.enable();
    }
    public void reset()
    {
        gyropid.disable();
        gyro.reset();
        gyropid.enable();
    }
    
    public void balanceBall()
    {   
     
        
        //gyropid.setSetpoint(0.0);
        System.out.println(gyro.getAngle());        
    }
    public void disable()
    {
        gyropid.disable();
    }
}