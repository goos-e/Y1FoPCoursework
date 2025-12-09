package uk.ac.bradford.farmgame.entity;

import java.util.ArrayList;
import uk.ac.bradford.farmgame.Vec2;

/**
 *
 * @author goose
 */
public class EntityManager {
    ArrayList<Entity> entities;
    private Player player;
    
    public EntityManager(){
        entities = new ArrayList<Entity>();
    }
    
    
    public void addEntity(Entity e){
        entities.add(e);
        if(e instanceof Player p){
            this.player = p;
        }
    }    
    
    public void removeEntity(Entity e){
        entities.remove(e); 
    }
    
    public void removeEntity(int i){
        entities.remove(i); 
    }
    
    public Entity getEntity(int i){
        return entities.get(i);
    }
    
    public int getEntityIndexAt(Vec2 v){
        for(int i = 0; i<entities.size(); i++){
            if(entities.get(i).getPosition().equals(v)){
                return i;
            }
        }
        return -1;
    }
    
    public boolean hasEntityAt(Vec2 v){
        boolean isOccupied = false;
        
        for(Entity e : entities){
            if(e.getPosition().equals(v)){
                isOccupied = true;
            }
        }
        
        return isOccupied;
    }
    
    public Entity[] getDebrisArray(){
        ArrayList<Entity> debrisArray = new ArrayList<Entity>();
        
        for(Entity e : entities){
            if(e instanceof Tree || e instanceof Rock){
                debrisArray.add(e);
            }
        }
        
        return debrisArray.toArray(new Entity[0]);
    }
    
}
