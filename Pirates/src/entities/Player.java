package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constant.PlayerConstants.*;

import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity {
    private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE; 
	private boolean left, right, up, down;
	private float playerSpeed = 2.0f;

	private boolean moving = false, attacking = false;
    

    public Player(float x, float y) {
        super(x, y);
		loadAnimations();
    }

    public void update(){
		updatePost();	//update positive
		updateAnimationTick();
		setAnimation();	//choose animation: IDLE, RUNNING, JUMP, FALL,.... 
    }



	public void render(Graphics g){
		//draw animation
		//playerAction now is IDLE
		g.drawImage(animations[playerAction][aniIndex], (int)x , (int)y , 256,160,  null);

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

	private void updatePost() {
		moving = false ;
		if (left && !right){
			x -= playerSpeed;
			moving = true;
		} else if (right && !left){
			x += playerSpeed;
			moving = true;
		}

		if (up && !down){
			y -= playerSpeed;
			moving = true;
		}else if (down && !up){
			y += playerSpeed;
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
