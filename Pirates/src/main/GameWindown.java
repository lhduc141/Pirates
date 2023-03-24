package main;

import javax.swing.JFrame;

public class GameWindown {
	private JFrame jframe;

	public GameWindown(GamePanel gamePanel){
		
		jframe = new JFrame();
		
		jframe.setSize(400,400);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setVisible(true);
	}
}
