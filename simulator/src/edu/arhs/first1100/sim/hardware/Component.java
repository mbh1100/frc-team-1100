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
    
    protected Output outputs[];
    protected Input inputs[];
    protected Hardware parent;
    
    public Component()
    {
    }
    public Output[] getOutputs() {
        return outputs;
    }
    public Input[] getInputs() {
        return inputs;
    }
    public Shape setParent(Hardware parent) {
        this.parent = parent;
        return null;
    }
}
