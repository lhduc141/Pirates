package entities;

public abstract class Enemy extends Entity {

    private int aniIndex, enemyState, enemyType;
    private aniTick, aniSpeed = 25;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    }
    
}
