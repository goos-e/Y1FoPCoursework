package uk.ac.bradford.farmgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author goose
 */
public final class Vec2 {
    private final int x;
    private final int y;
    
    public Vec2(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vec2(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Vec2(Vec2 v){
        this.x = v.getX();
        this.y = v.getY();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    

    
    public Vec2 add(Vec2 v){
        return new Vec2(this.x+v.x, this.y+v.y);
    }
    
    public Vec2 add(int x, int y){
        return new Vec2(this.x+x, this.y+y);
    }
    
    public Vec2 sub(Vec2 v){
        return new Vec2(this.x-v.x, this.y-v.y);
    }
    
    public Vec2 sub(int x, int y){
        return new Vec2(this.x-x, this.y-y);
    }
    
    public int mag(){
        return Math.abs(this.x)+Math.abs(this.y);
    }
    
    public Vec2 abs(){
        return new Vec2(Math.abs(this.x), Math.abs(this.y));
    }
    
    public Vec2 up(){
        return new Vec2(this.x, this.y-1);
    }
    
    public Vec2 down(){
        return new Vec2(this.x, this.y+1);
    }
    
    public Vec2 left(){
        return new Vec2(this.x-1, this.y);
    }
    
    public Vec2 right(){
        return new Vec2(this.x+1, this.y);
    }
    
    public Vec2 withX(int y){
        return new Vec2(this.x, y);
    }
    
    public Vec2 withY(int x){
        return new Vec2(x, this.y);
    }
    
    public Vec2[] getNeighbours4(){
        Vec2[] neighbours = new Vec2[4];
        
        neighbours[0] = this.up();
        neighbours[1] = this.down();
        neighbours[2] = this.left();
        neighbours[3] = this.right();
        
        return neighbours;
    }
    
    /**
     * Checks if current and passed vector are adjacent: makes adjacency array 
     * for current and traverses, comparing each to passed vector.
     * @param v Vec2 object to check against each adjacent to current vector
     * @return True if v is among adjacent vectors, false otherwise
     */
    public boolean isAdjacentTo(Vec2 v){
        Vec2[] neighbours = this.getNeighbours4();
        
        for(Vec2 adj : neighbours){
            if(adj.equals(v)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Vec2)){return false;}
        Vec2 v = (Vec2) o;
        return(this.x == v.getX() && this.y == v.getY());
    }
}
