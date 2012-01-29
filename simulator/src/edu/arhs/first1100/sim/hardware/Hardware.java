/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import edu.arhs.first1100.sim.Simulation;
import edu.arhs.first1100.sim.playfield.Playfield;
import edu.arhs.first1100.sim.hardware.component.CarDrive;
import java.util.LinkedList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Joed
 */
public class Hardware extends Group {

    Shape body;
    Playfield playfield;
    LinkedList<Component> components;
    Output outputs[];
    Input inputs[];
    static final int MAX_CHANNELS = 128;

    public Hardware() {
        components = new LinkedList<Component>();
        outputs = new Output[MAX_CHANNELS];
        inputs = new Input[MAX_CHANNELS];

        Rectangle chassis = new Rectangle(100, 80);
        chassis.setX(0);
        chassis.setY(0);
        chassis.setStrokeType(StrokeType.INSIDE);
        chassis.setStroke(Color.web("white", 1.0f));
        chassis.setStrokeWidth(2f);

        body = chassis;

        getChildren().add(body);

        Circle spot = new Circle(0, 0, 2);
        spot.setFill(Color.web("white", 1.0f));
        addShape(spot, 90, 40, 0);
    }

    public void position(double x, double y, double rotation) {                
        setTranslateX(x);
        setTranslateY(y);
        setRotate(rotation);
    }
    
    public Playfield getPlayfield()
    {
        return playfield;
    }
    
    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    public void addShape(Shape s, double x, double y, double rotation) {
        s.setTranslateX(x);
        s.setTranslateY(y);
        s.getTransforms().add(new Rotate(rotation, 0, 0));
        Shape sPath = Shape.intersect(s, s);
        Simulation.transformPath(this, (Path) sPath);
        body = Shape.union(body, sPath);
        getChildren().add(s);
    }

    public void addComponent(Component c) {
        addComponent(c, 0, 0, 0);
    }
    public void addComponent(Component c, double x, double y) {
        addComponent(c, x, y, 0);
    }
    public void addComponent(Component c, double x, double y, double rotation) {
        components.add(c);
        Shape shape = c.setParent(this);
        if(shape != null) {
            addShape(shape, x, y, rotation);
        }
        Output[] outs = c.getOutputs();
        if (outs != null) {
            for (Output o : outs) {
                int channel = o.getChannel();
                if (channel > MAX_CHANNELS) {
                    throw new RuntimeException("Component added on illegal channel " + channel);
                } else if(outputs[channel] != null) {
                    System.err.println("WARNING: Output channel " + channel + " being reassigned");
                }
                outputs[channel] = o;
            }
        }
        Input[] ins = c.getInputs();
        if (ins != null) {
            for (Input in : ins) {
                int channel = in.getChannel();
                if (channel > MAX_CHANNELS) {
                    throw new RuntimeException("Component added on illegal channel " + channel);
                } else if(inputs[channel] != null) {
                    System.err.println("WARNING: Input channel " + channel + " being reassigned");
                }
                inputs[channel] = in;
            }
        }
    }

    public Output getOutput(int channel) {
        if(outputs[channel] != null) {
            return outputs[Math.max(0, Math.min(MAX_CHANNELS - 1, channel))];
        }
        System.err.println("WARNING: No device on output channel " + channel);
        return new NullOutput(channel);
    }

    public Input getInput(int channel) {
        if(inputs[channel] != null) {
            return inputs[Math.max(0, Math.min(MAX_CHANNELS - 1, channel))];
        }
        System.err.println("WARNING: No device on input channel " + channel);
        return new NullInput(channel);
    }

    public boolean collision() {
        return playfield.collision(this, body);
    }
}
