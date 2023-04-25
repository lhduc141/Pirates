package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UP_SET = 200;

	private Playing playing;
	private Menu menu;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f; //scale 2.0f
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {
		initClasses(); // creat player

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {	
		
		menu = new Menu(this);
		playing = new Playing(this);
		
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch (Gamestate.state){
			case MENU:
				menu.update();
				break;
			case PLAYING:
				playing.update();
				break;
			default:
				break;
		}
	}

	public void render(Graphics g) {
		switch (Gamestate.state){
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
				break;
			default:
				break;
		}

	}

	@Override
	// repaint
	public void run() {

		double timeFrame = 1000000000.0 / FPS_SET;
		double timeUpdate = 1000000000.0 / UP_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long Check = System.currentTimeMillis();

		double dU = 0;
		double dF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			dU += (currentTime - previousTime) / timeUpdate;
			dF += (currentTime - previousTime) / timeFrame;
			previousTime = currentTime;

			if (dU >= 1) {
				update();
				updates++;
				dU--;
			}
			if (dF >= 1) {
				gamePanel.repaint();
				dF--;
				frames++;
			}

			if (System.currentTimeMillis() - Check >= 1000) {
				Check = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "| UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}

	public void windowLostFocus() {
		if(Gamestate.state == Gamestate.PLAYING){
			playing.getPlayer().resetDirBooleans();
		}
	}

	public Menu getMenu(){
		return menu;
	}

	public Playing getPlaying(){
		return playing;
	}
}
