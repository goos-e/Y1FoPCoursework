package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.entity.Entity;

/**
 *
 * @author goose
 */
public class Hand extends Item{
    public Hand(){
        super("Hand", 1, 1);
    }
    
    @Override
    public void use(Entity e){
        e.hurtEntity(this.getDamage());
    }
    
    @Override
    public void use(Tile t){}
}
