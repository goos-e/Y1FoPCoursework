package uk.ac.bradford.farmgame.Items;

import uk.ac.bradford.farmgame.Entities.Entity;
import uk.ac.bradford.farmgame.Entities.Rock;
import uk.ac.bradford.farmgame.Tile;

/**
 *
 * @author goose
 */
public class Pickaxe extends Item{
    public Pickaxe(){
        super("Pickaxe", 1, 25);
    }
    
    @Override
    public void use(Entity e){
        if (e instanceof Rock){
            e.hurtEntity(this.damage);
        }
        this.reduceDurability();
    }
    
    @Override
    public void use(Tile t){}
}
