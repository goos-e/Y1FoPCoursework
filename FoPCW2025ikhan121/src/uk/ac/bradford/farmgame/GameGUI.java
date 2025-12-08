package uk.ac.bradford.farmgame;

import uk.ac.bradford.farmgame.item.*;
import uk.ac.bradford.farmgame.entity.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * The GameGUI class is responsible for rendering graphics to the screen to
 * display the game level, player, pest and debris. The GameGUI class passes keyboard
 * events to a registered GameInputHandler to be handled.
 *
 * @author prtrundl
 */
public class GameGUI extends JFrame {

    /**
     * The three final int attributes below set the size of some graphical
     * elements, specifically the display height and width of tiles in the game. 
     * Tile sizes must match the size of the image files used in the game.
     */
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    /**
     * The canvas is the area that graphics are drawn to. It is an internal
     * class of the GameGUI class.
     */
    Canvas canvas;

    /**
     * Constructor for the GameGUI class. It calls the initGUI method to
     * generate the required objects for display.
     */
    public GameGUI() {
        initGUI();
    }

    /**
     * Registers an object to be passed keyboard events captured by the GUI.
     *
     * @param i the GameInputHandler object that will process keyboard events to
     * make the game respond to inputs
     */
    public void registerKeyHandler(InputHandler i) {
        addKeyListener(i);
    }

    /**
     * Method to create and initialise components for displaying elements of the
     * game on the screen.
     */
    private void initGUI() {
        add(canvas = new Canvas());     //adds canvas to this frame
        setTitle("Sunmist Plateau");
        setSize(1168, 679);
        setLocationRelativeTo(null);        //sets position of frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method to update the graphical elements on the screen, usually after
     * player (and possibly the pest) has moved when a keyboard event was handled. The
     * method requires three arguments and displays corresponding information on
     * the screen.
     *
     * @param tiles A 2-dimensional array of TileTypes. This is the tiles of the
     * current game level that should be drawn to the screen.
     * @param player A Player object. This object is used to draw the player in
     * the right tile. null can be passed for this argument, in which case no player will be drawn.
     * @param pest A Pest object that is processed to draw the pest. null
     * can be passed for this argument in which case no pest will be drawn.
     */
    public void updateDisplay(Tile[][] tiles, Entity[] debris, Player player, Pest pest) {
        canvas.update(tiles, debris, player, pest);
    }

    /**
     * A method to start the animation for night passing.
     * @param e the GameEngine that called this method, allowing a method to be called
     * in the engine to prompt crop growth.
     */
    public void doNight(GameEngine e) {
        canvas.animateNight(e);
    }
    
    /**
     * A method to check if the night animation is currently playing, so that player
     * movement can be disabled during the night cycle.
     * @return true if the animation is playing, false otherwise.
     */
    public boolean animatingNight() {
        return canvas.isNight();
    }
}

/**
 * Internal class used to draw elements within a JPanel. The Canvas class loads
 * images from the asset folder inside the main project folder.
 *
 * @author prtrundl
 */
class Canvas extends JPanel {

    private BufferedImage axeBox;
    private BufferedImage bed;
    private BufferedImage crop;
    private BufferedImage dirt;
    private BufferedImage hoeBox;
    private BufferedImage houseFloor;
    private BufferedImage pest;
    private BufferedImage pickaxeBox;
    private BufferedImage player;
    private BufferedImage axe;
    private BufferedImage hoe;
    private BufferedImage pickaxe;
    private BufferedImage seedbag;
    private BufferedImage rock;
    private BufferedImage seedBox;
    private BufferedImage sowedDirt;
    private BufferedImage stoneGround;
    private BufferedImage tilledDirt;
    private BufferedImage tree;
    private BufferedImage wall;
    // i added
    private BufferedImage npc;
    private BufferedImage door;

    Tile[][] currentTiles;      //the current 2D array of tiles to display
    Entity[] currentDebris;     //the current array of debris objects (e.g. trees, rocks)
    Player currentPlayer;       //the current player object to be drawn
    Pest currentPest;           //the current pest to draw

    private boolean night = false;  //boolean to track night animation status
    private float alpha = 0.0f;     //transparency of the night effect to simulate dark/light levels
    private float dAlpha = 0.1f;    //change in transparency, for use with the animation Timer

    /**
     * Constructor that loads tile images for use in this class
     */
    public Canvas() {
        loadTileImages();
    }

    /**
     * Loads tiles images from a fixed folder location within the project
     * directory; a folder called assets
     */
    private void loadTileImages() {
        try {
            axeBox = ImageIO.read(new File("assets/axeBox.png"));
            assert axeBox.getHeight() == GameGUI.TILE_HEIGHT
                    && axeBox.getWidth() == GameGUI.TILE_WIDTH;
            bed = ImageIO.read(new File("assets/bed.png"));
            assert bed.getHeight() == GameGUI.TILE_HEIGHT
                    && bed.getWidth() == GameGUI.TILE_WIDTH;
            crop = ImageIO.read(new File("assets/crop.png"));
            assert crop.getHeight() == GameGUI.TILE_HEIGHT
                    && crop.getWidth() == GameGUI.TILE_WIDTH;
            dirt = ImageIO.read(new File("assets/dirt.png"));
            assert dirt.getHeight() == GameGUI.TILE_HEIGHT
                    && dirt.getWidth() == GameGUI.TILE_WIDTH;
            hoeBox = ImageIO.read(new File("assets/hoeBox.png"));
            assert hoeBox.getHeight() == GameGUI.TILE_HEIGHT
                    && hoeBox.getWidth() == GameGUI.TILE_WIDTH;
            houseFloor = ImageIO.read(new File("assets/houseFloor.png"));
            assert houseFloor.getHeight() == GameGUI.TILE_HEIGHT
                    && houseFloor.getWidth() == GameGUI.TILE_WIDTH;
            pest = ImageIO.read(new File("assets/pest.png"));
            assert pest.getHeight() == GameGUI.TILE_HEIGHT
                    && pest.getWidth() == GameGUI.TILE_WIDTH;
            pickaxeBox = ImageIO.read(new File("assets/pickBox.png"));
            assert pickaxeBox.getHeight() == GameGUI.TILE_HEIGHT
                    && pickaxeBox.getWidth() == GameGUI.TILE_WIDTH;
            player = ImageIO.read(new File("assets/player.png"));
            assert player.getHeight() == GameGUI.TILE_HEIGHT
                    && player.getWidth() == GameGUI.TILE_WIDTH;
            axe = ImageIO.read(new File("assets/axe.png"));
            assert axe.getHeight() == GameGUI.TILE_HEIGHT
                    && axe.getWidth() == GameGUI.TILE_WIDTH;
            hoe = ImageIO.read(new File("assets/hoe.png"));
            assert hoe.getHeight() == GameGUI.TILE_HEIGHT
                    && hoe.getWidth() == GameGUI.TILE_WIDTH;
            pickaxe = ImageIO.read(new File("assets/pickaxe.png"));
            assert pickaxe.getHeight() == GameGUI.TILE_HEIGHT
                    && pickaxe.getWidth() == GameGUI.TILE_WIDTH;
            seedbag = ImageIO.read(new File("assets/seedbag.png"));
            assert seedbag.getHeight() == GameGUI.TILE_HEIGHT
                    && seedbag.getWidth() == GameGUI.TILE_WIDTH;
            rock = ImageIO.read(new File("assets/rock.png"));
            assert rock.getHeight() == GameGUI.TILE_HEIGHT
                    && rock.getWidth() == GameGUI.TILE_WIDTH;
            seedBox = ImageIO.read(new File("assets/seedBox.png"));
            assert seedBox.getHeight() == GameGUI.TILE_HEIGHT
                    && seedBox.getWidth() == GameGUI.TILE_WIDTH;
            sowedDirt = ImageIO.read(new File("assets/sowedDirt.png"));
            assert sowedDirt.getHeight() == GameGUI.TILE_HEIGHT
                    && sowedDirt.getWidth() == GameGUI.TILE_WIDTH;
            stoneGround = ImageIO.read(new File("assets/stoneGround.png"));
            assert stoneGround.getHeight() == GameGUI.TILE_HEIGHT
                    && stoneGround.getWidth() == GameGUI.TILE_WIDTH;
            tilledDirt = ImageIO.read(new File("assets/tilledDirt.png"));
            assert tilledDirt.getHeight() == GameGUI.TILE_HEIGHT
                    && tilledDirt.getWidth() == GameGUI.TILE_WIDTH;
            tree = ImageIO.read(new File("assets/tree.png"));
            assert tree.getHeight() == GameGUI.TILE_HEIGHT
                    && tree.getWidth() == GameGUI.TILE_WIDTH;
            wall = ImageIO.read(new File("assets/wall.png"));
            assert wall.getHeight() == GameGUI.TILE_HEIGHT
                    && wall.getWidth() == GameGUI.TILE_WIDTH;
            npc = ImageIO.read(new File("assets/npc.png"));
            assert npc.getHeight() == GameGUI.TILE_HEIGHT
                    && npc.getWidth() == GameGUI.TILE_WIDTH;
            door = ImageIO.read(new File("assets/door.png"));
            assert door.getHeight() == GameGUI.TILE_HEIGHT
                    && npc.getWidth() == GameGUI.TILE_WIDTH;

        } catch (IOException e) {
            System.out.println("Exception loading images: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * Updates the current graphics on the screen to display the tiles, player
     * and pest, and Trees/Rocks
     *
     * @param t The 2D array of TileTypes representing the current level of the
     * game
     * @param player The current player object, used to draw the player
     * @param pest The pest to display on the level
     */
    public void update(Tile[][] t, Entity[] debris, Player player, Pest pest) {
        currentTiles = t;
        currentPlayer = player;
        currentPest = pest;
        currentDebris = debris;
        repaint();
    }

    /**
     * Override of method in super class, it draws the custom elements for this
     * game such as the tiles, player and pest etc.
     *
     * @param g Graphics drawing object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLevel(g);
    }

    /**
     * Draws graphical elements to the screen to display the current game level
     * tiles, the player and the pest, plus Trees/Rocks. If the currentTiles,
     * currentPlayer or currentPest objects are null they will not be drawn.
     *
     * @param g
     */
    private void drawLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (currentTiles != null) {
            for (int i = 0; i < currentTiles.length; i++) {
                for (int j = 0; j < currentTiles[i].length; j++) {
                    if (currentTiles[i][j] != null) {
                        switch (currentTiles[i][j].getType()) {
                            case AXE_BOX ->
                                g2.drawImage(axeBox, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case BED ->
                                g2.drawImage(bed, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case CROP ->
                                g2.drawImage(crop, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case DIRT ->
                                g2.drawImage(dirt, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case HOE_BOX ->
                                g2.drawImage(hoeBox, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case HOUSE_FLOOR ->
                                g2.drawImage(houseFloor, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case PICKAXE_BOX ->
                                g2.drawImage(pickaxeBox, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case SEED_BOX ->
                                g2.drawImage(seedBox, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case SOWED_DIRT ->
                                g2.drawImage(sowedDirt, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case STONE_GROUND ->
                                g2.drawImage(stoneGround, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case TILLED_DIRT ->
                                g2.drawImage(tilledDirt, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case WALL ->
                                g2.drawImage(wall, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            case DOOR ->
                                g2.drawImage(door, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                        }
                    }
                }
            }
        }
        if (currentPest != null) {
            g2.drawImage(pest, currentPest.getX() * GameGUI.TILE_WIDTH, currentPest.getY() * GameGUI.TILE_HEIGHT, null);
        }
        if (currentPlayer != null) {
            if(currentPlayer.getHeldItem() != null){
                if(currentPlayer.getHeldItem() instanceof Hoe){
                    g2.drawImage(hoe, (currentTiles.length-1) * GameGUI.TILE_WIDTH, 0, null);
                }
                if(currentPlayer.getHeldItem() instanceof SeedBag){
                    g2.drawImage(seedbag, (currentTiles.length-1) * GameGUI.TILE_WIDTH, 0, null);
                }
                if(currentPlayer.getHeldItem() instanceof Axe){
                    g2.drawImage(axe, (currentTiles.length-1) * GameGUI.TILE_WIDTH, 0, null);
                }
                if(currentPlayer.getHeldItem() instanceof Pickaxe){
                    g2.drawImage(pickaxe, (currentTiles.length-1) * GameGUI.TILE_WIDTH, 0, null);
                }
            }
            g2.drawImage(player, currentPlayer.getX() * GameGUI.TILE_WIDTH, currentPlayer.getY() * GameGUI.TILE_HEIGHT, null);
        }
        if (currentDebris != null) {
            for (Entity e : currentDebris) {
                if (e != null) {
                    g2.drawImage(e instanceof Tree ? tree : rock, e.getX() * GameGUI.TILE_WIDTH, e.getY() * GameGUI.TILE_HEIGHT, null);
                }
            }
        }
        if (night) {
            int width = this.getSize().width;
            int height = this.getSize().height;
            g2.setColor(new Color(0, 0, 0, alpha));
            g2.fillRect(0, 0, width, height);
        }
        g2.dispose();
    }
    
    /**
     * A method to track whether the night animation is playing or not.
     * @return true if the animation is playing, false otherwise.
     */
    public boolean isNight() {
        return night;
    }

    /**
     * A method to start the night animation which darkens the screen to black
     * and then fades back to the standard level display. It uses a Timer to dispatch
     * events that draws an increasingly (then decreasingly) opaque black rectangle
     * across the level.
     * @param eng the GameEngine that triggered this animation, allowing a method for
     * crop growth to be called in the middle of the animation.
     */
    public void animateNight(GameEngine eng) {
        night = true;
        dAlpha = 0.025f;
        int drawDelay = 50;
        Timer t = new Timer(drawDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                alpha += dAlpha;
                if (alpha >= 1) {
                    eng.growCrops();
                    alpha = 1;
                    dAlpha = -0.025f;
                }
                if (alpha < 0) {
                    alpha = 0;
                    night = false;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        t.setInitialDelay(0);
        t.start();
    }
}
