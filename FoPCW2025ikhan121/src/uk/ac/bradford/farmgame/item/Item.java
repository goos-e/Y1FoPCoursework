package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.entity.Entity;
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
    private double durability;
    private double durabilityModifier;
    
    protected double damage;
    
    public Item(String name, int durabilityModifier, int damage){
        this.name = name;
        this.durabilityModifier = durabilityModifier;
        this.durability = 100 * durabilityModifier;
        this.damage = damage;
    }
    
    protected void reduceDurability(){
        this.durability = this.durability - 10.0;
    }
    
    
    /**
     * overloaded method for durability loss modifier
     * @param m 
     */
    protected void reduceDurability(double m){
        this.durability = this.durability - 10.0*m;
    }
    
    public double getDurability(){
        return this.durability;
    }
    
    public String getName(){
        return this.name;
    }
    
    public abstract void use(Entity e);
    public abstract void use(Tile t);
}
