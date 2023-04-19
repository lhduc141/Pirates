package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
    
    private Game game;
    private BufferedImage levelSprite;

    public LevelManager(Game game){
        this.game = game; 
        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }

    public void draw(Graphics g){
        g.drawImage(levelSprite, 0, 0, null);
    }

    public void update(){

    }
}

