package uk.ac.bradford.farmgame.Entities;

import uk.ac.bradford.farmgame.Entities.Entity;
import uk.ac.bradford.farmgame.Vector;

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
    public Tree(Vector v) {
        setPosition(v);
        setHealth(100);
    }
}
