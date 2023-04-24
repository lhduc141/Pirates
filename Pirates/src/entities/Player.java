package entities;

import static utilz.Constant.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	//jumping / Gravity 
	private float airSpeed = 0f; 
	private float gravity = 0.04f *Game.SCALE;
	private float jumpSpeed = -2.25f *Game.SCALE; 
	private float fallSpeedAfterCollision = 0.5f *Game.SCALE;
	private boolean inAir = false; 

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, 20*Game.SCALE, 27*Game.SCALE);
	}

	public void update() {
		updatePos(); // update positive
		// updateHitBox();
		updateAnimationTick();
		setAnimation(); // choose animation: IDLE, RUNNING, JUMP, FALL,....
	}

	public void render(Graphics g) {
		// draw animation
		// playerAction now is IDLE
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		drawHitBox(g);
	}

	public void updateAnimationTick() {
		aniTick++; // count to 25
		if (aniTick >= aniSpeed) {
			aniTick = 0; // reset aniTick
			aniIndex++; // change SubImage
			if (aniIndex >= GetSpiteAmount(playerAction)) {
				// move, attack, jump, fall => reset aniIndex to 0 (first column)
				aniIndex = 0;
				attacking = false;
			}
		}
	}

	private void setAnimation() {

		int startAnimation = playerAction;

		if (moving) {
			playerAction = RUNNING;
		} else
			playerAction = IDLE;

		if (attacking) {
			playerAction = ATTACK_1;
		}

		//animation jump, then failing 
		if (inAir){
			if (airSpeed<0) 	
				playerAction = JUMP;
			else playerAction = FALLING;
		}

		if (startAnimation != playerAction) {
			// resetAnimationTick();
			aniTick = 0;
			aniIndex = 0;
		}

	}

	// private void resetAnimationTick() {
	// aniTick = 0;
	// aniIndex = 0;
	// }

	private void updatePos() {
		moving = false;

		if(jump) jump();
		if (!left && !right && !inAir)
			return; //return false

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		if(!inAir){
			if(!IsEntityOnFloor(hitbox, lvlData)){
				inAir = true; 
			}
		}

		if (inAir){
			if (CanMoveHere(hitbox.x, hitbox.y +airSpeed, hitbox.width, hitbox.height, lvlData)){
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}else {
				hitbox.y = GetEntityYPosUnderRoofOfAboveFloor(hitbox, airSpeed);

				if(airSpeed >0){ //resetInAir
					inAir = false; 
					airSpeed = 0;
				}else airSpeed = fallSpeedAfterCollision;

				updateXPos(xSpeed);
			}
		}else 
			updateXPos(xSpeed);
		
		moving = true; 
	}

	private void jump() {
		if(inAir) return; 
		inAir = true; 
		airSpeed = jumpSpeed; 
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
	}

	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		// creat the Arrray animation
		// In this picture, there is max 6 columns and max 9 rows
		animations = new BufferedImage[9][6];

		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[j].length; j++) {
				// make a Sub-image
				animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
			}
		}
	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData)){
			inAir = true; 
		}
	}

	// out of screen => stop action
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	// setter attack
	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}

	//setter Jump
	public void setJump(boolean jump){
		this.jump = jump;
	}

	// Getter & setter KeyboardInputs
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
