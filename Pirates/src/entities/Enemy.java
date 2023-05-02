package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;


import main.Game;

public abstract class Enemy extends Entity {
	protected int aniIndex, enemyState, enemyType;
	protected int aniTick, aniSpeed = 25;
	protected boolean firstUpdate = true;
	protected boolean inAir;
	protected float fallSpeed;
	protected float gravity = 0.04f * Game.SCALE;
	protected float walkSpeed = 0.35f * Game.SCALE;
	protected int walkDir = LEFT;
    
    public Enemy(float x, float y, int width, int height, int enemyType) {
            super(x, y, width, height);
            this.enemyType = enemyType;
            initHitbox(x, y, width, height);
    }

    //check the first status spawn is in air or not base lvlData
    //if enemy isn't on floor => in air & cancel firstUpdate
    protected void firstUpdateCheck(int[][] lvlData){
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        firstUpdate = false;
    }

    //if enemy in air => make it fall
    protected void updateInAir(int[][] lvlData){
        if(CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.y += fallSpeed;
            fallSpeed += gravity; 
        }else {
            inAir  = false; 
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed); 
        }
    }

    //change move direction when it reach place can't move
    protected void move(int[][] lvlData){
        float xSpeed = 0; 

        if(walkDir == LEFT){
            xSpeed = -walkSpeed; 
        }else 
            xSpeed = walkSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)){
                hitbox.x += xSpeed;
                return;
            }
                
        //change Dir
        changeWaldDir();

    }
    
    //reset animation tick when change state
    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniTick = 0; 
        aniIndex = 0; 
    }

    protected void updateAnimationTick(){
        aniTick ++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)){
                aniIndex = 0;
            }
        }
    }

    protected void changeWaldDir(){
        if (walkDir == LEFT) {
                walkDir = RIGHT; 
        }else walkDir = LEFT; 
    }
    
    public int getAniIndex(){
        return aniIndex;
    }

    public int getEnemyState(){
        return enemyState;
    }

}
