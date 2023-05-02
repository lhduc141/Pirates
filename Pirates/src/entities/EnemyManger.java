package entities;
//similarity with level manager

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManger {
    
    private Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies = new ArrayList<>(); 

    public EnemyManger(Playing playing){
        this.playing = playing; 
        loadEnemyImg();
        addEnemies();
    }

    private void addEnemies() {
        crabbies = LoadSave.GetCrabs();
    }

    public void update(int[][] lvlData){
        for (Crabby c: crabbies){
            c.update(lvlData);
        }
    }

    public void draw(Graphics g, int xLvlOffset){
        drawCrabs(g, xLvlOffset);
    }

    public void drawCrabs(Graphics g, int xLvlOffset){
        for (Crabby c: crabbies){
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset -CRABBY_DRAWOFFSET_X,(int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y,  CRABBY_WIDTH, CRABBY_HEIGHT, null);
            c.drawHitbox(g, xLvlOffset);
        }
    }

    private void loadEnemyImg(){
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int i =0; i<crabbyArr.length; i++)
            for (int j=0; j<crabbyArr[i].length; j++)
                crabbyArr[i][j] = temp.getSubimage(j* CRABBY_WIDTH_DEFAULT, i*CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
    }
}
