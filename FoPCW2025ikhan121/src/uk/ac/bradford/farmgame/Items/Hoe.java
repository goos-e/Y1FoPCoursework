package uk.ac.bradford.farmgame.Items;

import uk.ac.bradford.farmgame.Entities.Entity;
import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.Tile.TileType;

/**
 *
 * @author goose
 */
public class Hoe extends Item{
    public Hoe(){
        super("Hoe", 1, 15);
    }
    
    @Override
    public void use(Entity e){}
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.DIRT){
            t.setType(TileType.TILLED_DIRT);
        }
        this.reduceDurability();
    }
}
