/*
 * Members: 
 * Lưu Hoàng Đức – ITITIU21181
 * Nguyễn Hoàng Giang – ITITIU21192
 * Nguyễn Tiến Luân – ITITIU21040
 * Đoàn Bảo Nhật Minh – ITITIU21243
 * 
 * Purpose: cannon properties
 */
package objects;

import main.Game;

public class Cannon extends GameObject {

    private int tileY;
    
    public Cannon(int x, int y, int objType) {
        super (x, y ,objType);
        tileY = y/Game.TILES_SIZE;
        initHitbox(40, 26);
        hitbox.x -= (int)(4 * Game.SCALE);
        hitbox.y += (int)(6 * Game.SCALE);
    }

    public void update() {
        if (doAnimation)
        updateAnimationTick();
    }

    public int getTileY() {
        return tileY;
    }
}
