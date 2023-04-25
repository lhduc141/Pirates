package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.*;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void setPanelSize() {
		//width = 1248 
		//height = 672
		// Dimension size = new Dimension(672, 1248);
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println("Size:" + GAME_WIDTH + ":" + GAME_HEIGHT);
	}

	public void updateGame(){
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}


	public Game getGame(){
		return game; 
	}
}
