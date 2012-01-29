/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.sim.playfield;

import edu.arhs.first1100.sim.Config;

/**
 *
 * @author Joed
 */
public class MazePlayfield extends WalledPlayfield {

    static final double vSize = Config.SCREEN_HEIGHT / 4;
    static final double hSize = Config.SCREEN_WIDTH / 8;
    static final double wallCoords[][] = {
        {0, 0, Config.SCREEN_WIDTH, 3},
        {0, 0, 3, Config.SCREEN_HEIGHT},
        {0, Config.SCREEN_HEIGHT - 3, Config.SCREEN_WIDTH, 3},
        {Config.SCREEN_WIDTH - 3, 0, 3, Config.SCREEN_HEIGHT},
        {0, vSize - 3, 6 * hSize, 3},
        {0, 2 * vSize - 3, 2 * hSize, 3},
        {4 * hSize, 2 * vSize - 3, 8 * hSize, 3},
        {hSize, 3 * vSize - 3, 4 * hSize, 3},
        {2 * hSize - 3, 3 * vSize, 3, 4 * vSize}
    };

    public MazePlayfield() {
        super(wallCoords);
    }
}
