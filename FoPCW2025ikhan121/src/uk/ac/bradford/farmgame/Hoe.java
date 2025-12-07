package uk.ac.bradford.farmgame;
import uk.ac.bradford.farmgame.Tile.TileType;

/**
 *
 * @author goose
 */
public class Hoe extends Item{
    public Hoe(){
        super("Pickaxe", 1);
    }
    
    @Override
    public void use(Entity e){}
    
    @Override
    public void use(Tile t){
        if (t.getType() == TileType.DIRT){
            t.setType(TileType.TILLED_DIRT);
            this.reduceDurability();
        }
    }
}
