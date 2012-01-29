/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware.component;

import edu.arhs.first1100.sim.hardware.Component;
import edu.arhs.first1100.sim.hardware.Hardware;
import edu.arhs.first1100.sim.hardware.Input;
import edu.arhs.first1100.sim.hardware.Output;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Joed
 */
public class RangeSensor extends Component {

    public RangeSensor(final int pingChannel, final int echoChannel) {
        outputs = new Output[1];
        outputs[0] = new Output() {

            @Override
            public int getChannel() {
                return pingChannel;
            }

            @Override
            public void set(double value) {
            }
        };
        inputs = new Input[1];
        inputs[0] = new Input() {

            @Override
            public int getChannel() {
                return echoChannel;
            }

            @Override
            public double get() {
                return getRange();
            }
        };
    }
    Arc rangeSensor;

    public Shape setParent(Hardware parent) {
        super.setParent(parent);
        rangeSensor = new Arc();
        rangeSensor.setCenterX(0);
        rangeSensor.setCenterY(0);
        rangeSensor.setRadiusX(5);
        rangeSensor.setRadiusY(5);
        rangeSensor.setType(ArcType.CHORD);
        rangeSensor.setStartAngle(270);
        rangeSensor.setLength(180);
        rangeSensor.setStrokeType(StrokeType.INSIDE);
        rangeSensor.setStroke(Color.web("white", 1.0f));
        rangeSensor.setStrokeWidth(1f);
        rangeSensor.setFill(Color.web("white", 1.0f));

        return rangeSensor;
    }

    protected Point2D pointInScene(double x, double y) {
        return parent.localToScene(rangeSensor.localToParent(x, y));
    }

    protected Shape getBeamShape() {
        Path path = new Path();
        Point2D p;
        p = pointInScene(0, 0);
        path.getElements().add(new MoveTo(p.getX(), p.getY()));
        p = pointInScene(300, 40);
        path.getElements().add(new LineTo(p.getX(), p.getY()));
        p = pointInScene(300, -40);
        path.getElements().add(new LineTo(p.getX(), p.getY()));
        path.getElements().add(new ClosePath());

        return path;
    }

    public double getRange() {
        Point2D origin = pointInScene(0, 0);
        return parent.getPlayfield().closestPoint(origin, getBeamShape());
    }
}
