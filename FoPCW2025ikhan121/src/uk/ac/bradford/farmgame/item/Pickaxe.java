package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.entity.Entity;
import uk.ac.bradford.farmgame.entity.Rock;
import uk.ac.bradford.farmgame.Tile;

/**
 *
 * @author goose
 */
public class Pickaxe extends Item{
    
    public Pickaxe(){
        super("Pickaxe", 200, 25);
    }
    
    @Override
    public void use(Entity e){
        if(e instanceof Rock){
            e.hurtEntity(this.getDamage() * 2);
            this.reduceDurability(this.getDurabilityLoss() / 2);
        }
        else{
            e.hurtEntity(this.getDamage());
            this.reduceDurability(this.getDurabilityLoss());
        }
    }
    
    @Override
    public void use(Tile t){}
}
