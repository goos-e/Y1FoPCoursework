/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.farmgame;
import java.util.ArrayList;
import java.util.List;
import uk.ac.bradford.farmgame.Tile.TileType;
import uk.ac.bradford.farmgame.entity.EntityManager;

/**
 *
 * @author goose
 */
public class Level {
    private Tile[][] level;
    public EntityManager entityManager;
    
    private int LEVEL_WIDTH;
    private int LEVEL_HEIGHT;
    
    
    /**
     * Constructor for Level object, creates an empty Tile[][] array of dimension w * h
     * @param width width (number of columns; x axis) of the 2d tile array
     * @param height height (number of rows; y axis) of the 2d tile array
     */
    public Level(int width, int height){
        this.LEVEL_WIDTH = width;
        this.LEVEL_HEIGHT = height;
        level = new Tile[this.LEVEL_WIDTH][this.LEVEL_HEIGHT];
        
        this.entityManager = new EntityManager();
    }
    
    /**
     * get method for the Tile object at coordinate (x,y) according to Vector v
     * @param v Vector object for the Tile object at coords (x,y)
     * @return the Tile object at level[x][y]
     */
    public Tile getTile(Vec2 v){
        int x = v.getX();
        int y = v.getY();
        
        return level[x][y];
    }
    
    /**
     * Gets the Tile object in the Tile[][] array coordinate (x,y) 
     * @param x x coordiante of Tile object in Tile[][] array
     * @param y y coordinate of Tile object in Tile[][] array
     * @return the Tile object at level[x][y]
     */
    public Tile getTile(int x, int y){
        
        return level[x][y];
    }
    
    /**
     * Helper method to replace all level[x][y] calls. Takes a tile type and vector.
     * @param t TileType to set the coordinate at coordinate v to
     * @param v Vector object with (x,y) coords to create new Tile at
     */
    public void fillTile(TileType t, Vec2 v){
        int x = v.getX();
        int y = v.getY();
        
        level[x][y] = new Tile(t);
    }
    
    /**
     * Creates new Tile objects of tile type t in a line from starting point v1 
     * to end point v2, determining whether line is horizontal or vertical by comparing x,y coords
     *  
     * @param t tile type to fill the line with
     * @param v1 vector object for starting point of line
     * @param v2 vector object for endpoint of line
     */
    public void fillLine(TileType t, Vec2 v1, Vec2 v2){
        if(v1.getX() == v2.getX()){
            // draw vertical line
            for(int j = v1.getY(); j<=v2.getY(); j++){
                level[v1.getX()][j] = new Tile(t);
            }
        }
        else{
            // draw horizontal
            for(int i = v1.getX(); i<=v2.getX(); i++){
                
                level[i][v1.getY()] = new Tile(t);
            }
        }
    }
    
    /**
     * Creates new Tile objects of type t for each coordinate in a rectangle defined by
     * the passed vector arguments, top-left and bottom-right corners
     * @param t tile type to fill the level with
     * @param v1 vector object for top-left coords of rectangle
     * @param v2 vector object for bottom right coords of rectangle
     */
    public void fillRect(TileType t, Vec2 v1, Vec2 v2){
        // default terrain generation: TileType.t
        for (int i = v1.getX(); i < v2.getX(); i++){ //cols (x)
            for (int j = v1.getY(); j < v2.getY();  j++){ //rows (y)
                level[i][j] = new Tile(t);
            }
        }
        // System.out.printf("v1 = (%d,%d)%nv2 = (%d,%d)%n",v1.getX(),v1.getY(),v2.getX(),v2.getY());
    }
    
    /**
     * Creates new Tile objects of type t for each coordinate in a circle defined by
     * the passed vector and integer arguments, mid-point and radius. 
     * @param t
     * @param mid
     * @param rad 
     */
    public void fillCircle(TileType t, Vec2 mid, int rad){
        
    }
    
    /**
     * Searches through the level array at O(n*m) complexity for all tiles that 
     * match the passed TileType, returning a 2D array of all coordinate pairs. 
     * @param t type to search level array for
     * @return int[][] 2D array of coordinate pairs of found tiles, empty if 
     * none found
     */
    public Vec2[] findTiles(TileType t){
        List<Vec2> coords = new ArrayList<>();
        
        for(int i = 0; i<this.LEVEL_WIDTH; i++){
            for (int j = 0; j<this.LEVEL_HEIGHT; j++){
                if(level[i][j].getType() == t){
                    coords.add(new Vec2(i, j));
                }
            }
        }
        
        // Vector[] result = coords.toArray(new Vector[coords.size()]);
        Vec2[] result = coords.toArray(Vec2[]::new);
        
        return result;
    }
    
    /**
     * Finds the coordinates of the closest Tile with type t, with respect to 
     * coordinate v1, and returns as a vector object. Utilises findTiles() to 
     * create a Vector[] array, which is then searched.
     * 
     * @param t Tile.TileType to search
     * @param v1 position to search from for closest
     * @return Vector object with x,y coordinates of closest tile with type t
     */
    public Vec2 findClosest(TileType t, Vec2 v1){
        Vec2[] tiles = findTiles(t);
        
        if (tiles!= null && tiles.length > 0){
            
            Vec2 currentCoords = new Vec2(v1.getX(), v1.getY());
            Vec2 closestCoords = new Vec2();
            
            
            int shortestDistance = 999;
            
            for (Vec2 crop : tiles){
                Vec2 delta = currentCoords.sub(crop);
                
                if (shortestDistance > delta.mag()){
                    closestCoords = crop;
                    shortestDistance = delta.mag();
                }
            }
            
            return closestCoords;
        }
        return null;
    }    
    
    /**
     * Checks whether coordinate pair exists in the level array by boundaries 
     * defined by LEVEL_HEIGHT and LEVEL_WIDTH
     * @param v vector containing coordinates to check
     * @return false if the tile coordinate exceeds the map, true if not
     */
    public boolean isWithinLevel(Vec2 v){
        if(v == null){
            return false;
        }
        int x = v.getX();
        int y = v.getY();
        
        if( x<0 || LEVEL_WIDTH-1<x || y<0 || LEVEL_HEIGHT-1<y){
            return false;
        }
        else{
            return true;
        }
    }
    
    public Tile[][] toArray(){
        return level;
    }    
    
    /**
     * Check if coordinate location on level is valid for entity to stand on by 
     * checking if location exists on map, and if yes, is the tile at that location 
     * collidable. 
     * 
     * @param v vector containing coordinates to check
     * @return true if coordinates are valid for movement
     */
    public boolean isValid(Vec2 v){
        if(v == null){
            return false;
        }
        
        return (isWithinLevel(v) && !getTile(v).isCollidable() && !entityManager.hasEntityAt(v));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public int getLEVEL_WIDTH() {
        return LEVEL_WIDTH;
    }

    public void setLEVEL_WIDTH(int LEVEL_WIDTH) {
        this.LEVEL_WIDTH = LEVEL_WIDTH;
    }

    public int getLEVEL_HEIGHT() {
        return LEVEL_HEIGHT;
    }

    public void setLEVEL_HEIGHT(int LEVEL_HEIGHT) {
        this.LEVEL_HEIGHT = LEVEL_HEIGHT;
    }
    
}
