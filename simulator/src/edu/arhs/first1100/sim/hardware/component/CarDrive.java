/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware.component;

import edu.arhs.first1100.sim.Config;
import edu.arhs.first1100.sim.hardware.Channel;
import edu.arhs.first1100.sim.hardware.Component;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Joed
 */
public class CarDrive extends Component {

    static final double ROT_RESOLUTION = 0.01;
    static final double SPEED_RESOLUTION = 0.01;
    static final double SPEED = 3;
    Timeline driveTimeline;
    Timeline turnTimeline;
    private double speed = 0;
    private double rotation = 0;

    public CarDrive(final int driveChannel, final int turnChannel) {
        channels = new Channel[2];
        channels[0] = new Channel(driveChannel) {

            @Override
            public void set(double value) {
                drive(value);
            }
        };

        channels[1] = new Channel(turnChannel) {

            @Override
            public void set(double value) {
                turn(value);
            }
        };
    }

    public void drive(double s) {
        s = SPEED * Math.min(1.0, Math.abs(s)) * Math.signum(s);
        if (s != 0.0 && Math.abs(s - speed) < SPEED_RESOLUTION) {
            return;
        }
        speed = s;
        if (driveTimeline != null) {
            driveTimeline.stop();
            driveTimeline = null;
        }

        if (speed == 0) {
            return;
        }
        driveTimeline = new Timeline();
        driveTimeline.setCycleCount(Timeline.INDEFINITE);
        driveTimeline.getKeyFrames().addAll(
                new KeyFrame(Config.ANIMATION_TIME,
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        double theta = Math.PI * 2 * parent.getRotate() / 360.0;
                        double dx = speed * Math.sin(Math.PI / 2 - theta);
                        double dy = speed * Math.cos(Math.PI / 2 - theta);
                        double x = parent.getTranslateX() + dx;
                        double y = parent.getTranslateY() + dy;

                        parent.setTranslateX(x);
                        parent.setTranslateY(y);
                        if (parent.collision()) {
                            parent.setTranslateX(x - dx);
                            parent.setTranslateY(y - dy);
                        }
                    }
                }));
        driveTimeline.play();
    }

    public void turn(double s) {
        double r = Math.min(1.0, Math.abs(s)) * Math.signum(s);
        if (r != 0.0 && Math.abs(rotation - r) < ROT_RESOLUTION) {
            return;
        }
        rotation = r;
        if (turnTimeline != null) {
            turnTimeline.stop();
            turnTimeline = null;
        }

        if (rotation == 0.0) {
            return;
        }
        turnTimeline = new Timeline();
        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.getKeyFrames().addAll(
                new KeyFrame(Config.ANIMATION_TIME,
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        double was = parent.getRotate();
                        parent.setRotate(was + rotation * Math.signum(speed));
                        if (parent.collision()) {
                            parent.setRotate(was);
                        }
                    }
                }));
        turnTimeline.play();
    }
}
