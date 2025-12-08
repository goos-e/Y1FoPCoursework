package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.entity.Entity;
import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.Tile.TileType;

/**
 *
 * @author goose
 */
public class SeedBag extends Item{
    public SeedBag(){
        super("SeedBag", 2, 1);
    }
    
    @Override
    public void use(Entity e){
        double damageMod = 1.0;
        double durabilityLossMod = 1.0;
        
        e.hurtEntity(this.damage * damageMod);
        this.reduceDurability(durabilityLossMod);
    }
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.TILLED_DIRT){
            t.setType(TileType.SOWED_DIRT);
            this.reduceDurability(0.5);
        }
    }
}
