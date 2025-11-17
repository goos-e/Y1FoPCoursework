package uk.ac.bradford.farmgame;

/**
 * A class to represent a Tree in the game. Used for the debris task that adds Rocks and Trees.
 * @author pault
 */
public class Tree extends Entity {
    /**
     * A constructor to create a new Tree object.
     * @param x the X coordinate for this Tree object.
     * @param y the Y coordinate for this Tree object.
     */
    public Tree(int x, int y) {
        setPosition(x, y);
    }
}
