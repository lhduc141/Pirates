package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;
public class Crabby extends Enemy {

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
    } 

    public void update(int[][] lvlData){
        updateMove(lvlData);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData){
		if (firstUpdate) 
            firstUpdateCheck(lvlData); 

        
        if (inAir){
            updateInAir(lvlData);
        }else 
            switch(enemyState){
                //can help crab can patrol
                //when it idle => make it move
                case IDLE: 
                    newState(RUNNING);
                    break;
                //when it running => change direction when reach can't move place
                case RUNNING:
                    move(lvlData);
                    break;
    
            }
    }
    
}
