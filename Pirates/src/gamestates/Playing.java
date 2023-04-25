package gamestates;

import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{
    private Player player; 
    private LevelManager levelManager;

    public Playing(Game game){
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());   
    }


    //IMPLEMENT METHODs
    @Override
    public void update() { 
        levelManager.update();
        player.update();
    }
    public void draw(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e)  {}
    public void mouseMoved(MouseEvent e)  {}
    public void mouseClicked(MouseEvent e) {
        // if (e.getButton() == MouseEvent.BUTTON1)
		// 	    player.setAttack(true);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            //key
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;

            //key attack => Hoang Duc Choose J is attack
            case KeyEvent.VK_J:
                player.setAttack(true);
                break;

            //key jump => Cyber choose K is jump 
            case KeyEvent.VK_K:
                player.setJump(true);
                break;

            //back to menu: original is BacksSpace
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
            }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
                player.setRight(false); 
				break;
            //attack
			case KeyEvent.VK_J:
                player.setRight(false);
				break;
            //jump
			case KeyEvent.VK_K:
                player.setJump(false);
				break;
    }
    }

    public Player getPlayer(){
        return player;
    }

}
