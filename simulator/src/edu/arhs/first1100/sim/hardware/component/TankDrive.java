/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware.component;

import edu.arhs.first1100.sim.Config;
import edu.arhs.first1100.sim.Simulation;
import edu.arhs.first1100.sim.hardware.Output;
import edu.arhs.first1100.sim.hardware.Component;
import edu.arhs.first1100.sim.hardware.Hardware;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Joed
 */
public class TankDrive extends Component {

    static final double SPEED_RESOLUTION = 0.01;
    static final double ROT_RESOLUTION = 0.01;
    static final double SPEED = 3;
    Timeline driveTimeline;
    private double leftSpeed = 0;
    private double rightSpeed = 0;
    private double speed = 0;
    private double rotation = 0;
    private double angle = 0;
    
    static Point2D center = new Point2D(50, 40);
    static Point2D leftWheel = new Point2D(50, 0);
    static Point2D rightWheel = new Point2D(50, 80);

    public TankDrive(final int leftChannel, final int rightChannel) {
        outputs = new Output[2];
        outputs[0] = new Output() {

            public int getChannel() {
                return leftChannel;
            }

            public void set(double value) {
                setLeft(value);
            }
        };

        outputs[1] = new Output() {

            public int getChannel() {
                return rightChannel;
            }

            public void set(double value) {
                setRight(value);
            }
        };
    }

    public void setLeft(double s) {
        this.leftSpeed = Math.min(1.0, Math.abs(s)) * Math.signum(s);
        drive();
    }

    public void setRight(double s) {
        this.rightSpeed = Math.min(1.0, Math.abs(s)) * Math.signum(s);
        drive();
    }

    public void drive() {
        double delta = rightSpeed - leftSpeed;
        double rDelta;
        try {
            rDelta = 100 * leftSpeed / delta;
        } catch (Exception e) {
            System.err.println("Math error");
            rDelta = Double.MAX_VALUE;
        }

        double rot;
        if (Double.isNaN(rDelta) || Double.isInfinite(rDelta)) {
            rot = 0;
        } else {
            rot = -Math.atan(rightSpeed / (100 + rDelta));
            if (Double.isNaN(rot)) {
                rot = -Math.atan(leftSpeed / rDelta);
            }
            rot = rot / (2.0 * Math.PI) * 360.0;
        }
        
        /*
         * This projection method seems to just give the average of left and
         * right. I should prove that some day :-)
         *
         * Point2D leftDelta = EduBot.add(leftWheel, new Point2D(leftSpeed, 0));
         * Point2D rightDelta = EduBot.add(rightWheel, new Point2D(rightSpeed,
         * 0)); double s = EduBot.distanceToSegment(center, leftDelta,
         * rightDelta);
         *
         */
        double s = SPEED * (rightSpeed + leftSpeed) / 2;

        if (Math.abs(speed - s) < SPEED_RESOLUTION &&
            Math.abs(rotation - rot) < ROT_RESOLUTION) {
            return;
        }

        speed = s;
        rotation = rot;

        if (driveTimeline != null) {
            driveTimeline.stop();
            driveTimeline = null;
        }

        if (speed == 0 && rotation == 0) {
            return;
        }
        driveTimeline = new Timeline();
        driveTimeline.setCycleCount(Timeline.INDEFINITE);
        driveTimeline.getKeyFrames().addAll(
                new KeyFrame(Config.ANIMATION_TIME,
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        parent.getTransforms().clear();
                        parent.getTransforms().add(new Rotate(angle + rotation, 50, 40));
                        double theta = Math.PI * 2 * (angle + rotation) / 360.0;
                        double dx = speed * Math.cos(theta);
                        double dy = speed * Math.sin(theta);
                        double x = parent.getTranslateX() + dx;
                        double y = parent.getTranslateY() + dy;

                        parent.setTranslateX(x);
                        parent.setTranslateY(y);
                        if (parent.collision()) {
                            parent.setTranslateX(x - dx);
                            parent.setTranslateY(y - dy);
                            parent.getTransforms().clear();
                            parent.getTransforms().add(new Rotate(angle, 50, 40));
                        } else {
                            angle += rotation;
                        }
                    }
                }));
        driveTimeline.play();
    }
}
