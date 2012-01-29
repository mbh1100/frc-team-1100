/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.BasicTank;
import edu.arhs.first1100.sim.hardware.Hardware;
import edu.arhs.first1100.sim.hardware.component.CarDrive;
import edu.arhs.first1100.sim.hardware.component.RangeSensor;
import edu.arhs.first1100.sim.hardware.component.TankDrive;
import edu.arhs.first1100.sim.playfield.MazePlayfield;
import edu.arhs.first1100.sim.playfield.SimpleWalledPlayfield;

/**
 *
 * @author Joed
 */
public class MySimulation extends Simulation {

    public void init() {
        setController(new MyController());
        setHardware(new BasicTank());
        setPlayfield(new MazePlayfield());
    }
}
