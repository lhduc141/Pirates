package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UP_SET = 200;
	
	public final static int DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_WIDTH = 26; 
	public final static int TILES_HEIGHT = 14; 
	public final static int SIZE = (int) (DEFAULT_SIZE * SCALE); 
	public final static int GAME_WIDTH = SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = SIZE * TILES_HEIGHT;



	private Player player;
	private LevelManager levelManager; 

	public Game() {
		initClasses(); // creat player
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();	

		startGameLoop(); 
	}

	private void initClasses() {
		player = new Player(200,200);
		levelManager = new LevelManager(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update(){
		player.update();
		levelManager.update();
	}

	public void render(Graphics g) {
		player.render(g);
		levelManager.draw(g);
	}

	@Override
	//repaint 
	 public void run() {

		double timeFrame = 1000000000.0 / FPS_SET;
		double timeUpdate =  1000000000.0 / UP_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0; 
		long Check = System.currentTimeMillis();

		double dU = 0;
		double dF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			dU  += (currentTime - previousTime) / timeUpdate;
			dF  += (currentTime - previousTime) / timeUpdate;
			previousTime = currentTime;

			if (dU >= 1){
				update();
				updates++;
				dU--;
			}
			if (dF >= 1){
				gamePanel.repaint();
				dF--;
				frames++;
			}

			if (System.currentTimeMillis() - Check >= 1000) {
				Check = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "| UPS: "+updates);
				frames = 0;
				updates =0;
			}
		}

	}

	public void windowLostFocus(){
		player.resetDirBooleans();
	}

	public Player getPlayer(){
		return player;
	}
}
