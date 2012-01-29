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
public class SimpleWalledPlayfield extends WalledPlayfield {
    static final double wallCoords[][] = {
        {0, 0, Config.SCREEN_WIDTH, 3},
        {0, 0, 3, Config.SCREEN_HEIGHT},
        {0, Config.SCREEN_HEIGHT-3, Config.SCREEN_WIDTH, 3},
        {Config.SCREEN_WIDTH-3, 0, 3, Config.SCREEN_HEIGHT},
        
        {0, Config.SCREEN_HEIGHT/2-3, Config.SCREEN_WIDTH/2, 3}
    };
    
    public SimpleWalledPlayfield()
    {
        super(wallCoords);
    }
}
