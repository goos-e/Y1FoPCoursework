package uk.ac.bradford.farmgame;

/**
 * i should have done this 10 years ago
 * @author goose
 */
public class Item {
    public String name;
    
    private double durability;
    private static ItemType type;
    
    public enum ItemType{
        HOE, AXE, PICKAXE, SEEDBAG,
    }
    
    public Item(ItemType t){
        this.type = t;
        this.name = "itemname";
        this.durability = 1.0;
    }
    
    public Item(ItemType t, String n){
        this.name = n;
        this.type = t;
        this.durability = 1.0;
    }
    
    public ItemType getType(){
        return this.type;
    }
    
    
    public void lowerDurability(double a){
        
    }
}
