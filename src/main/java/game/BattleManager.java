package game;

import java.util.List;

import entities.Character;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BattleManager {
	private final List<Character> charactersInBattle;
	private int playingIndex = 0;
	private final ObjectProperty<BattleState> state = new SimpleObjectProperty<>(BattleState.FIRSTCHOICE);

    public BattleManager(List<Character> charactersInBattle) {
        this.charactersInBattle = charactersInBattle;
        charactersInBattle.get(playingIndex).setIsPlaying(true);
    }

    public Character getCurrentCharacter() {
        return charactersInBattle.get(playingIndex);
    }

    public Character getICharacter(int i) {
    	return i < charactersInBattle.size() ? charactersInBattle.get(i) : null;
    }
    
    private boolean isAllyPlaying() {
    	return playingIndex%2 == 0;
    }
    
    private void nextTurn() {
		charactersInBattle.get(playingIndex).setIsPlaying(false);
    	playingIndex = (playingIndex + 1)%charactersInBattle.size();
		charactersInBattle.get(playingIndex).setIsPlaying(true);
		charactersInBattle.get(playingIndex).removeOneTurnFromStats();
		if (isAllyPlaying())
			allyTurn();
		else
			ennemyTurn();
    }
    
    private void allyTurn() {
		charactersInBattle.get(playingIndex).setIsParrying(false);
    }
    
    private void ennemyTurn() {
		charactersInBattle.get(playingIndex).setIsParrying(false);
		nextTurn();
    }
    
    public ObjectProperty<BattleState> getState() {
		return state;
	}

	public void firstChoiceAttack() {
    	state.set(BattleState.ATTACKSELECTION);
    }
	
	public void firstChoiceParry() {
		charactersInBattle.get(playingIndex).setIsParrying(true);
		nextTurn();
	}
}
