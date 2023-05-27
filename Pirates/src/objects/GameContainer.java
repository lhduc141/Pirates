/*
 * Members: 
 * Lưu Hoàng Đức – ITITIU21181
 * Nguyễn Hoàng Giang – ITITIU21192
 * Nguyễn Tiến Luân – ITITIU21040
 * Đoàn Bảo Nhật Minh – ITITIU21243
 * 
 * Purpose: container properties
 */
package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject{

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        creatHitbox();
    }

    private void creatHitbox() {

        if (objType == BOX){
            initHitbox(25, 18);

            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);

        }else{
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }

        hitbox.y += yDrawOffset + (int)(Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;

    }

    public void update(){
        if (doAnimation)
            updateAnimationTick();
    }
}
