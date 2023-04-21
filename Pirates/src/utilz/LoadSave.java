package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";


    
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null    ;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		//add picture

		try {
		    img = ImageIO.read(is);
		}catch (IOException e) {
			e.printStackTrace();
		} finally{
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return img; 
    }

    public static int[][] GetLevelData(){
        int[][] lvData = new int[Game.TILES_HEIGHT][Game.TILES_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int j=0; j< img.getHeight(); j++){
            for (int i=0; i<img.getWidth(); i++){
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvData[j][i] = value; 
            }
        }
        return lvData;
    }

}
