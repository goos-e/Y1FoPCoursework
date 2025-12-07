package uk.ac.bradford.farmgame.Items;

import uk.ac.bradford.farmgame.Entities.Entity;
import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.Entities.Tree;

/**
 *
 * @author goose
 */
public class Axe extends Item{
    
    
    public Axe(){
        super("Axe", 1);
    }
    
    @Override
    public void use(Entity e){
        if (e instanceof Tree){
            e.hurtEntity(this.damage);
            this.reduceDurability();
        }
    }
    
    @Override
    public void use(Tile t){}
}
