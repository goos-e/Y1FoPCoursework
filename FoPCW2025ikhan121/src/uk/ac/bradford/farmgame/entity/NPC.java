package uk.ac.bradford.farmgame.entity;

import uk.ac.bradford.farmgame.Vec2;

/**
 *
 * @author goose
 */
public class NPC extends Entity{
    
    public NPC(Vec2 v){
        setPosition(v);
        setHealth(100);
    }
    
    public void interact(Player player){
        System.out.println("good evening..");
    }
}
