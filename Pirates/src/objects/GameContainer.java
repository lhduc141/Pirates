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
    }

    public void update(){
        if (doAnimation)
            updateAnimationTick();
    }
}
