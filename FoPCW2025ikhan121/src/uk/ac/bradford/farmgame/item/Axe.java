package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.entity.Entity;
import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.entity.Tree;

/**
 *
 * @author goose
 */
public class Axe extends Item{
    
    public Axe(){
        super("Axe", 200, 25);
    }
    
    @Override
    public void use(Entity e){
        if(e instanceof Tree){
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
