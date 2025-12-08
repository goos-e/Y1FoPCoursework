package uk.ac.bradford.farmgame.entity;

import uk.ac.bradford.farmgame.Vec2;

/**
 * A class to represent a Tree in the game. Used for the debris task that adds Rocks and Trees.
 * @author pault
 */
public class Tree extends Entity {
    /**
     * A constructor to create a new Tree object.
     * 
     * @param v the starting position of this Tree in the game defined by a 
     * Vector object (x,y)
     */
    public Tree(Vec2 v) {
        setPosition(v);
        setHealth(100);
    }
}
