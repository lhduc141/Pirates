package main;

public class Game {
	
	private GameWindown gameWindown;
	private GamePanel gamePanel;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindown = new GameWindown(gamePanel);
		gamePanel.requestFocus();
		
		
		
	}
}
