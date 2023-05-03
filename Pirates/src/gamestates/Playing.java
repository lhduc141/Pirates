package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Random;

import entities.EnemyManger;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;
import static utilz.Constants.Environment.*;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private EnemyManger enemyManger;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	// this is offset that we gonna add to and remove from => to draw anything a bit to the left or to the right
	private int xLvlOffset;
	// the line the player is beyond and we gonna start calculating if there is anything to move
	//if the width was 100 pixels then player is below 20 pixels and we no need to move to the left
	private int leftBorder = (int) (0.2 * game.GAME_WIDTH); 
	private int rightBorder = (int) (0.8 * game.GAME_WIDTH);

	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	// if gamewidth = 20 but lvlwidth = 30 => maxoffset  = lvlwidth - gamewidth = 10;
	private int maxTilesOffset = lvlTilesWide - game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset *Game.TILES_SIZE;

	private BufferedImage backgroundImg, bigCloud, smallCloud;
	private int [] smallCloudPos;
	private Random rnd = new Random();

	public Playing(Game game) {
		super(game);
		initClasses();
		// create background in game
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAY_BG_IMG);
		// create the big clouds image
		bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
		// create the small clouds image
		smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
		smallCloudPos = new int [8];
		for(int i = 0; i < smallCloudPos.length; i++)
			smallCloudPos[i] = (int)(70 * Game.SCALE) + rnd.nextInt((int) (150 * Game.SCALE));
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		enemyManger = new EnemyManger(this);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), this);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			enemyManger.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		} else {
			pauseOverlay.update();
		}
	}

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff  = playerX - xLvlOffset;

		//if the player is beyond the right border we need to move level to the right and reverse
		if(diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if(diff < leftBorder)
			xLvlOffset += diff - leftBorder;
		// make sure leveloffset not get too high and less than 0
		if(xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if(xLvlOffset < 0)
			xLvlOffset = 0;

		}


	@Override
	public void draw(Graphics g) {
		// draw background for game
		g.drawImage(backgroundImg, 0, 0, game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		drawClouds(g);

		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		enemyManger.draw(g, xLvlOffset);

		if (paused){
			// make the side pause screen become darker 
			g.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
			pauseOverlay.draw(g);
	}
}
	//  add clouds into the main screen 
	private void drawClouds(Graphics g) {
		// create more big clouds and make them move
		for(int i = 0; i < 3; i++)
			g.drawImage(bigCloud, i * BIG_CLOUDS_WIDTH -(int)(xLvlOffset * 0.3), (int)(204 * Game.SCALE), BIG_CLOUDS_WIDTH, BIG_CLOUDS_HEIGHT, null);
		// create the random number of small clouds in the sky and make it move when character move
		for(int i = 0; i < smallCloudPos.length; i++)
			g.drawImage(smallCloud, SMALL_CLOUDS_WIDTH * 4 * i - (int)(xLvlOffset * 0.7), smallCloudPos[i], SMALL_CLOUDS_WIDTH, SMALL_CLOUDS_HEIGHT, null);
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// if (e.getButton() == MouseEvent.BUTTON1)
		// 	player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		//move
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		//Jump
		case KeyEvent.VK_K:
			player.setJump(true);
			break;
		//pause
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_K:
			player.setJump(false);
			break;
		case KeyEvent.VK_J: 
			player.setAttacking(true);
			break;	
		}
	}

	public void resetAll(){
		//TODO: reset all player enemy, lvl ect. 
	}

	public void checkEnemyHit(Rectangle2D.FLoat attackBox){
		enemyManger.checkEnemyHit(attackBox);
	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);

	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}
