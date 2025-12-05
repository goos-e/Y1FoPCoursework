package uk.ac.bradford.farmgame;

/**
 * A class to create Tile objects used in the game. A Tile object represents one single
 * tile in the game. Each Tile object has a type, defining their appearance and their
 * their purpose in the game.
 * 
 * @author prtrundl
 */
public class Tile {
    
    /**
     * A type for this Tile object. The type affects what is drawn to the screen
     * and is used for checking what kind of Tile it is in the game for e.g.
     * changing tools, tilling dirt etc. The type must use one of the values from the
     * TileType enumeration defined below.
     */
    private TileType type;
    
    /**
     * A flag  for the Tile object. This flag determines whether the Tile object 
     * be collided with, or if entities can walk over it. Useful for TileTypes 
     * which should prevent movement ie WALL, HOE_BOX etc.
     */
    private boolean collidable;

    
    /**
     * An enumeration type to restrict the type of Tile objects to one from a set
     * of fixed values. Each type has an associated graphic for drawing to the
     * screen which is set when the Tile object constructor is called.
     */
    public enum TileType {
        AXE_BOX, BED, CROP, DIRT,
        HOE_BOX, HOUSE_FLOOR, PICKAXE_BOX, SEED_BOX,
        SOWED_DIRT, STONE_GROUND, TILLED_DIRT, WALL;
    }
    
    /**
     * Constructor for creating Tile objects. You must pass one of the permitted
     * values from the TileType enumeration into this constructor when you call it,
     * for example by passing the value TileType.BACKGROUND between the brackets of the
     * call to the constructor. Attribute collidable defaults to false. 
     * @param t the type for this Tile object; a value from the TileType enumeration
     */
    public Tile(TileType t) {
        type = t;
        switch (t){
            case TileType.WALL:
            case TileType.HOE_BOX:
            case TileType.SEED_BOX:
            case TileType.AXE_BOX:
            case TileType.PICKAXE_BOX:
            case TileType.BED:
                collidable = true;
                break;
            default:
                collidable = false;
        }
    }
    
    /**
     * Overloaded constructor to set attribute collidable
     */
    public Tile(TileType t, boolean c) {
        type = t;
        collidable = c;
    }
        
    /**
     * Get the type for this Tile object. The value will be one of those
     * defined in the TileType enumeration in the Tile class, e.g. TileType.DIRT
     * or TileType.WALL
     * @return the TIleType for this tile; one value from the TIleType enumeration defined above
     */
    public TileType getType() {
        return type;
    }
    
    /**
     * Sets the type for this Tile object. The value will be one of those defined
     * in the TileType enumeration in the Tile class, e.g. TileType.DIRT or
     * TileType.WALL
     * @param t the type to change this Tile object to; a value from the TileType
     * enumeration
     */
    public void setType(TileType t){
        type = t;
    }
    
    /**
     * Get the flag for whether Tile object is collidable or not.  
     * @return true if it IS collidable, false if NOT collidable
     */
    public boolean isCollidable(){
        return collidable;
    }
    
    
    /**
     * Sets the collidable state of tile 
     * @param c boolean flag for if tile is collidable; true if it is, false if not
     */
    public void setCollidable(boolean c){
        collidable = c;
    }
}
