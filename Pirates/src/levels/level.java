package levels;

public class Level {
    private int[][] lvlData;

    public Level(int[][] lvData) {
        this.lvlData = lvData;
    }

    public int getSpiriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }
}
