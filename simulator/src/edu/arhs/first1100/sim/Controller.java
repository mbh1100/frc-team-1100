/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import javafx.application.Platform;

/**
 *
 * @author Joed
 */
public abstract class Controller extends Thread {

    boolean stopController;

    abstract public void init();

    public void run() {
        stopController = false;
        while (!stopController) {
            tick();
            try {
                Thread.sleep(40);
            } catch (Exception e) {
            }
        }
    }

    public void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    public void tick() {
    }

    public void die() {
        stopController = true;
        Platform.exit();
    }
}
