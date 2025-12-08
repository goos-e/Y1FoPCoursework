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
        super("Pickaxe", 1, 25);
    }
    
    @Override
    public void use(Entity e){
        double damageMod = 1.0;
        double durabilityLossMod = 1.0;
        
        if (e instanceof Rock){
            damageMod = 2.0;
            durabilityLossMod = 0.5;
        }
        
        e.hurtEntity(this.damage * damageMod);
        this.reduceDurability(durabilityLossMod);
    }
    
    @Override
    public void use(Tile t){}
}
