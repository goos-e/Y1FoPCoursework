/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.farmgame;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import uk.ac.bradford.farmgame.Tile.TileType;
import uk.ac.bradford.farmgame.entity.*;

/**
 *
 * @author goose
 */
public class Level {
    private Vec2 globalPosition;
    private Tile[][] level;
    private Random rng;
    private EntityManager entities;
    
    
    private int LEVEL_WIDTH;
    private int LEVEL_HEIGHT;
    
    
    /**
     * Constructor for Level object, creates an empty Tile[][] array of dimension w * h
     * @param size: Vec2 containing the (width, height) of the level
     * @param rng: seeded Random object 
     */
    public Level(Vec2 size, Vec2 position, Random rng){
        this.LEVEL_WIDTH = size.getX();
        this.LEVEL_HEIGHT = size.getY();
        this.globalPosition = position;
        this.rng = rng;
        
        level = new Tile[this.LEVEL_WIDTH][this.LEVEL_HEIGHT];
        this.entities = new EntityManager();
    }
        
    public void init(){
        fillRect(TileType.STONE_GROUND, new Vec2(), new Vec2(this.LEVEL_WIDTH, this.LEVEL_HEIGHT));
        
    }
    /**
     * This method should add debris to the game in the form of Tree and Rock objects.
     * It should instantiate the debris array with a sensible length and then fill
     * the array with instances of the Tree and Rock classes that you create in this
     * method. For top marks in this task the objects should be randomly placed so
     * that their position is different each game. Ideally they should not be placed
     * in a way that prevents the player from playing the game (e.g. should not be placed
     * in a way that blocks the door to the farmhouse!)
     * Rocks and Trees should block player movement; add code to your newest
     * player movement method (depends on which tasks you have already completed!)
     */
    public void addDebris() {
       
        int maxDebris = 100;
        
        for(int i = 0; i<maxDebris; i++){
            int x = rng.nextInt(1, LEVEL_WIDTH-1);
            int y = rng.nextInt(1, LEVEL_HEIGHT-1);
            
            Vec2 v = new Vec2(x, y);
            
            if(isValidSpawn(v)){
                if(rng.nextBoolean()){
                    entities.addEntity(new Tree(v));
                }
                else{
                    entities.addEntity(new Rock(v));
                }
            }
        }
    }
    
    
    /**
     * get method for the Tile object at coordinate (x,y) according to Vector v
     * @param v Vector object for the Tile object at coords (x,y)
     * @return the Tile object at level[x][y]
     */
    public Tile getTile(Vec2 v){
        if(!isWithinLevel(v)){return null;}
        
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
        if(!isWithinLevel(new Vec2(x, y))){return null;}
        
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
        Vec2 tileCoord;
        if(v1.getX() == v2.getX()){
            // draw vertical line; keep the x value the same as v1.getX()
            for(int j = v1.getY(); j<=v2.getY(); j++){
                tileCoord = v1.withX(j);
                fillTile(t, tileCoord);
            }
        }
        else{
            // draw horizontal; keep the y value the same as v1.getY()
            for(int i = v1.getX(); i<=v2.getX(); i++){
                tileCoord = v1.withY(i);
                fillTile(t, tileCoord);
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
        Vec2 tileCoord;
        for (int i = v1.getX(); i < v2.getX(); i++){ //cols (x)
            for (int j = v1.getY(); j < v2.getY();  j++){ //rows (y)
                tileCoord = new Vec2(i, j);
                fillTile(t, tileCoord);
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
        /*
        circle equation used: (x - a)^2 + (y - b) <= r, where (a,b) is centre
        note the sign '<=' indicates the points ON and INSIDE the circle
        cardinal points are: (a+r, b), (a-r, b), (a, b+r), (a, b-r)
        every point p in circle MUST satisfy:
            1. isWithinLevel(p)
            2. (centre - rad) <= p <= (centre + rad)
            3. the above equation
        */
        
        int a = mid.getX();
        int b = mid.getY();
        
        for(int i = a-rad; i <= a+rad; i++){
            for(int j = b-rad; j <= j+rad; j++){
                Vec2 currentV = new Vec2(i, j);
                
                if(!isWithinLevel(currentV)){continue;}
                
                int dx = (i-a);
                int dy = (j-b);
                
                if( dx*dx + dy*dy <= rad*rad){
                    fillTile(t, currentV);
                }
            }
        }
    }
    
    /**
     * Searches through the level array at O(n*m) complexity for all tiles that 
     * match the passed TileType, returning a 2D array of all coordinate pairs. 
     * @param t type to search level array for
     * @return int[][] 2D array of coordinate pairs of found tiles, empty if 
     * none found
     */
    public Vec2[] findTiles(TileType type){
        ArrayList<Vec2> coords = new ArrayList<>();
        
        for(int i = 0; i<this.LEVEL_WIDTH; i++){
            for (int j = 0; j<this.LEVEL_HEIGHT; j++){
                Tile currentTile = getTile(i, j);
                if(currentTile.getType() == type){
                    coords.add(new Vec2(i, j));
                }
            }
        }
        
        // Vector[] result = coords.toArray(new Vector[coords.size()]);
        Vec2[] result = coords.toArray(Vec2[]::new);
        if(result.length > 0){
            return result;
        }
        else{
            return null;
        }
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
        if(v == null){return false;}
        
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
        if(v == null){return false;}
        
        return (isWithinLevel(v) && !getTile(v).isCollidable() && !entities.hasEntityAt(v));
    }
    
    /**
     * Check if the location is a valid spawn location. At present, this checks 
     * if the tile beneath or adjacent are of type HOUSE_FLOOR, or DOOR. This
     * can be updated to include more 'no spawning' tile types. 
     * @param v
     * @return 
     */
    public boolean isValidSpawn(Vec2 v){
        // very neat thing i found that lets me store a logic function as a variable
        // i couldve just made into a separate function, but ive never seen this before
        Predicate<TileType> canEntitySpawnHere = t -> (
                t != TileType.HOUSE_FLOOR &&
                t != TileType.DOOR
                );
        
        
        if(v == null){return false;}
        if(!isValid(v)){return false;}
        
        
        TileType type = getTile(v).getType();
        Vec2[] adjacent = v.getNeighbours4();
        
        boolean valid = canEntitySpawnHere.test(type);
        
        if(valid){
            for(Vec2 adj : adjacent){
                if(!isValid(adj)){continue;}
                
                TileType adjType = getTile(adj).getType();
                if(!canEntitySpawnHere.test(adjType)){
                    valid = false;
                    break;
                }
            }
        }
        
        return valid;
    }
    
        
    /**
     * Randomly generates a Vec2 coordinate for a point on the edge of the map
     * that is valid for non-static entities to stand on.
     * @return Vec2 edgePoint containing x,y coordinates of a point on the edge of the map
     */
    public Vec2 getPointOnEdge(){
        Vec2 edgePoint = null;
        
        while (!isValid(edgePoint)){
                switch(rng.nextInt(1,5)){
                    case 1->{
                        // (0, y) left
                        edgePoint = new Vec2(0, rng.nextInt(LEVEL_HEIGHT-1));
                        }
                    case 2->{
                        // (x, 0) top
                        edgePoint = new Vec2(rng.nextInt(LEVEL_WIDTH-1), 0);
                        }
                    case 3->{
                        // (LEVEL_WIDTH-1, y) right
                        edgePoint = new Vec2(LEVEL_WIDTH-1, rng.nextInt(LEVEL_HEIGHT-1));
                        }
                    case 4->{
                        // (x, LEVEL_HEIGHT-1) bottom
                        edgePoint = new Vec2(rng.nextInt(LEVEL_WIDTH-1), LEVEL_HEIGHT-1);
                        }
                }
            }
        
        return edgePoint;
    }
    
    /**
     * Generates the floor and walls of the house for generateEvenBetterFarm()
     */
    public void generateHouse(){
        // house floor generation
        // size (width, height) -> (x, y)
        Vec2 size = new Vec2(rng.nextInt(5, 7), rng.nextInt(4, 6));
        Vec2 topLeft = new Vec2(rng.nextInt(LEVEL_WIDTH-1-size.getX()),
                                        rng.nextInt(LEVEL_HEIGHT/2, LEVEL_HEIGHT-size.getY()));
        
        // calculate all corners
        Vec2 bottomRight = topLeft.add(size);
        Vec2 topRight = topLeft.add(size.getX(), 0);
        Vec2 bottomLeft = topLeft.add(0, size.getY());
        
        // door, bed and box vector coords
        Vec2 doorV = topLeft.add(size.getX() / 2, 0);
        Vec2 bedV = bottomRight.sub(1, 1);
        Vec2 boxV = doorV.add(1,1);
        
        // floor
        fillRect(TileType.HOUSE_FLOOR, topLeft, topLeft.add(size));
        
        // walls
        fillLine(TileType.WALL, topLeft, topRight);
        fillLine(TileType.WALL, topLeft, bottomLeft);
        fillLine(TileType.WALL, topRight, bottomRight);
        fillLine(TileType.WALL, bottomLeft, bottomRight);
        
        // door
        fillTile(TileType.DOOR, doorV);
        
        // place bed
        fillTile(TileType.BED, bedV);
        
        // place boxes
        // this is the most JANK code ever
        fillTile(TileType.WATERINGCAN_BOX, doorV.up().right());
        fillTile(TileType.HOE_BOX, doorV.up().right().right());
        fillTile(TileType.SEED_BOX, doorV.up().right().right().right());
        fillTile(TileType.AXE_BOX, boxV);
        fillTile(TileType.PICKAXE_BOX, boxV.right());
    }
    

    /**
     * Generates the dirt patch for generateEvenBetterFarm() and places the 
     * hoe and seed box alongside
     */
    public void generateDirtPatch(){
        // farm plot generation 
        // size (width, height) -> (x, y)
        Vec2 size = new Vec2(rng.nextInt(5, 26), rng.nextInt(3,LEVEL_HEIGHT/2));
        Vec2 topLeft = new Vec2(rng.nextInt(LEVEL_WIDTH-size.getX()), 
                                       rng.nextInt(LEVEL_HEIGHT/2-size.getY()));

        fillRect(TileType.DIRT, topLeft, topLeft.add(size));
        //fillCircle(TileType.DIRT, new Vec2(12,12), 2);
        
        // place item boxes
        //level[topLeft.getX()+size.getX()/2][topLeft.getY()] = new Tile(TileType.HOE_BOX, true);
        //level[topLeft.getX()+size.getX()/2-2][topLeft.getY()] = new Tile(TileType.SEED_BOX, true);
        
        //Vec2 boxV = new Vec2(topLeft.getX()+size.getX()/2, topLeft.getY());
        //fillTile(TileType.HOE_BOX, boxV);
        //fillTile(TileType.WATERINGCAN_BOX, boxV.right());
        //fillTile(TileType.SEED_BOX, boxV.left());
    }
    
    public Vec2 getGlobalPosition(){
        return this.globalPosition;
    }
    
    public void setGlobalPosition(Vec2 v){
        this.globalPosition = v;
    }
    public EntityManager getEntities() {
        return entities;
    }
    
    public void setEntities(EntityManager entityManager) {
        this.entities = entityManager;
    }

    public int getWidth() {
        return LEVEL_WIDTH;
    }

    public void setWidth(int LEVEL_WIDTH) {
        this.LEVEL_WIDTH = LEVEL_WIDTH;
    }

    public int getHeight() {
        return LEVEL_HEIGHT;
    }

    public void setHeight(int LEVEL_HEIGHT) {
        this.LEVEL_HEIGHT = LEVEL_HEIGHT;
    }
    
}
