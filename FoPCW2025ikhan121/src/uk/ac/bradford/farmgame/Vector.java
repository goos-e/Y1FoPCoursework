package uk.ac.bradford.farmgame;

/**
 *
 * @author goose
 */
public final class Vector {
    private final int x;
    private final int y;
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vector add(Vector v){
        return new Vector(this.x+v.x, this.y+v.y);
    }
    
    public Vector add(int x, int y){
        return new Vector(this.x+x, this.y+y);
    }
    
    public Vector sub(Vector v){
        return new Vector(this.x-v.x, this.y-v.y);
    }
    
    public Vector sub(int x, int y){
        return new Vector(this.x-x, this.y-y);
    }
    
    public int mag(){
        return Math.abs(this.x)+Math.abs(this.y);
    }
    
    public Vector abs(){
        return new Vector(Math.abs(this.x), Math.abs(this.y));
    }
    
    public Vector up(){
        return new Vector(this.x, this.y-1);
    }
    
    public Vector down(){
        return new Vector(this.x, this.y+1);
    }
    
    public Vector left(){
        return new Vector(this.x-1, this.y);
    }
    
    public Vector right(){
        return new Vector(this.x+1, this.y);
    }
}
