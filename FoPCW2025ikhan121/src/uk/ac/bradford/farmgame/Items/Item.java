package uk.ac.bradford.farmgame.Items;

import uk.ac.bradford.farmgame.Entities.Entity;
import uk.ac.bradford.farmgame.Tile;

/**
 * i should have done this 10 years ago
 * @author goose
 */
public abstract class Item {
    private String name;
    
    /**
     * the current durability and durability modifier of the item, 
     * the max durability is calculated by 100*durabilityModifier
     */
    private int durability;
    private int durabilityModifier;
    
    protected int damage;
    
    public Item(String name, int durabilityModifier){
        this.name = name;
        this.durabilityModifier = durabilityModifier;
        this.durability = 100 * durabilityModifier;
    }
    
    
    public void reduceDurability(){
        this.durability = this.durability - 10;
        
        if(this.durability <= 0){
            breakItem();
        }
    }
    
    private void breakItem(){
        System.out.println("item is broken");
        
        // NEED TO IMPLEMENT ITEM DESTRUCTION 
        return;
    }
    
    public String getName(){
        return this.name;
    }
    
    public abstract void use(Entity e);
    public abstract void use(Tile t);
}
