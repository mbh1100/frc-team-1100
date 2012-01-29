/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.playfield;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Joed
 */
public class WalledPlayfield extends Playfield {

    Line walls[];
    static final int WALL_THICKNESS = 3;

    public WalledPlayfield(double[][] wallCoords) {
        int i;
        for (i = 0; i < wallCoords.length; i++) {
            Rectangle wall = new Rectangle(wallCoords[i][0], wallCoords[i][1],
                    wallCoords[i][2], wallCoords[i][3]);
            wall.setFill(Color.web("white", 1.0f));
            addObstacle(wall);
        }
    }
}
