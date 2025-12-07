package uk.ac.bradford.farmgame;

/**
 * The Entity class stores basic state information for the Player, Pest, Tree and
 * Rock classes in the game. It implements the position concept with X and Y
 * coordinates and allows movement of Entity objects.
 * 
 * @author prtrundl
 */
public abstract class Entity {
    
    /**
     * xPos is the current x position in the game for this entity. Together with the
     * yPos attribute this determines the position of this Entity when it is drawn to
     * the screen using the style X,Y. 0,0 is the top left tile in the level.
     * 1,0 is the tile to the right of 0,0. 0,1 is the tile below 0,0 etc.
     */
    private int xPos;
    
    /**
     * yPos is the current y position in the game for this entity. Together with the
     * xPos attribute this sets the position of this Entity when it is drawn to
     * the screen using the style X,Y. 0,0 is the top left tile in the level.
     * 1,0 is the tile to the right of 0,0. 0,1 is the tile below 0,0.
     */
    private int yPos;

    
    /**
     * position is the Vector object containing the x,y coordinates in the game
     * for this entity. 
     */
    private Vector position = new Vector(xPos, yPos);
    
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
    
    public Vector getPosition(){
        return this.position;
    }

    public void setPosition(Vector v){
        this.position = v;
    }
}
