package uk.ac.bradford.farmgame;

/**
 * A class to represent a Rock in the game. Used for the debris task that adds Rocks and Trees.
 * @author pault
 */
public class Rock extends Entity {
    /**
     * A constructor to create a new Rock object.
     * @param x the X coordinate for this Rock object.
     * @param y the Y coordinate for this Rock object.
     */
    public Rock(int x, int y) {
        setPosition(x, y);
    }
}
