package uk.ac.bradford.farmgame.Entities;

import uk.ac.bradford.farmgame.Vector;

/**
 * The Entity class stores basic state information for the Player, Pest, Tree and
 * Rock classes in the game. It implements the position concept with X and Y
 * coordinates and allows movement of Entity objects.
 * 
 * @author prtrundl
 */
public abstract class Entity {
    
    /**
     * Total health of this entity, 100 is 'full' and 0 is 'dead'
     */
    private double health;
    
    /**
     * position is the Vector object containing the x,y coordinates in the game
     * for this entity. 
     */
    protected Vector position;
    
    /**
     * This method returns the current X position for this entity in the game
     * @return The X co-ordinate of this Entity in the game
     */
    public int getX() {
        return position.getX();
    }
    
    /**
     * This method returns the current Y position for this entity in the game
     * @return The Y co-ordinate of this Entity in the game
     */
    public int getY() {
        return position.getY();
    }
    
    /**
     * This method returns the current X,Y coordinate pair for this entity in the game
     * @return The X,Y coordinate as a Vector 
     */
    public Vector getPosition(){
        return this.position;
    }

    /**
     * This method sets the current X,Y coordinate pair of this entity as the 
     * passed vector parameter, v
     * @param v the x,y coordinate as a vector, to set the entity's position to
     */
    public void setPosition(Vector v){
        this.position = v;
    }
    
    public double getHealth(){
        return this.health;
    }
    
    public void setHealth(double health){
        this.health = health;
    }
    
    /**
     * Deals 'damage' to the entity by subtracting a value 'd' from its health
     * @param d The amount to subtract from the health of the entity
     */
    public void hurtEntity(double d){
        this.health  = this.health - d;
    }
}
