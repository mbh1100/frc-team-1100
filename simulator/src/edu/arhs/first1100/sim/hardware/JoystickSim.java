/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.hardware;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Joed
 */
public class JoystickSim {

    private KeyCode keys[];
    private KeyCode lastYKey = null;
    private KeyCode lastXKey = null;
    private double x, y;
    private boolean buttons[];
    public static final int A = 0;
    public static final int B = 1;
    public static final int X = 2;
    public static final int Y = 3;
    private static KeyCode[] noKeys = {};

    public JoystickSim() {
        this(noKeys);
    }

    public JoystickSim(KeyCode[] keys) {
        this.keys = keys;
        buttons = new boolean[4];
        for (int i = 0; i < 4; i++) {
            buttons[i] = false;
        }
    }

    protected void setYValue(double y) {
        this.y = y;
    }

    public double getYValue() {
        return y;
    }

    protected void setXValue(double x) {
        this.x = x;
    }

    public double getXValue() {
        return x;
    }

    public boolean isButtonPressed(int b) {
        if (b < A || b > Y) {
            return false;
        }
        return buttons[b];
    }

    protected void setButton(int b) {
        buttons[b] = true;
    }

    protected void clearButton(int b) {
        buttons[b] = false;
    }

    public void handleKey(KeyEvent ke) {
        if (keys.length > 0 && ke.getCode() == keys[0]) {
            setYValue(1.0);
            lastYKey = ke.getCode();
        } else if (keys.length > 1 && ke.getCode() == keys[1]) {
            setYValue(0.5);
            lastYKey = ke.getCode();
        } else if (keys.length > 2 && ke.getCode() == keys[2]) {
            setYValue(-0.5);
            lastYKey = ke.getCode();
        } else if (keys.length > 3 && ke.getCode() == keys[3]) {
            setYValue(-1.0);
            lastYKey = ke.getCode();
        } else if (keys.length > 4 && ke.getCode() == keys[4]) {
            setXValue(1.0);
            lastXKey = ke.getCode();
        } else if (keys.length > 5 && ke.getCode() == keys[5]) {
            setXValue(0.5);
            lastXKey = ke.getCode();
        } else if (keys.length > 6 && ke.getCode() == keys[6]) {
            setXValue(-0.5);
            lastXKey = ke.getCode();
        } else if (keys.length > 7 && ke.getCode() == keys[7]) {
            setXValue(-1.0);
            lastXKey = ke.getCode();
        } else if (keys.length > 8 && ke.getCode() == keys[8]) {
            setButton(A);
        } else if (keys.length > 9 && ke.getCode() == keys[9]) {
            setButton(B);
        } else if (keys.length > 10 && ke.getCode() == keys[10]) {
            setButton(X);
        } else if (keys.length > 11 && ke.getCode() == keys[11]) {
            setButton(Y);
        }
    }

    public void handleKeyUp(KeyEvent ke) {
        if (ke.getCode() == lastYKey) {
            setYValue(0);
            lastYKey = null;
        } else if (ke.getCode() == lastXKey) {
            setXValue(0);
            lastXKey = null;
        } else if (keys.length > 8 && ke.getCode() == keys[8]) {
            clearButton(A);
        } else if (keys.length > 9 && ke.getCode() == keys[9]) {
            clearButton(B);
        } else if (keys.length > 10 && ke.getCode() == keys[10]) {
            clearButton(X);
        } else if (keys.length > 11 && ke.getCode() == keys[11]) {
            clearButton(Y);
        }
    }
}
