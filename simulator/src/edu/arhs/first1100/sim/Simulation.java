/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim;

import edu.arhs.first1100.sim.hardware.Hardware;
import edu.arhs.first1100.sim.hardware.Input;
import edu.arhs.first1100.sim.hardware.JoystickSim;
import edu.arhs.first1100.sim.hardware.Output;
import edu.arhs.first1100.sim.playfield.Playfield;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 *
 * @author Joed
 */
public abstract class Simulation extends Application {

    static Controller controller;
    static Hardware hw;
    static Playfield playfield;
    static KeyCode[] fourKeys = {KeyCode.UP, null, null, KeyCode.DOWN,
                                 KeyCode.LEFT, null, null, KeyCode.RIGHT,
                                 KeyCode.SHIFT, KeyCode.CONTROL,
                                 KeyCode.X, KeyCode.SPACE};
    static KeyCode[] leftKeys = {KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F};
    static KeyCode[] rightKeys = {KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.SEMICOLON};
    static JoystickSim[] joysticks = {new JoystickSim(fourKeys),
                                      new JoystickSim(leftKeys),
                                      new JoystickSim(rightKeys)};
    static JoystickSim nullStick = new JoystickSim();
    
    static public Output getOutput(int channel)
    {
        return hw.getOutput(channel);
    }
    
    static public Input getInput(int channel)
    {
        return hw.getInput(channel);
    }
    
    static public JoystickSim getJoystickSim(int which)
    {
            if(which < 0 || which > 2) {
                System.err.println("WARNING: Attempt to configure illegal joystick number " + which);
                        return nullStick;
                        
            }
        return joysticks[which];
    }

    public static void setController(Controller controller) {
        Simulation.controller = controller;
    }

    public static void setHardware(Hardware hw, double initialX, double initialY,
                                    double initialRotation) {
        Simulation.hw = hw;
        hw.position(initialX, initialY, initialRotation);
        if(Simulation.playfield != null) {
            Simulation.hw.setPlayfield(Simulation.playfield);
        }
    }

    public static void setPlayfield(Playfield playfield) {
        Simulation.playfield = playfield;
        if(Simulation.hw != null) {
            Simulation.hw.setPlayfield(playfield);
        }
    }
    
    public static Point2D vector(Point2D to, Point2D from) {
        return new Point2D(to.getX() - from.getX(),
                to.getY() - from.getY());
    }

    public static Point2D add(Point2D a, Point2D b) {
        return new Point2D(a.getX() + b.getX(), a.getY() + b.getY());
    }
    
    public static double dot(Point2D v1, Point2D v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public static double distanceToSegment(Point2D p, Point2D v, Point2D w) {
        double l = v.distance(w);
        if (l == 0.0) {
            return p.distance(v);   // v == w case
        }
        double t = dot(vector(p, v), vector(w, v)) / (l * l);
        if (t < 0.0) {
            return p.distance(v);
        } else if (t > 1.0) {
            return p.distance(w);  // Beyond the 'w' end of the segment
        }
        Point2D delta = vector(w, v);
        Point2D projection = new Point2D(v.getX() + t * delta.getX(),
                v.getY() + t * delta.getY());
        return p.distance(projection);
    }

    // Transforms the points in path into Node n's parent coordinates (destructively)
    public static void transformPath(Node n, Path path)
    {
        for (PathElement e : path.getElements()) {
            if (e instanceof MoveTo) {
                MoveTo m = (MoveTo) e;
                Point2D p = n.localToParent(m.getX(), m.getY());
                m.setX(p.getX());
                m.setY(p.getY());
            } else if (e instanceof LineTo) {
                LineTo l = (LineTo) e;
                Point2D p = n.localToParent(l.getX(), l.getY());
                l.setX(p.getX());
                l.setY(p.getY());
            } else if (e instanceof CubicCurveTo) {
                CubicCurveTo c = (CubicCurveTo) e;
                Point2D p = n.localToParent(c.getControlX1(), c.getControlY1());
                c.setControlX1(p.getX());
                c.setControlY1(p.getY());
                p = n.localToParent(c.getControlX2(), c.getControlY2());
                c.setControlX2(p.getX());
                c.setControlY2(p.getY());
                p = n.localToParent(c.getX(), c.getY());
                c.setX(p.getX());
                c.setY(p.getY());
            } else if (e instanceof ClosePath) {
            } else {
                System.err.println("Don't handle this type of shape");
                Platform.exit();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group(playfield, hw);
        Rectangle clip = new Rectangle(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        clip.setSmooth(false);
        root.setClip(clip);
        ImageView background = new ImageView();
        background.setFocusTraversable(true);
        background.setFitWidth(Config.SCREEN_WIDTH);
        background.setFitHeight(Config.SCREEN_HEIGHT);

        background.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent ke) {
                if ((ke.getCode() == KeyCode.POWER) || (ke.getCode() == KeyCode.ESCAPE)) {
                    controller.die();
                }
                for(JoystickSim j : joysticks) {
                    j.handleKey(ke);
                }
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent ke) {
                for(JoystickSim j : joysticks) {
                    j.handleKeyUp(ke);
                }
            }
        });

        root.getChildren().add(background);
        stage.setTitle("ARHS Team1100 Robot Simulator");
        stage.setResizable(false);
        stage.setWidth(Config.SCREEN_WIDTH + 2 * Config.WINDOW_BORDER);
        stage.setHeight(Config.SCREEN_HEIGHT + 4 * Config.WINDOW_BORDER + Config.TITLE_BAR_HEIGHT);
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();

        controller.init();
        controller.start();
    }
    
    public void stop()
    {
        if(controller != null) {
            controller.die();
        }
    }
}
