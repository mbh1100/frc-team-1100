/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.playfield;

import edu.arhs.first1100.sim.Simulation;
import java.util.LinkedList;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Joed
 */
public class Playfield extends Group {

    protected LinkedList<Shape> obstacles;

    public Playfield() {
        obstacles = new LinkedList<Shape>();
    }

    public void addObstacle(Shape s) {
        obstacles.add(s);
        getChildren().add(s);

    }

    public boolean collision(Node n, Shape s) {
        //
        // There is no way, yet, in JavaFX to intersect translated
        //  shapes, so I use this hack.  I know that Shape.intersects()
        //  will actually return a Path, so I cast it.  Then I walk
        //  through the elements of that path and manually transform
        //  their parameters, and test intersection with the translated path
        //
        // This currently works only because the obstacles are simple Shapes
        //  that have an internal X-Y position (like Rectangles), and
        //  Shape.intersects() respects those internal values (but not the
        //  translateX and translateY values).  If we add more complicated
        //  obstacles, or if obstacles can move (via setTranslateX/Y), then
        //  we'll have to get the altered paths for the obstacles, too.
        //

        Path foo = (Path) Shape.intersect(s, s);
        Simulation.transformPath(n, foo);
        for (Shape o : obstacles) {
            Shape isect = Shape.intersect(foo, o);
            if (!isect.getBoundsInLocal().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public double closestPoint(Point2D ref, Shape s) {
        double min = Double.MAX_VALUE;
        for (Shape o : obstacles) {
            Shape isect = Shape.intersect(s, o);
            Bounds b = isect.getBoundsInLocal();
            if (!b.isEmpty()) {
                Path p = (Path) isect;
                Point2D firstPoint = null, lastPoint = null;
                boolean started = false;
                for (PathElement e : p.getElements()) {
                    if (e instanceof MoveTo) {
                        MoveTo m = (MoveTo) e;
                        lastPoint = new Point2D(m.getX(), m.getY());
                        if (!started) {
                            firstPoint = lastPoint;
                            started = true;
                        }
                    } else if (e instanceof LineTo) {
                        LineTo l = (LineTo) e;
                        Point2D nextPoint = new Point2D(l.getX(), l.getY());
                        double d = Simulation.distanceToSegment(ref, lastPoint, nextPoint);
                        lastPoint = nextPoint;
                        if (d < min) {
                            min = d;
                        }
                    } else if (e instanceof CubicCurveTo) {
                        // This is wrong.  It just uses the line segment from start to end.
                        CubicCurveTo c = (CubicCurveTo) e;
                        Point2D nextPoint = new Point2D(c.getX(), c.getY());
                        double d = Simulation.distanceToSegment(ref, lastPoint, nextPoint);
                        lastPoint = nextPoint;
                        if (d < min) {
                            min = d;
                        }
                    } else if (e instanceof ClosePath) {
                        double d = Simulation.distanceToSegment(ref, lastPoint, firstPoint);
                        if (d < min) {
                            min = d;
                        }
                    } else {
                        System.err.println("Don't handle this type of shape");
                        Platform.exit();
                    }
                }
            }
        }
        return min;
    }
}
