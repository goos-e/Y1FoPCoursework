package uk.ac.bradford.farmgame.entity;

import uk.ac.bradford.farmgame.Vec2;

/**
 * A class to represent a Rock in the game. Used for the debris task that adds Rocks and Trees.
 * @author pault
 */
public class Rock extends Entity {
    /**
     * A constructor to create a new Rock object.
     * 
     * @param v the starting position of this Rock in the game defined by a 
     * Vector object (x,y)
     */
    public Rock(Vec2 v) {
        super(v, 200, 200);
    }
}
