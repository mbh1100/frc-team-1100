/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.BasicTank;
import edu.arhs.first1100.sim.playfield.MazePlayfield;

/**
 *
 * @author Joed
 */
public class MySimulation extends Simulation {

    public void init() {
        setController(new MyController());
        setHardware(new BasicTank(), 50, 50, 0);
        setPlayfield(new MazePlayfield());
    }
}
