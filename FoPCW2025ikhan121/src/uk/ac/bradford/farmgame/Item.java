package uk.ac.bradford.farmgame;

/**
 * i should have done this 10 years ago
 * @author goose
 */
public class Item {
    public String name;
    
    private int durability;
    private int durabilityModifier;
    
    private int damage;
    
    private final ItemType type;
    
    public enum ItemType{
        HOE, AXE, PICKAXE, SEEDBAG,
    }
    
    public Item(ItemType t, int d){
        this.name = "itemname";
        this.durability = 100 * durabilityModifier;
        this.durabilityModifier = d;
        this.type = t;
    }
    
    public ItemType getType(){
        return this.type;
    }
    
    public void reduceDurability(){
        this.durability = this.durability - 10;
        
        if(this.durability <= 0){
            breakItem();
        }
    }
    
    private void breakItem(){
        System.out.println("item is broken");
        return;
    }
}
