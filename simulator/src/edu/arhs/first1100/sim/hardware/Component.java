/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import javafx.scene.shape.Shape;

/**
 *
 * @author Joed
 */
public class Component {

    protected Channel channels[];
    protected Hardware parent;

    public Component() {
    }

    public Channel[] getChannels() {
        return channels;
    }

    public Shape setParent(Hardware parent) {
        this.parent = parent;
        return null;
    }
}
