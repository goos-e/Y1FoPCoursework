package uk.ac.bradford.farmgame;

import uk.ac.bradford.farmgame.Entities.*;
import uk.ac.bradford.farmgame.Items.*;

import java.util.Random;
import uk.ac.bradford.farmgame.Tile.TileType;

/**
 * The GameEngine class is responsible for managing information about the game,
 * creating the level, the player and pest, as well as updating information when
 * a key is pressed (processed by the InputHandler) while the game is running.
 *
 * @author prtrundl
 */
public class GameEngine {

    /**
     * The width of the level, measured in tiles. Changing this may cause the
     * display to draw incorrectly, and as a minimum the size of the GUI would
     * need to be adjusted.
     */
    public static final int LEVEL_WIDTH = 36;

    /**
     * The height of the level, measured in tiles. Changing this may cause the
     * display to draw incorrectly, and as a minimum the size of the GUI would
     * need to be adjusted.
     */
    public static final int LEVEL_HEIGHT = 20;
    
    /**
     * Width and height of level as a Vector object for indexing level array.
     */
    private static final Vector LEVEL_SIZE = new Vector(LEVEL_WIDTH, LEVEL_HEIGHT);
    
    /**
     * A random number generator that can be used to include randomised choices
     * in the creation of levels, in choosing places to place the player and other
     * objects, and to randomise movement etc. Passing an integer (e.g. 123) to
     * the constructor called here will give fixed results - the same numbers
     * will be generated every time WHICH CAN BE VERY USEFUL FOR TESTING AND
     * BUGFIXING!
     */
    private Random rng = new Random();

    /**
     * The current turn number. Increased by one every turn. Used to control
     * effects that only occur on certain turn numbers.
     */
    private int turnNumber = 1;

    /**
     * The amount of money the player has in the current game. Money is gained by
     * harvesting crops.
     */
    private int money = 0;

    /**
     * The GUI associated with this GameEngine object. This link allows the
     * engine to pass level and entity information to the GUI to be drawn.
     */
    private GameGUI gui;

    /**
     * The 2 dimensional array of tiles that represent the current level. The
     * size of this array should use the LEVEL_WIDTH (X dimension!) and LEVEL_HEIGHT
     * (Y dimension!) attributes when it is created. This is the array that is used
     * to draw images to the screen by the GUI class, and by you to check what a specific
     * Tile contains by checking the content of specific elements of the array and
     * using the getType() method to determine the type of the Tile.
     * 
     * For example:
     * 
     * level[10][15].getType() == TileType.DIRT
     * 
     * would return the value true if the tile at position 10,15 was a DIRT tile.
     * 
     * 
     * ive changed this but yet to amend the javadoc: forgive me
     */
    private Level level;
    
    /**
     * A Player object that is the current player. This object stores the state
     * information for the player, including the current position
     * (which is a pair of co-ordinates that corresponds to a tile in the
     * current level - see the Entity class for more information on the
     * co-ordinate system used as well as the coursework specification
     * document).
     */
    private Player player;

    /**
     * A Pest object used to create the pest when crops grow. The object
     * has position information stored via its attributes, and methods to check
     * and update the position of the pest.
     */
    private Pest pest;

    /**
     * An array of Entity objects that is used to store Tree and Rock objects for
     * one of the coursework tasks. New Tree and Rock objects should be created and
     * stored in this array for e.g. checking player movement as these objects
     * should block player movement into the tile that they are in.
     */
    private Entity[] debris;
    
    /**
     * Constructor that creates a GameEngine object and connects it with a
     * GameGUI object.
     *
     * @param gui The GameGUI object that this engine will pass information to
     * in order to draw levels and entities to the screen.
     */
    public GameEngine(GameGUI gui) {
        this.gui = gui;
    }

    /**
     * Creates a Player object in the game. This method should instantiate the
     * Player class by creating an object using the appropriate constructor. The
     * created object should then be assigned to the "player" attribute of this
     * class.
     *
     * This method should use fixed X and Y values for the player's position.
     */
    private void createPlayer() {
        Vector playerSpawnPoint = new Vector(-1, -1);
        
        while(!isValid(playerSpawnPoint)){
            int x = rng.nextInt(0, LEVEL_WIDTH);
            int y = rng.nextInt(0, LEVEL_HEIGHT);
            
            playerSpawnPoint = new Vector(x, y);
        }
        
        player = new Player(playerSpawnPoint);
    }
    
    /**
     * Handles the movement of the player when attempting to move in the game.
     * This method is automatically called by the GameInputHandler class when
     * the user has pressed one of the keys on the keyboard. Your code should
     * change the X and Y position of the player to place them in the correct tile
     * based on the direction of movement, i.e. changing their X or Y position by
     * +1 or -1. You will need to use the relevant method from the Player/Entity
     * classes to find the current position of the player, and set new values
     * for the X and Y position by using another method and passing it updated values.
     * @param direction the direction of movement based on the arrow key that was pressed:
     * 1 is up, 2 is right, 3 is down and 4 is left.
     */
    public void movePlayer(int direction) {
        // CODE IS DEPRECATED AND CAUSES SYNTAX ERRORS
        /*
        int currentX = player.getX();
        int currentY = player.getY();
        
        switch(direction){
            case 1:
                // up
                player.setPosition(currentX, currentY-1);
                break;
            case 2:
                // right
                player.setPosition(currentX+1, currentY);
                break;
            case 3:
                // down
                player.setPosition(currentX, currentY+1);
                break;
            case 4:
                // left
                player.setPosition(currentX-1, currentY);
                break;
        }
        */
    }
    
    /**
     * Generates a new level. This method should instantiate the level array, and
     * then fill it with Tile objects that are created inside this method.
     * This method is used for an early task, but later you will replace calls 
     * to this method with calls to generateBetterFarm() instead.
     * 
     * It is recommended that for this method you create the level array and then fill
     * it using for a pair of for loops, and create new Tile objects inside the inner loop.
     * Set their type using the constructor and the TileType enumeration, then
     * assign the Tile object to the current element in the array using the loop counters.
     * For this method you should use just Tile objects with the type STONE_GROUND.
     *
     */
    private void generateFarm() {
        // CODE IS DEPRECATED AND CAUSES SYNTAX ERRORS
        /*
        level = new Tile[LEVEL_WIDTH][LEVEL_HEIGHT];
        
        for (int i = 0; i < level.length; i++){
            for (int j = 0; j < level[i].length;  j++){
                level[i][j] = new Tile(TileType.STONE_GROUND);
            }
        }
        */
    }
    
    /**
     * This method is used in a later task to improve the movement logic for the player.
     * You should remove the line of code in this method and replace it with your own code.
     * You may like to start by copying and pasting your movement code from the movePlayer()
     * method and then editing it. This method should improve the movement code to prevent
     * the player from moving off the edge of the level (i.e. stopping movement that would
     * change the X or Y positions to be less than 0 or greater than the level width/height
     * values).
     * @param direction the direction of movement based on the arrow key that was pressed:
     * 1 is up, 2 is right, 3 is down and 4 is left.
     */
    public void betterMovePlayer(int direction) {
        // CODE IS DEPRECATED AND CAUSES SYNTAX ERRORS
        /*
        int currentX = player.getX();
        int currentY = player.getY();
        
        int nextX = 0;
        int nextY = 0;
        
        switch(direction){
            case 1 -> {
                // up
                nextX = currentX;
                nextY = currentY-1;
            }
            case 2 -> {
                // right
                nextX = currentX+1;
                nextY = currentY;
            }
            case 3 -> {
                // down
                nextX = currentX;
                nextY = currentY+1;
            }
            case 4 -> {
                // left
                nextX = currentX-1;
                nextY = currentY;
            }
        }
        
        // NOTE THESE FUNCTIONS ORIGINALLY DID NOT TAKE IN A VECTOR AS AN ARGUMENT
        if(isValid(nextX, nextY))){
            player.setPosition(nextX, nextY);
        }
        
        if(isWithinLevel(nextX, nextY)){
            handlePlayerInteraction(nextX, nextY);
        }
        */
    }
    
    /**
     * This method is used in a later task to generate a better level with more features.
     * Leave the generateFarm() method code as it is and write your new code in this method.
     * Remove the call to generateFarm() that is in this method and then 
     * begin writing your new code. You may wish to begin by copying and pasting the code
     * from generateFarm() into this method. 
     * 
     * This method should generate a farm with the same stone ground as before, but
     * should add a rectangular patch of dirt tiles somewhere in the level that will
     * be used with later tasks. For the highest marks in this task your dirt patch
     * should be dynamically placed i.e. its position and size is determined randomly
     * each time this method is called so it is different every time the game is started.
     */
    private void betterGenerateFarm() {
        // CODE IS DEPRECATED AND CAUSES SYNTAX ERRORS
        /*
        level = new Tile[LEVEL_WIDTH][LEVEL_HEIGHT];
        
        // should make function for this + spawning
        int plotWidth = rng.nextInt(6, 12);
        int plotHeight = rng.nextInt(5, 12);
        int plotCornerX = rng.nextInt(LEVEL_WIDTH-plotWidth);
        int plotCornerY = rng.nextInt(LEVEL_HEIGHT-plotHeight);

        
        // System.out.printf("Width, Height: %d,%d %nCorner Coords : (%d,%d) %n", plotWidth, plotHeight, plotCornerX, plotCornerY);

        // default terrain generation: stone ground
        for (int i = 0; i < level.length; i++){ //cols (x)
            for (int j = 0; j < level[i].length;  j++){ //rows (y)
                level[i][j] = new Tile(TileType.STONE_GROUND);
            }
        }
        
        // farm plot generation
        for (int i = plotCornerX; i<plotCornerX+plotWidth; i++){
            for (int j = plotCornerY; j<plotCornerY+plotHeight; j++){
                level[i][j] = new Tile(TileType.DIRT);
            }
        }
        
        // place hoe and seed box
        level[plotCornerX][plotCornerY] = new Tile(TileType.HOE_BOX, true);
        level[plotCornerX+1][plotCornerY] = new Tile(TileType.SEED_BOX, true);
        */
    }
    
    /**
     * This method is used in an even later task to generate an even better level with even more features!
     * Leave the generateBetterFarm() method code as it is and write your new code in this method.
     * Remove the call to generateBetterFarm() that is in this method and then 
     * begin writing your new code.
     * You may wish to begin by copying and pasting the code from generateBetterFarm() into
     * this method. 
     * 
     * This method should generate the same farm as before with stone ground and a dirt patch,
     * but should also add a "farmhouse" using the farmhouse floor tiles and possibly wall tiles.
     * One bed should be placed somewhere in the house to be used in a later task. For top marks
     * in this task the farmhouse should be dynamically generated i.e. its size and/or position
     * is different each game, and ideally it should avoid overwriting the dirt patch previously created.
     */
    private void evenBetterGenerateFarm() {
        level = new Level(LEVEL_WIDTH, LEVEL_HEIGHT);
        
        // create default stone ground map
        // Vector() defaults to (0,0) 
        level.fillRect(TileType.STONE_GROUND, new Vector(), LEVEL_SIZE);
        // spawn a dirt patch ie farm plot
        generateDirtPatch();
        // spawn the house
        generateHouse();
        // generates the debris layer 
        addDebris();
    }
    
    /**
     * This method is used to make the crops grow overnight if any tiles exist
     * that have been tilled with the hoe and then seeded with the seed bag.
     * You should check every tile in the level 2D array and any that are of the
     * SOWED_DIRT type should be changed to be of the CROP type. You can do this
     * by creating a new Tile object with the correct type and adding it to the
     * level array in the position of the current SOWED_DIRT tile being checked.
     * A later task will require you to modify this method so that a Pest is created
     * whenever one or more crops are created.
     */
    public void growCrops() {
        soilDecay();
        Vector[] sowedCoords = level.findTiles(TileType.SOWED_DIRT);
        
        if (sowedCoords.length>0){
            
            if(pest==null){
                createPest();
            }

            for (Vector v : sowedCoords){
                
                if(rng.nextDouble() > 0.05){
                    level.fillTile(TileType.CROP, v);
                }
                else{
                    level.fillTile(TileType.TILLED_DIRT, v);
                }
            }
        }
    }
    
    /**
     * This method implements the possible soil decay if no seeds are sown
     * on tilled soil. The % is hard coded but this may change ?
     */
    private void soilDecay(){
        Vector [] tilledCoords = level.findTiles(TileType.TILLED_DIRT);
        
        if(tilledCoords.length > 0){
            for(Vector v : tilledCoords){
                if(rng.nextDouble() <= 0.33){
                    level.fillTile(TileType.DIRT, v);
                }
            }
        }
    }
    
    /**
     * This method is used in an even later task to improve the movement logic for the player.
     * You should remove the line of code in this method and replace it with your own code.
     * You may like to start by copying and pasting your movement code from the betterMovePlayer()
     * method and then editing it. This method should add logic that handles the player moving into
     * a BED tile by calling the triggerNight() method. You should also rewrite the method to be
     * as efficient and easy to understand as possible, e.g by removing/refining duplicated code that is
     * common to all movement directions (or writing a new method or methods), creating new methods
     * that help streamline this code and any other improvements to readability and conciseness.
     * @param direction the direction of movement based on the arrow key that was pressed:
     * 1 is up, 2 is right, 3 is down and 4 is left.
     */
    public void evenBetterMovePlayer(int direction) {
        Vector currentCoords = new Vector(player.getX(), player.getY());
        
        Vector nextCoords = null;
        
        switch(direction){
            case 1 -> {
                // up
                nextCoords = currentCoords.up();
            }
            case 2 -> {
                // right
                nextCoords = currentCoords.right();
            }
            case 3 -> {
                // down
                nextCoords = currentCoords.down();
            }
            case 4 -> {
                // left
                nextCoords = currentCoords.left();
            }
        } 
        
        if(isValid(nextCoords)){
            player.setPosition(nextCoords);
        }
        
        if(level.isWithinLevel(nextCoords)){
            //System.out.println("Moving to: " + level[nextCoords.getX()][nextCoords.getY()].getType());
            handlePlayerInteraction(nextCoords);
        }
    }
    
    /**
     * Adds a pest to the current level. This method should create a new Pest object
     * and assign it to the pest attribute of this class. For top marks you should
     * make the position of the Pest dynamic i.e. it is randomly selected in some way.
     */
    private void createPest() {
        
        // generate random pestX and pestY spawn coords
        Vector pestCoords = new Vector(-1, -1);
        
        /**
         * only want pest to spawn on EDGES of map, edges are 
         * (0, y) ; (x, 0)
         * (LEVEL_WIDTH-1, y) ; (x, LEVEL_WIDTH-1)
         */
        if (pest == null){
            while (!isValid(pestCoords)){
                switch(rng.nextInt(1,5)){
                    case 1->{
                        // (0, y) left
                        pestCoords = new Vector(0, rng.nextInt(LEVEL_HEIGHT-1));
                        }
                    case 2->{
                        // (x, 0) top
                        pestCoords = new Vector(rng.nextInt(LEVEL_WIDTH-1), 0);
                        }
                    case 3->{
                        // (LEVEL_WIDTH-1, y) right
                        pestCoords = new Vector(LEVEL_WIDTH-1, rng.nextInt(LEVEL_HEIGHT-1));
                        }
                    case 4->{
                        // (x, LEVEL_HEIGHT-1) bottom
                        pestCoords = new Vector(rng.nextInt(LEVEL_WIDTH-1), LEVEL_HEIGHT-1);
                        }
                }
            }
        }
        
        pest = new Pest(pestCoords);
    }
    
    /**
     * Moves the pest object within the level. This method should change the pest objects
     * X and Y positions so that it moves towards the nearest crop in the level. You will
     * need to write code to find the nearest crop to the pest. You should then change the
     * pests position so that it moves closer to the nearest crop. The pest should not
     * be blocked by any tile types nor by Trees or Rocks (though you can add code to
     * create more complex movement rules if you wish!)
     */
    private void movePest() {
        Vector start = new Vector(pest.getX(), pest.getY());
        int sX = start.getX();
        int sY = start.getY();
        
        Vector closest = level.findClosest(TileType.CROP, start);
        
        if(closest != null){
            if (start != closest) {
                    Vector delta = closest.sub(start);
                    int dX = delta.getX();
                    int dY = delta.getY();
                    
                    Vector end;

                    if(delta.abs().getX() > delta.abs().getY()){
                        end = start.add(Integer.signum(dX), 0);
                    } 
                    else{
                        end = start.add(0, Integer.signum(dY));
                    }

                    pest.setPosition(end);
            }
            else{
            }
        }
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
    private void addDebris() {
        debris = new Entity[100]; 
        
        for(int i = 0; i<debris.length; i++){
            int x = rng.nextInt(LEVEL_WIDTH);
            int y = rng.nextInt(LEVEL_HEIGHT);
            
            Vector v = new Vector(x, y);
            
            TileType type = level.getTile(v).getType();
            
            if(isValid(v) &&  type == TileType.DIRT){
                if(rng.nextBoolean()){
                    debris[i] = new Tree(v);
                }
                else{
                    debris[i] = new Rock(v);
                }
            }
        }
    }

    /**
     * A method to trigger the night animation for the game when the player enters
     * a BED tile. You can call this method to start the night animation and the growth
     * of crops.
     */
    private void triggerNight() {
        growCrops();
        gui.doNight(this);
    }
    
    /**
     * A method that is used to prevent player movement during the night animation.
     * @return true if the night animation is still playing, false otherwise.
     */
    public boolean sleeping() {
        return gui.animatingNight();
    }

    /**
     * Performs a single turn of the game when the user presses a key on the
     * keyboard. The method increments the turnNumber, moves the pest object
     * every four turns, and requests the GUI to redraw the game level by passing
     * it the level, debris, player and pest objects/arrays.
     *
     */
    public void doTurn() {
        turnNumber++;
        if (turnNumber % 4 == 0 && pest != null) {
            movePest();
        }
        gui.updateDisplay(level.toArray(), debris, player, pest);
    }

    /**
     * Starts a game. This method generates a room, adds the dropper and player,
     * then requests the GUI to update the level on screen using the information
     * passed to it.
     */
    public void startGame() {
        evenBetterGenerateFarm();
        createPlayer(); 
        gui.updateDisplay(level.toArray(), debris, player, pest);
    }
    
    /**
     * Check if coordinate location on level is valid for entity to stand on by 
     * checking if location exists on map, and if yes, is the tile at that location 
     * collidable. 
     * 
     * @param v vector containing coordinates to check
     * @return true if coordinates are valid for movement
     */
    private boolean isValid(Vector v){
        if(v == null){
            return false;
        }
        
        int x  = v.getX();
        int y = v.getY();
        
        return (level.isWithinLevel(v) && !level.getTile(v).isCollidable() && !hasDebris(v));
    }
    
    /**
     * Traversed debris array to check whether an entity has the same x,y
     * as the passed vector.
     * @param v vector object to check against each entity in the debris array
     * @return true if there is at least 1 entity with the same (x,y) coordinates
     * as the vector
     */
    private boolean hasDebris(Vector v){
        for (Entity entity : debris){
            if(entity!=null){
                int entityX = entity.getX();
                int entityY = entity.getY();
                int vX = v.getX();
                int vY = v.getY();
                
                if(entityX == vX && entityY == vY){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Logic for deciding which item to set the player's holding attribute to,
     * depending on what the TileType of the passed tile is, ie AXE_BOX, HOE_BOX
     * etc.
     * @param tile 
     */
    private void updatePlayerItem(Tile tile){
        
        TileType t = tile.getType();
        Item holding = player.getHeldItem();
        
        if(holding != null){
            if(holding.getDurability() <= 0){
                player.checkHeldDurability();
            }
        }
        
        if(t != null)
            switch (t){
            case HOE_BOX:
                player.setHeldItem(new Hoe());
                break;
            case SEED_BOX:
                player.setHeldItem(new SeedBag());
                break;
            case AXE_BOX:
                player.setHeldItem(new Axe());
                break;
            case PICKAXE_BOX:
                player.setHeldItem(new Pickaxe());
                break;
            default:
                break;
        }
    }
    
    /**
     * Handles the interaction between the level array and the player, for example
     * tilling the ground if the player is holding a hoe.
     * @param v vector object of tile coordinate player is attemping to interact with
     */
    private void handlePlayerInteraction(Vector v){
        
        Tile tile = level.getTile(v);
        TileType type = tile.getType();
        
        updatePlayerItem(tile);
        Item holding = player.getHeldItem();
        
        // checks if tile has debris on it -> preventing interaction
        if (hasDebris(v)){
            if(holding != null){
                int debrisIndex = getEntity(v);
                Entity e = debris[debrisIndex];
                
                holding.use(e);
                // System.out.printf("Entity hp is at: %d%n", e.getHealth());
                if(e.getHealth() <= 0){
                    debris[debrisIndex] = null;
                }
            }
            return;
        }

        // no prerequisite interactions    
        
        switch(type){
            case BED->{
                triggerNight();
            }
            case CROP->{
                level.fillTile(TileType.DIRT, v);
                System.out.printf("MONEY: $%d + $5%n",money);
                money += 5;
            }
        }
        
        // interactions with pest
        if(pest!=null && pest.getPosition().equals(v)){
            pest=null;
        }
        
        // item interactions with world
        if(holding!=null){
            holding.use(tile);
        }
    }
    

    /**
     * Generates the floor and walls of the house for generateEvenBetterFarm()
     */
    private void generateHouse(){
        // house floor generation
        // size (width, height) -> (x, y)
        Vector size = new Vector(rng.nextInt(5, 7), rng.nextInt(4, 6));
        Vector topLeft = new Vector(rng.nextInt(LEVEL_WIDTH-1-size.getX()),
                                        rng.nextInt(LEVEL_HEIGHT/2, LEVEL_HEIGHT-size.getY()));
        
        // calculate all corners
        Vector bottomRight = topLeft.add(size);
        Vector topRight = topLeft.add(size.getX(), 0);
        Vector bottomLeft = topLeft.add(0, size.getY());
        
        // door, bed and box vector coords
        Vector doorV = topLeft.add(size.getX() / 2, 0);
        Vector bedV = bottomRight.sub(1, 1);
        Vector boxV = doorV.add(1,1);
        
        // floor
        level.fillRect(TileType.HOUSE_FLOOR, topLeft, topLeft.add(size));
        
        // walls
        level.fillLine(TileType.WALL, topLeft, topRight);
        level.fillLine(TileType.WALL, topLeft, bottomLeft);
        level.fillLine(TileType.WALL, topRight, bottomRight);
        level.fillLine(TileType.WALL, bottomLeft, bottomRight);
        
        // 'door' - empty tile
        level.fillTile(TileType.HOUSE_FLOOR, doorV);
        
        // place bed
        level.fillTile(TileType.BED, bedV);
        
        // place boxes
        level.fillTile(TileType.AXE_BOX, boxV);
        level.fillTile(TileType.PICKAXE_BOX, boxV.right());
    }
    

    /**
     * Generates the dirt patch for generateEvenBetterFarm() and places the 
     * hoe and seed box alongside
     */
    private void generateDirtPatch(){
        // farm plot generation 
        // size (width, height) -> (x, y)
        Vector size = new Vector(rng.nextInt(5, 26), rng.nextInt(3,LEVEL_HEIGHT/2));
        Vector topLeft = new Vector(rng.nextInt(LEVEL_WIDTH-size.getX()), 
                                       rng.nextInt(LEVEL_HEIGHT/2-size.getY()));

        level.fillRect(TileType.DIRT, topLeft, topLeft.add(size));
        
        // place item boxes
        //level[topLeft.getX()+size.getX()/2][topLeft.getY()] = new Tile(TileType.HOE_BOX, true);
        //level[topLeft.getX()+size.getX()/2-2][topLeft.getY()] = new Tile(TileType.SEED_BOX, true);
        
        Vector boxV = new Vector(topLeft.getX()+size.getX()/2, topLeft.getY());
        level.fillTile(TileType.HOE_BOX, boxV);
        level.fillTile(TileType.SEED_BOX, boxV.left());
    }
    
    /**
     * Gets the Entity object at the coordinate v, stored in the Entity[] debris
     * @param v Vector object containing coordinates to find index
     * @param e Entity[] containing objects with class or child of Entity 
     * @return 
     */
    private int getEntity(Vector v){
        for(int i = 0; i<debris.length; i++){
            Entity e = debris[i];
            
            if(e == null){continue;}
            if(e.getPosition().equals(v)){
                return i;
            }
        }
        return 0;
    }
    
    // ALL FUNCTIONS BELOW HERE ARE BEING SPLIT INTO OTHER CLASSSES 
    // AND ARE TO BE REMOVED LATER
}
