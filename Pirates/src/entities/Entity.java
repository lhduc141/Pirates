package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    protected float x,y;
    protected int width, height;
    private Rectangle hitbox; 
    
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initHitBox();
    }

    //draw hit box 
    protected void drawHitBox(Graphics g ){
        //for debugging the hit box
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    //creat hit box
    private void initHitBox() {
        hitbox = new Rectangle((int)x,(int) y,(int) width,(int) height);
    }

    //update position of hit box
    protected void updateHitBox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitBox(){
        return hitbox; 
    }
}
