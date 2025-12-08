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
        super("Axe", 1, 25);
    }
    
    
    @Override
    public void use(Entity e){
        double damageMod = 1.0;
        double durabilityLossMod = 1.0;
        
        if (e instanceof Tree){
            damageMod = 2.0;
            durabilityLossMod = 0.5;
        }
        
        e.hurtEntity(this.damage * damageMod);
        this.reduceDurability(durabilityLossMod);
    }
    
    public void use(Tile t){}
}
