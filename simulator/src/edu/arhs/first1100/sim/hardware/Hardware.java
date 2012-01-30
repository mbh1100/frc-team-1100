/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import edu.arhs.first1100.sim.Simulation;
import edu.arhs.first1100.sim.playfield.Playfield;
import java.util.LinkedList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Joed
 */
public class Hardware extends Group {

    Shape body;
    Playfield playfield;
    LinkedList<Component> components;
    Channel channels[];
    static final int MAX_CHANNELS = 128;

    public Hardware() {
        components = new LinkedList<Component>();
        channels = new Channel[MAX_CHANNELS];

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

    public Playfield getPlayfield() {
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
        if (shape != null) {
            addShape(shape, x, y, rotation);
        }
        Channel[] chans = c.getChannels();
        if (chans != null) {
            for (Channel chan : chans) {
                int channel = chan.getChannel();
                if (channel > MAX_CHANNELS) {
                    throw new RuntimeException("Component added on illegal channel " + channel);
                } else if (channels[channel] != null) {
                    System.err.println("WARNING: Output channel " + channel + " being reassigned");
                }
                channels[channel] = chan;
            }
        }
    }

    public Channel getChannel(int channel) {
        if (channels[channel] != null) {
            return channels[Math.max(0, Math.min(MAX_CHANNELS - 1, channel))];
        }
        System.err.println("WARNING: No device on output channel " + channel);
        return new Channel(channel);
    }

    public boolean collision() {
        return playfield.collision(this, body);
    }
}
