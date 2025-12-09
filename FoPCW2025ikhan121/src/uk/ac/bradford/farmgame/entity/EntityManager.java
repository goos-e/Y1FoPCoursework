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
    private Pest pest;
    private NPC npc;
    
    public EntityManager(){
        entities = new ArrayList<Entity>();
    }
    
    
    public void addEntity(Entity e){
        entities.add(e);
        if(e instanceof Player player){
            this.player = player;
        }
        else if(e instanceof NPC npc){
            this.npc = npc;
        }
        else if(e instanceof Pest pest){
            this.pest = pest;
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
    
    
    /**
     * Traverses the entities array and compares each entities position against the
     * passed coordinate, and returns the first entity which has the same position
     * 
     * @param v Vec2 coordinate to check against each entity in the entities array
     * @return Entity object which has the same position as the passed coordinate
     */
    public Entity getEntityAt(Vec2 v){
        for(Entity e : entities){
            if(e.getPosition().equals(v)){
                return e;
            }
        }
        return null;
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
    
    /**
     * Traverses the entities array and checks against a generic class as an input
     * parameter, T. If at least one entity is an instance of that class, the method
     * returns true, otherwise false.
     * 
     * @param c class to check if an entity is an instance of
     * @return true if there is at least one instance of that class, false otherwise
     */
    public boolean hasEntityOfType(Class<?> c){
        for(Entity e : entities){
            if(c.isInstance(e)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Traverses the entities array and checks against a generic class as an input
     * parameter, T. If at least one entity is an instance of that class, the method
     * returns true, otherwise false.
     * 
     * @param c class to check if an entity is an instance of
     * @return true if there is at least one instance of that class, false otherwise
     */
    public <T extends Entity> T getEntityOfType(Class<T> c){
        for(Entity e : entities){
            if(c.isInstance(e)){
                return c.cast(e);
            }
        }
        return null;
    }
    
    public Player getPlayer(){
        return getEntityOfType(Player.class);
    }
    public NPC getNPC(){
        return getEntityOfType(NPC.class);
    }
    public Pest getPest(){
        return getEntityOfType(Pest.class);
    }
}
