package levels;

public class Level {
    
    private int[][] lvData;

    public Level(int[][] lvData){
        this.lvData = lvData;
    }

    public int getSpiriteIndex(int x, int y){
        return lvData[y][x];
    }

    public int[][] getLevelData(){
        return lvData;
    }
    
}
