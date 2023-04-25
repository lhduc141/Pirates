package gamestates;

import main.Game;

public class State {
    
    protected Game game; 

    public State(Game game) {
    }

    private Game getGame(){
        return game;
    }
} 
