package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;

public class GameOverOverlay {

    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;

    public GameOverOverlay (Playing playing){
        this.playing = playing;
        createImg();
    }
    
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
        imgW = (int)(img.getWidth() * Game.SCALE);
        imgH = (int)(img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH/2 - imgW/2;
        imgY = (int)(100*Game.SCALE);
    }

    public void draw(Graphics g){
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, imgX, imgY, imgW, imgH, null);


        // g.setColor(Color.white);
        // g.drawString("NGUUUUUUU", Game.GAME_WIDTH/2, 150);
        // g.drawString("PRESS ESC TO ENTER MAIN MENU", Game.GAME_WIDTH/2, 300);

    }

    public void KeyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU; 
        }
    }
}
