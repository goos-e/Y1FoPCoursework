package uk.ac.bradford.farmgame.entity;

import java.util.ArrayList;
import uk.ac.bradford.farmgame.Vec2;

/**
 *
 * @author goose
 */
public class EntityManager {
    private ArrayList<Entity> entities;
    Player player;
    
    public EntityManager(){
        entities = new ArrayList<Entity>();
    }
    
    public void addEntity(Entity e){
        entities.add(e);
        if(e instanceof Player){
            this.player = ((Player) e);
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
     * returns null if no entity exists at that coordinate
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
     * parameter, clazz. If at least one entity is an instance of that class, the method
     * returns that entity, otherwise null.
     * 
     * @param <T>
     * @param clazz class to check if each entity is an instance of
     * @return the first entity of that type, cast as type T
     */
    public <T extends Entity> T getEntityOfType(Class<T> clazz){
        for(Entity e : entities){
            if(clazz.isInstance(e)){
                return clazz.cast(e);
            }
        }
        return null;
    }
    
    /**
     * Traverses the entities array and checks if each instance is an instance of
     * the generic class parameter, clazz. Each one that matches is added to a temporary
     * ArrayList of type clazz, which is returned as a regular array.
     * 
     * @param <T>
     * @param clazz class to check if each entity is an instance of 
     * @return 
     */
    public <T extends Entity> ArrayList<T> getEntitiesOfType(Class<T> clazz){
        ArrayList<T> typedEntities = new ArrayList<T>();
        
        for(Entity e : entities){
            if(clazz.isInstance(e)){
                typedEntities.add(clazz.cast(e));
            }
        }
        
        return typedEntities;
    }
    
    public int getSize(){
        return this.entities.size();
    }
    
    public Entity[] asArray(){
        return this.entities.toArray(Entity[]::new);
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
