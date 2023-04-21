package entities;

import static utilz.Constant.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class Player extends Entity {
    private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE; 
	private boolean moving = false, attacking = false;
	private boolean left, right, up, down;
	private float playerSpeed = 2.0f;
	private int[][] lvData; 

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
		loadAnimations();
    }

    public void update(){
		updatePos();	//update positive
		updateHitBox();
		updateAnimationTick();
		setAnimation();	//choose animation: IDLE, RUNNING, JUMP, FALL,.... 
    }



	public void render(Graphics g){
		//draw animation
		//playerAction now is IDLE
		g.drawImage(animations[playerAction][aniIndex], (int)x , (int)y ,  width, height,  null);
		drawHitBox(g);
    }

	public void updateAnimationTick() {
		aniTick ++; // count to 25
		if (aniTick >= aniSpeed){
			aniTick =0; //reset aniTick 
			aniIndex ++; //change SubImage 
			if (aniIndex >= GetSpiteAmount(playerAction)){ 
				//move, attack, jump, fall => reset aniIndex to 0 (first column)
				aniIndex = 0; 
				attacking = false;
			}
		}
	}

	private void setAnimation() {

		int startAnimation = playerAction;

		if(moving ){
			playerAction = RUNNING;
		}else 
			playerAction = IDLE;

		if (attacking){
			playerAction = ATTACK_1;
		}

		if (startAnimation != playerAction){
			// resetAnimationTick();
			aniTick = 0; 
			aniIndex = 0;
		}

	}

	// private void resetAnimationTick() {
	// 	aniTick = 0; 
	// 	aniIndex = 0;
	// }

	private void updatePos() {
		moving = false ;

		if(!left && !right && !up && !down)
			return;
		
		float xSpeed = 0 , ySpeed = 0;


		if (left && !right)
			xSpeed = -playerSpeed;
		else if (right && !left)
			xSpeed = +playerSpeed;

		if (up && !down)
			ySpeed = -playerSpeed;
		else if (down && !up)
			ySpeed = +playerSpeed;
		
		if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvData)){
			this.x += xSpeed;
			this.y += ySpeed;
			moving = true; 
		}


	}

    private void loadAnimations() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

			//creat the Arrray animation
			//In this picture, there is max 6 columns and max 9 rows
            animations = new BufferedImage[9][6];  

		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[j].length; j++){
				//make a Sub-image
				animations[i][j] = img.getSubimage(j*64, i*40, 64, 40);
			}
		}
	}

	public void loadLvData(int[][] lvData){
		this.lvData = lvData;
	}

	//out of screen => stop action
	public void resetDirBooleans(){
		left = false;
		right = false;
		up = false;
		down = false;
	}

	//setter attack
	public void setAttack(boolean attacking){
		this.attacking = attacking; 
	}

	//Getter & setter KeyboardInputs
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
    
}
