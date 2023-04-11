package inputs;
import java.awt.event.KeyListener;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class Keyboardinputs implements KeyListener{
    private GamePanel GamePanel;
    public Keyboardinputs(GamePanel gamePanel){
        this.GamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode()){
            case KeyEvent.VK_W:
            GamePanel.changeYdenta(-5);
            break;
            case KeyEvent.VK_A:
            GamePanel.changeXdenta(-5);
            break;
            case KeyEvent.VK_S:
            GamePanel.changeYdenta(5);
            break;
            case KeyEvent.VK_D:
            GamePanel.changeXdenta(5);
            break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
