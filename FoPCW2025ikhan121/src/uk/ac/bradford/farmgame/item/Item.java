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
    private double durabilityLossOnUse;
    private double maxDurability;
    
    private double damage;
    
    public Item(String name, double durability, double damage){
        this.name = name;
        this.durability = durability;
        this.maxDurability = durability;
        this.durabilityLossOnUse = 10;
        this.damage = damage;
    }
    
    
    protected void reduceDurability(double d){
        this.durability = this.durability - d;
    }
    
    public double getDurability(){
        return this.durability;
    }
    
    public void setDurability(double d){
        if(d >= this.maxDurability){
            this.durability = maxDurability;
        }
        else{
            this.durability = d;
        }
    }
    
    public double getDurabilityLoss(){
        return this.durabilityLossOnUse;
    }
    
    public void setDurabilityLoss(double d){
        this.durabilityLossOnUse = d;
    }
    
    public double getDamage(){
        return this.damage;
    }
    
    public void setDamage(double d){
        this.damage = d;
    }
    
    public double getMaxDurability(){
        return this.maxDurability;
    }
    
    public void setMaxDurability(double d){
        this.maxDurability = d;
    }
    
    public String getName(){
        return this.name;
    }
    
    public abstract void use(Entity e);
    public abstract void use(Tile t);
}
