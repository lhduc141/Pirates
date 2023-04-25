package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // draw hit box
    protected void drawHitBox(Graphics g) {
        // for debugging the hit box
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // creat hit box
    protected void initHitBox(float x, float y, float width, float height) { 
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    // update position of hit box
    // protected void updateHitBox() {
    //     hitbox.x = (int) x;
    //     hitbox.y = (int) y;
    // }
    
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}
