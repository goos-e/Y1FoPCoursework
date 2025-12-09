/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.farmgame.item;

import uk.ac.bradford.farmgame.Tile;
import uk.ac.bradford.farmgame.Tile.TileType;
import uk.ac.bradford.farmgame.entity.Entity;

/**
 *
 * @author goose
 */
public class WateringCan extends Item{
    public WateringCan(){
        super("Watering Can", 1, 1);
    }
    
    @Override
    public void use(Entity e) {
        double damageMod = 1.0;
        double durabilityLossMod = 1.0;
        
        e.hurtEntity(this.damage * damageMod);
        this.reduceDurability(durabilityLossMod);
    }

    @Override
    public void use(Tile t) {
        if(t.getType() == TileType.SOWED_DIRT){
            t.setType(TileType.SOWED_DIRT_WATERED);
            this.reduceDurability(0.2);
        }
    }
    
}
