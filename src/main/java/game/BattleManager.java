package game;

import java.util.List;

import entities.Character;

public class BattleManager {
	private final List<Character> charactersInBattle;
	private int playingIndex = 0;

    public BattleManager(List<Character> charactersInBattle) {
        this.charactersInBattle = charactersInBattle;
    }

    public Character getCurrentCharacter() {
        return charactersInBattle.get(playingIndex);
    }

    public void nextTurn() {
    	playingIndex = (playingIndex + 1)%charactersInBattle.size();
    }
    
    public Character getICharacter(int i) {
    	return i < charactersInBattle.size() ? charactersInBattle.get(i) : null;
    }
}
