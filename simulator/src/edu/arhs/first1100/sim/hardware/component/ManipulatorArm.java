/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware.component;

import edu.arhs.first1100.sim.hardware.Output;
import edu.arhs.first1100.sim.hardware.Component;
import edu.arhs.first1100.sim.hardware.Hardware;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Joed
 */
public class ManipulatorArm extends Component {
    static final double ARM_RESOLUTION = 0.01;
    Timeline armTimeline;
    private double armRotation = 0;
    private double armAngle = 0;
    
    public ManipulatorArm(final int channel)
    {
        outputs = new Output[1];
        outputs[0] = new Output() {
            public int getChannel() { return channel; }
                public void set(double value) {
                    rotate(value);
                }
            };
    }
    
    public Shape setParent(Hardware parent) {
        super.setParent(parent);
        Shape parts;
        
        Circle circ = new Circle(8.0);
        circ.setStrokeType(StrokeType.INSIDE);
        circ.setStroke(Color.web("white", 1.0f));
        circ.setStrokeWidth(2f);
        circ.setFill(Color.web("white", 1.0f));
        circ.setCenterX(0);
        circ.setCenterY(0);

        Line line = new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(60);
        line.setEndY(0);
        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.web("white", 1.0f));
        line.setStrokeWidth(2f);
        line.setFill(Color.web("white", 1.0f));
        parts = Path.union(circ, line);

        circ = new Circle(3.0);
        circ.setStrokeType(StrokeType.INSIDE);
        circ.setStroke(Color.web("white", 1.0f));
        circ.setStrokeWidth(2f);
        circ.setFill(Color.web("white", 1.0f));
        circ.setCenterX(60);
        circ.setCenterY(0);
        parts = Path.union(parts, circ);

        parts.setTranslateX(80);
        parts.setTranslateY(40);

        return parts;
    }
    
    public void rotate(double s) {
        /*
         * Not working - when bot is rotated, rotating arm moves body! double r
         * = Math.min(1.0, Math.abs(s)) * Math.signum(s); if (r != 0.0 &&
         * Math.abs(armRotation - r) < ARM_RESOLUTION) { return; } armRotation =
         * r; if (armTimeline != null) { armTimeline.stop(); armTimeline = null;
         * }
         *
         * if (armRotation == 0.0) { return; } armTimeline = new Timeline();
         * armTimeline.setCycleCount(Timeline.INDEFINITE);
         * armTimeline.getKeyFrames().addAll( new
         * KeyFrame(Config.ANIMATION_TIME, new EventHandler<ActionEvent>() {
         *
         * public void handle(ActionEvent event) { arm.getTransforms().clear();
         * arm.getTransforms().add(new Rotate(armAngle + armRotation, 0, 0));
         * Bounds armBounds = arm.getBoundsInParent(); if
         * (playfield.collision(localToParent(armBounds))) {
         * System.out.println("Reset arm"); arm.getTransforms().clear();
         * arm.getTransforms().add(new Rotate(armAngle, 0, 0)); } else {
         * armAngle = armAngle + armRotation; } } } )); armTimeline.play ();
         */
    }
}
