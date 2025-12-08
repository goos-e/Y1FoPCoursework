package uk.ac.bradford.farmgame.entity;

import uk.ac.bradford.farmgame.Vec2;

/**The Pest class is a subclass of Entity and implements the
 * pest concept in the game. The pest moves towards crops once they have grown
 * and "eats" them unless stopped by the player.
 *
 * @author prtrundl
 */
public class Pest extends Entity {
    
    /**
     * This constructor is used to create a Pest object to use in the game.
     * 
     * @param v the starting position of this Pest in the game defined by a 
     * Vector object (x,y)
     */
    public Pest(Vec2 v) {
        setPosition(v);
        setHealth(100);
    }
}
