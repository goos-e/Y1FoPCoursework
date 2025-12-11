package uk.ac.bradford.farmgame;

import java.util.ArrayList;
import java.util.Random;

import uk.ac.bradford.farmgame.item.*;
import uk.ac.bradford.farmgame.entity.*;
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
    private static final Vec2 LEVEL_SIZE = new Vec2(LEVEL_WIDTH, LEVEL_HEIGHT);
    
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
     * The GUI associated with this GameEngine object. This link allows the
     * engine to pass level and entity information to the GUI to be drawn.
     */
    private GameGUI gui;

    private ArrayList<Level> levels;
    private Level currentLevel;
    private EntityManager currentEntities;
    private Player currentPlayer;
    
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
     */
    private void createPlayer() {
        if(currentEntities.hasEntityOfType(Player.class)){
            currentPlayer = currentEntities.getPlayer();
            return;
        }
        
        Vec2 bed = currentLevel.findClosest(TileType.BED, new Vec2());
        
        Vec2 playerSpawn = new Vec2(bed);
        currentEntities.addEntity(new Player(playerSpawn));
        
        currentPlayer = currentEntities.getPlayer();
    }
    
    private void createNPC(){
        if(currentEntities.getEntitiesOfType(NPC.class).size() >= 1){return;}
        
        Vec2 npcSpawn = currentLevel.getPointOnEdge();
        currentEntities.addEntity(new NPC(npcSpawn));
    }
    
    /**
     * Adds a pest to the current level. This method should create a new Pest object
     * and assign it to the pest attribute of this class. For top marks you should
     * make the position of the Pest dynamic i.e. it is randomly selected in some way.
     */
    private void createPest() {
        if(currentEntities.getEntitiesOfType(Pest.class).size() >= 3){return;}
        
        Vec2 pestSpawn = currentLevel.getPointOnEdge();
        currentEntities.addEntity(new Pest(pestSpawn));
    }
    
    /**
     * Moves the pest object within the level. This method should change the pest objects
     * X and Y positions so that it moves towards the nearest crop in the level. You will
     * need to write code to find the nearest crop to the pest. You should then change the
     * pests position so that it moves closer to the nearest crop. The pest should not
     * be blocked by any tile types nor by Trees or Rocks (though you can add code to
     * create more complex movement rules if you wish!)
     */
    private void movePest(Pest pest) {
        if(pest == null){return;}
        
        Vec2 closestCrop = currentLevel.findClosest(TileType.CROP, pest.getPosition());
        pest.moveTowards(closestCrop);
    }
    
    private void moveNPC(NPC npc){
        if(npc == null){return;}
        
        Vec2 start = npc.getPosition();
        Vec2 playerPos = currentEntities.getEntityOfType(Player.class).getPosition();
        
        if(start.isAdjacent(playerPos)){
            System.out.println("good evening..");
        }
        else{
            npc.moveTowards(playerPos);
        }
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
    @SuppressWarnings("unused")
    private void movePlayer(int direction) {
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    private void betterMovePlayer(int direction) {
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
    @SuppressWarnings("unused")
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
        // spawn a dirt patch ie farm plot
        // currentLevel.generateDirtPatch();
        // spawn the house
        
        /**
         * this method has been altered significantly due to task 19 for multiple
         * levels, it USED to call all the necessary level generation functions
         */
        
        currentLevel.generateHouse();
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
        Vec2[] sowedCoords = currentLevel.findTiles(TileType.SOWED_DIRT_WATERED);
        
        if (sowedCoords != null){
            
            createPest();

            for (Vec2 v : sowedCoords){
                
                if(rng.nextDouble() > 0.05){
                    currentLevel.fillTile(TileType.CROP, v);
                }
                else{
                    currentLevel.fillTile(TileType.TILLED_DIRT, v);
                }
            }
        }
    }
    
    /**
     * This method implements the possible soil decay if no seeds are sown
     * on tilled soil. The % is hard coded but this may change ?
     */
    private void soilDecay(){
        Vec2 [] tilledCoords = currentLevel.findTiles(TileType.TILLED_DIRT);
        
        if(tilledCoords != null){
            for(Vec2 v : tilledCoords){
                if(rng.nextDouble() <= 0.33){
                    currentLevel.fillTile(TileType.DIRT, v);
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
        Vec2 currentCoords = currentEntities.getPlayer().getPosition();
        
        Vec2 nextCoords = null;
        
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
        
        
        if(currentLevel.isWithinLevel(nextCoords)){
            //System.out.println("Moving to: " + level[nextCoords.getX()][nextCoords.getY()].getType());
            handlePlayer(nextCoords);
        }
        else{
            changeLevel(direction);
        }
        
        if(currentLevel.isValid(nextCoords)){
            currentPlayer.setPosition(nextCoords);
        }
    }
    
    /**
     * Generates a new Level object and adds to the global levels ArrayList
     * 
     * @param direction to be used for determining which side the player should spawn on
     * 1 is up, 2 is right, 3 is down, 4 is left
     */
    private void createNewLevel(int direction){
        
        // the point the player 'enters' the level from, player's position is set
        // to this value
        Vec2 playerSpawn = new Vec2(); 
        Vec2 globalLevelPosition = new Vec2();
        // position in world map
        
        // modify the globalLevelPosition and playerSpawn if there exists a passed direction via 
        // changeLevel(), otherwise initialise with (0,0)
        switch(direction){
            case 0-> {
                // do nothing to globalLevelPosition, direction 0 is first call and generates
                // a level at the 'centre' of the world map
                // this is pointless, but exists to clarify the intent
            }
            case 1-> {
                globalLevelPosition = currentLevel.getGlobalPosition().up();
                playerSpawn = new Vec2(LEVEL_WIDTH/2, LEVEL_HEIGHT-1);
            }
            case 2-> {
                globalLevelPosition = currentLevel.getGlobalPosition().right();
                playerSpawn = new Vec2(0, LEVEL_HEIGHT/2);
            }
            case 3-> {
                globalLevelPosition = currentLevel.getGlobalPosition().down();
                playerSpawn = new Vec2(LEVEL_WIDTH/2, 0);
            }
            case 4-> {
                globalLevelPosition = currentLevel.getGlobalPosition().left();
                playerSpawn = new Vec2(LEVEL_WIDTH-1, LEVEL_HEIGHT/2);
            }
        }
        
        currentLevel = new Level(LEVEL_SIZE, globalLevelPosition, rng);
        levels.add(currentLevel); // add to global map of levels
        currentEntities = currentLevel.getEntities();
        
        // check if player exists and transfer to new level and remove from old
        if(currentPlayer != null){
            currentEntities.addEntity(currentPlayer);
            currentPlayer = currentEntities.getPlayer();
            currentPlayer.setPosition(playerSpawn);
        }
        
        currentLevel.init();
        
        // CALL THE TILE WRITING FUNCTIONS HERE
        if(direction == 0){
            evenBetterGenerateFarm();
        }
        else{
        }
        
        currentLevel.addDebris();
    }
    
    public void changeLevel(int direction){
        Vec2 playerSpawn = new Vec2(LEVEL_WIDTH/2, LEVEL_HEIGHT/2); // default to center of map
        
        // position in world map
        Vec2 globalLevelPosition = currentLevel.getGlobalPosition();
        // 1 is up, 2 is right, 3 is down, 4 is left
        switch(direction){
            case 0-> {
                // do nothing to globalLevelPosition, direction 0 is first call and generates
                // a level at the 'centre' of the world map
            }
            case 1-> {
                //System.out.println("GOING UP");
                globalLevelPosition = globalLevelPosition.up();
                playerSpawn = new Vec2(LEVEL_WIDTH/2, LEVEL_HEIGHT-1);
            }
            case 2-> {
                //System.out.println("GOING RIGHT");
                globalLevelPosition = globalLevelPosition.right();
                playerSpawn = new Vec2(0, LEVEL_HEIGHT/2);
            }
            case 3-> {
                //System.out.println("GOING DOWN");
                globalLevelPosition = globalLevelPosition.down();
                playerSpawn = new Vec2(LEVEL_WIDTH/2, 0);
            }
            case 4-> {
                //System.out.println("GOING LEFT");
                globalLevelPosition = globalLevelPosition.left();
                playerSpawn = new Vec2(LEVEL_WIDTH-1, LEVEL_HEIGHT/2);
            }
        }
        
        System.out.printf("Current global coords: %d,%d%n", globalLevelPosition.getX(), globalLevelPosition.getY());
        // traverses world map to see if level exists
        // if level does exist, transfer player and load new level
        for(Level level : levels){
            if(level.getGlobalPosition().equals(globalLevelPosition)){
                currentEntities.removeEntity(currentPlayer);
                currentLevel = level;
                currentEntities = currentLevel.getEntities();
                currentEntities.addEntity(currentPlayer);
                currentPlayer.setPosition(playerSpawn);
                return;
            }
        }
        
        createNewLevel(direction);
    }
    
    /**
     * A method to trigger the night animation for the game when the player enters
     * a BED tile. You can call this method to start the night animation and the growth
     * of crops.
     */
    private void triggerNight() {
        growCrops();
        gui.doNight(this);
        
        if(rng.nextBoolean()){
            createNPC();
        }
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
        if (turnNumber % 4 == 0 && currentEntities.getPest() != null) {
            ArrayList<Pest> pests = currentEntities.getEntitiesOfType(Pest.class);
            for(Pest pest : pests){
                movePest(pest);
            }
        }
        if (turnNumber % 2 == 0 && currentEntities.getNPC() != null) {
            ArrayList<NPC> NPCs = currentEntities.getEntitiesOfType(NPC.class);
            for(NPC npc : NPCs){
                moveNPC(npc);
            }
        }
        gui.updateDisplay(currentLevel);
    }

    /**
     * Starts a game. This method generates a room, adds the dropper and player,
     * then requests the GUI to update the level on screen using the information
     * passed to it.
     */
    public void startGame() {
        levels = new ArrayList<Level>();
        createNewLevel(0);
        //evenBetterGenerateFarm();
        createPlayer();
        gui.updateDisplay(currentLevel);
    }
    
    
    /**
     * Handles the interaction between the level array and the player, for example
     * tilling the ground if the player is holding a hoe.
     * @param v vector object of tile coordinate player is attemping to interact with
     */
    private void handlePlayer(Vec2 v){
        
        Tile tile = currentLevel.getTile(v);
        TileType type = tile.getType();
        int entityIndex = currentEntities.getEntityIndexAt(v);
        
        currentPlayer.updatePlayerItem(type);
        Item holding = currentPlayer.getHeldItem();
        
        // checks if tile has entity on it -> forcing entity interaction
        if(entityIndex != -1){
            Entity entity = currentEntities.getEntity(entityIndex);
            
            holding.use(entity);
            
            if(entity.getHealth() <= 0){
                currentEntities.removeEntity(entityIndex);
            }
            
            return;
        }

        // tile interactions
        switch(type){
            case BED->{
                triggerNight();
            }
            case CROP->{
                int currentMoney = currentPlayer.getMoney();
                currentLevel.fillTile(TileType.DIRT, v);
                //System.out.printf("MONEY: $%d + $5%n",currentMoney);
                currentPlayer.setMoney(currentMoney + 5);
            }
            default->{
                holding.use(tile);
            }
        }
    }
    
    // ALL FUNCTIONS BELOW HERE ARE BEING SPLIT INTO OTHER CLASSSES 
    // AND ARE TO BE REMOVED LATER
}
