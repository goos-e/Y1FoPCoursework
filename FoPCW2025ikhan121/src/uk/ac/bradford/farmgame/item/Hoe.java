package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.entity.Entity;
import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.Tile.TileType;

/**
 *
 * @author goose
 */
public class Hoe extends Item{
    public Hoe(){
        super("Hoe", 200, 10);
    }
    
    @Override
    public void use(Entity e){
        e.hurtEntity(this.getDamage());
        this.reduceDurability(this.getDurabilityLoss());
    }
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.DIRT){
            t.setType(TileType.TILLED_DIRT);
            this.reduceDurability(4);
        }
    }
}
