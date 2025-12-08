package uk.ac.bradford.farmgame.entity;

import uk.ac.bradford.farmgame.item.*;
import uk.ac.bradford.farmgame.item.Hand;
import uk.ac.bradford.farmgame.Tile.TileType;
import uk.ac.bradford.farmgame.Vec2;

/**
 * The Player class is a subclass of Entity and adds specific state and
 * behaviour for the player in the game, including what item (if any) the player
 * is holding based on their actions in the game.
 *
 * @author prtrundl
 */
public class Player extends Entity {
    
   /**
    * Item object for what the player is holding
    */
    private Item holding;
    
    /**
     * This constructor is used to create a Player object to use in the game
     *
     * @param v the starting position of this Player in the game defined by a 
     * Vector object (x,y)
     */
    public Player(Vec2 v) {
        setPosition(v);
        this.holding = new Hand();
    }

    /**
     * A getter method to check if the player is holding a tool or item. Used to check
     * what is held by the player when taking actions in the game that require the player
     * to hold a specific item e.g. an axe or seed bag.
     * @return a numerical value representing the currently held item: 0 is no item, 1 is
     * the how to till dirt, 2 is the seed bag to sow seeds, 3 is the axe to cut trees and 4 is
     * the pickaxe to break rocks.
     */
    public Item getHeldItem() {
        return holding;
    }
    
    /**
     * A setter to change the held item for the player. Used for actions that
     * require the player to be holding a certain items to perform them e.g. 
     * planting seeds that requires a seed bag.
     * @param a the numerical value representing the item the player will now hold: 0 is no item, 1 is
     * the how to till dirt, 2 is the seed bag to sow seeds, 3 is the axe to cut trees and 4 is
     * the pickaxe to break rocks.
     */
    public void setHeldItem(Item a) {
        holding = a;
    }
    
    /**
     * A setter to change the held item to null, so the item is destroyed.
     */
    private void removeHeldItem(){
        if(holding!=null){
            this.holding = new Hand();
        }
    }
    
    public void checkHeldDurability(){
        if(holding.getDurability() <= 0){
            removeHeldItem();
        }
    }    
    
    /**
     * Logic for deciding which item to set the player's holding attribute to,
     * depending on what the TileType of the passed tile is, ie AXE_BOX, HOE_BOX
     * etc.
     * @param tile 
     */
    public void updatePlayerItem(TileType t){
        
        if(this.holding != null){
            if(this.holding.getDurability() <= 0){
                checkHeldDurability();
            }
        }
        
        if(t != null){
            switch(t){
                case HOE_BOX->setHeldItem(new Hoe());
                case SEED_BOX->setHeldItem(new SeedBag());
                case AXE_BOX->setHeldItem(new Axe());
                case PICKAXE_BOX->setHeldItem(new Pickaxe());
                case WATERINGCAN_BOX->setHeldItem(new WateringCan());
            }
        }
    }
}
