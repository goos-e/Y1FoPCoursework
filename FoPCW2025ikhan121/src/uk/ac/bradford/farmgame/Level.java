/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.farmgame;
import java.util.ArrayList;
import java.util.List;
import uk.ac.bradford.farmgame.Tile.TileType;
/**
 *
 * @author goose
 */
public class Level {
    private Tile[][] level;
    private int LEVEL_WIDTH = 0;
    private int LEVEL_HEIGHT = 0;
    
    
    /**
     * Constructor for Level object, creates an empty Tile[][] array of dimension w * h
     * @param width width (number of columns; x axis) of the 2d tile array
     * @param height height (number of rows; y axis) of the 2d tile array
     */
    public Level(int width, int height){
        this.LEVEL_WIDTH = width;
        this.LEVEL_HEIGHT = height;
        level = new Tile[this.LEVEL_WIDTH][this.LEVEL_HEIGHT];
    }
    
    /**
     * get method for the Tile object at coordinate (x,y) according to Vector v
     * @param v Vector object for the Tile object at coords (x,y)
     * @return the Tile object at level[x][y]
     */
    public Tile getTile(Vector v){
        int x = v.getX();
        int y = v.getY();
        
        return level[x][y];
    }
    
    /**
     * Helper method to replace all level[x][y] calls. Takes a tile type and vector.
     * @param t TileType to set the coordinate at coordinate v to
     * @param v Vector object with (x,y) coords to create new Tile at
     */
    public void fillTile(TileType t, Vector v){
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
    public void fillLine(TileType t, Vector v1, Vector v2){
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
    public void fillRect(TileType t, Vector v1, Vector v2){
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
    public void fillCircle(TileType t, Vector mid, int rad){
        
    }
    
    /**
     * Searches through the level array at O(n*m) complexity for all tiles that 
     * match the passed TileType, returning a 2D array of all coordinate pairs. 
     * @param t type to search level array for
     * @return int[][] 2D array of coordinate pairs of found tiles, empty if 
     * none found
     */
    public Vector[] findTiles(TileType t){
        List<Vector> coords = new ArrayList<>();
        
        for(int i = 0; i<this.LEVEL_WIDTH; i++){
            for (int j = 0; j<this.LEVEL_HEIGHT; j++){
                if(level[i][j].getType() == t){
                    coords.add(new Vector(i, j));
                }
            }
        }
        
        // Vector[] result = coords.toArray(new Vector[coords.size()]);
        Vector[] result = coords.toArray(Vector[]::new);
        
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
    public Vector findClosest(TileType t, Vector v1){
        Vector[] tiles = findTiles(t);
        
        if (tiles!= null && tiles.length > 0){
            
            Vector currentCoords = new Vector(v1.getX(), v1.getY());
            Vector closestCoords = new Vector();
            
            
            int shortestDistance = 999;
            
            for (Vector crop : tiles){
                Vector delta = currentCoords.sub(crop);
                
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
    public boolean isWithinLevel(Vector v){
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
    
    public Tile[][] asArray(){
        return level;
    }
}
