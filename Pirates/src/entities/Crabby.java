package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;


import main.Game;
public class Crabby extends Enemy {
    
    //attack box => range attack of player 
	private Rectangle2D.Float attackBox; 
    private int attackBoxOffsetX; 

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
        initAttackBox();
    } 

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x , y, (int)(82*Game.SCALE), (int) (19*Game.SCALE));
        attackBoxOffsetX = (int) (30*Game.SCALE);
    }

    public void update(int[][] lvlData, Player player){
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    private void updateBehavior(int[][] lvlData, Player player){
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

                    if(canSeePlayer(lvlData, player))
                        turnTowardsPlayer(player);
                    if(isPlayerCloseForAttack(player))
                        newState(ATTACK);

                    move(lvlData);
                    break;
                case ATTACK: 
                    if (aniIndex == 3 && !attackChecked)
                        checkEnemyHit(player);
                    break; 
                case HIT: 
                    break; 
            }
    }

    public void drawAttackBox(Graphics g, int lvlOffset){
        g.setColor(Color.red);
        g.drawRect( (int) attackBox.x - lvlOffset, (int)attackBox.y,(int) attackBox.width, (int)attackBox.height);
    }

    public int flipX(){
        if(walkDir == RIGHT){
            return width;
        }else return 0; 
    }
     
    public int flipW(){
        if (walkDir == RIGHT){
            return -1;
        }else return 1;
    }
    
}
