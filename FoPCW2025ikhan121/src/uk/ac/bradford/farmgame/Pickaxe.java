package uk.ac.bradford.farmgame;

/**
 *
 * @author goose
 */
public class Pickaxe extends Item{
    public Pickaxe(){
        super("Pickaxe", 1);
    }
    
    @Override
    public void use(Entity e){
        if (e instanceof Rock){
            e.hurtEntity(this.damage);
            this.reduceDurability();
        }
    }
    
    @Override
    public void use(Tile t){}
}
