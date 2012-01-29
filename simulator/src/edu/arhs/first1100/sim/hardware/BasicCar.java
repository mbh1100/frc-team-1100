/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import edu.arhs.first1100.sim.hardware.component.RangeSensor;
import edu.arhs.first1100.sim.hardware.component.CarDrive;

/**
 *
 * @author Joed
 */
public class BasicCar extends Hardware {
    public BasicCar() {
        addComponent(new CarDrive(1, 2));
        addComponent(new RangeSensor(3, 4), 100, 40);
        addComponent(new RangeSensor(5, 6), 0, 40, 180);
    }
}
