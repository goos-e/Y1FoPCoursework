package uk.ac.bradford.farmgame.Items;

import uk.ac.bradford.farmgame.Entities.Entity;
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
    public void use(Entity e){}
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.TILLED_DIRT){
            t.setType(TileType.SOWED_DIRT);
        }
        this.reduceDurability();
    }
}
