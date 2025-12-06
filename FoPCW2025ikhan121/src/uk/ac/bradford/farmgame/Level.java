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
    
    public Level(int w, int h){
        this.WIDTH = w;
        this.HEIGHT = h;
        level = new Tile[this.WIDTH][this.HEIGHT];
    }
    
    /**
     * Helper method to replace all level[x][y] calls. Takes a tile type and vector.
     * @param t TileType to set the coordinate at coordinate v to
     * @param v Vector object with (x,y) coords to create new Tile at
     */
    private void placeTile(TileType t, Vector v){
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
    private void fillLine(TileType t, Vector v1, Vector v2){
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
     * Creates new Tile objects of type t for each coordinate in a circle defined by
     * the passed vector and integer arguments, mid-point and radius. 
     * @param t
     * @param mid
     * @param rad 
     */
    private void fillCircle(TileType t, Vector mid, int rad){
        
    }
        
    /**
     * Searches through the level array at O(n*m) complexity for all tiles that 
     * match the passed TileType, returning a 2D array of all coordinate pairs. 
     * @param t type to search level array for
     * @return int[][] 2D array of coordinate pairs of found tiles, empty if 
     * none found
     */
    private Vector[] findTiles(TileType t){
        List<Vector> coords = new ArrayList<>();
        
        for(int i = 0; i<this.WIDTH; i++){
            for (int j = 0; j<this.HEIGHT; j++){
                if(level[i][j].getType() == t){
                    coords.add(new Vector(i, j));
                }
            }
        }
        
        // Vector[] result = coords.toArray(new Vector[coords.size()]);
        Vector[] result = coords.toArray(Vector[]::new);
        
        return result;
    }
    
}
