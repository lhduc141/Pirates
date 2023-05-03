package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;

public class GameOverOverlay {

    private Playing playing;
    public GameOverOverlay (Playing playing){
        this.playing = playing;
    }
    
    public void draw(Graphics g){
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("NGUUUUUUU", Game.GAME_WIDTH/2, 150);
        g.drawString("PRESS ESC TO ENTER MAIN MENU", Game.GAME_WIDTH/2, 300);
    }

    public void KeyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU; 
        }
    }
}
