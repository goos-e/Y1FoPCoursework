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
        super("SeedBag", 200, 1);
    }
    
    @Override
    public void use(Entity e){
        e.hurtEntity(this.getDamage());
        this.reduceDurability(this.getDurabilityLoss());
    }
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.TILLED_DIRT){
            t.setType(TileType.SOWED_DIRT);
            this.reduceDurability(this.getDurabilityLoss() / 2);
        }
    }
}
