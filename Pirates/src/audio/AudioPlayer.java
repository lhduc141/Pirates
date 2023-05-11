package audio;

import java.util.Random;

import javax.sound.sampled.Clip;

public class AudioPlayer {
    
    public static int MENU_1 = 0;
	public static int LEVEL_1 = 1;
	public static int LEVEL_2 = 2;

	public static int DIE = 0;
	public static int JUMP = 1;
	public static int GAMEOVER = 2;
	public static int LVL_COMPLETED = 3;
	public static int ATTACK_ONE = 4;
	public static int ATTACK_TWO = 5;
	public static int ATTACK_THREE = 6;

    private Clip[] songs, effects;
    private int currentSongId;
	private float volume = 0.5f;
	private boolean songMute, effectMute;
	private Random rand = new Random();
    
    public AudioPlayer() {

    }
}
