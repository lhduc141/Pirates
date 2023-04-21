package utilz;

import main.Game;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvData){
            if(!IsSolid(x, y, lvData))
                if(!IsSolid(x+width, y+height, lvData))
                    if(!IsSolid(x+width, y, lvData))
                        if(!IsSolid(x, y+height, lvData))
                            return true; 
            return false; 
    }


    public static boolean IsSolid(float x, float y, int[][] lvData){
        if(x<0 || x>Game.GAME_WIDTH)
            return true;
        if (y<0 || y>Game.GAME_HEIGHT)
            return true; 

        float xIndex = x / Game.SIZE; 
        float yIndex = y / Game.SIZE;

        int value = lvData[(int) xIndex][(int) yIndex];

        if (value>=48 || value<0 || value!=11){
            return true;
        }else return false; 
    }

}
