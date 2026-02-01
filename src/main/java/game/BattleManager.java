package game;

import java.util.ArrayList;
import java.util.List;

import entities.Character;
import entities.spells.Spell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

public class BattleManager {
	private final List<Character> charactersInBattle;
	private int playingIndex = 0;
	private Spell spellSelected;
	private final ObjectProperty<BattleState> state = new SimpleObjectProperty<>(BattleState.FIRSTCHOICE);
	private StringProperty message = new SimpleStringProperty("");
    private Timeline messageTimeline;

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
    
    private boolean isAlly(int index) {
    	return index%2 == 0;
    }
    
    private void nextTurn() {
		state.set(BattleState.FIRSTCHOICE);
		charactersInBattle.get(playingIndex).setIsPlaying(false);
    	playingIndex = (playingIndex + 1)%charactersInBattle.size();
		charactersInBattle.get(playingIndex).setIsPlaying(true);
		charactersInBattle.get(playingIndex).removeOneTurnFromStats();
		if (charactersInBattle.get(playingIndex).isAlive()) {
			if (isAlly(playingIndex))
				allyTurn();
			else
				ennemyTurn();
		} else {
			nextTurn();
		}
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

	public StringProperty getMessage() {
		return message;
	}
	
	public void showMessage(String text, int duration) {
        message.set(text);
        if (messageTimeline != null)
        	messageTimeline.stop();
        messageTimeline = new Timeline(
            new KeyFrame(Duration.seconds(duration), _ -> message.set(""))
        );
        messageTimeline.setCycleCount(1);
        messageTimeline.play();
    }
	
	public void firstChoiceAttack() {
    	state.set(BattleState.ATTACKSELECTION);
    }
	
	public void cancelChoice() {
		switch(state.get()) {
			case ATTACKSELECTION, PERSONASPELLSELECTION:
				state.set(BattleState.FIRSTCHOICE);
				break;
			case PERSONAATTACKSELECTION:
				state.set(BattleState.PERSONASPELLSELECTION);
				break;
			default:
		}
	}
	
	public void firstChoiceParry() {
		charactersInBattle.get(playingIndex).setIsParrying(true);
		nextTurn();
	}
	
	public void firstChoicePersona() {
		state.set(BattleState.PERSONASPELLSELECTION);
	}
	
	public void spellPersonaClicked(Spell spell) {
		if (spell.getAPCost() > charactersInBattle.get(playingIndex).getCurrentAP()) {
			showMessage("AP insuffisants !", 2);
		} else {
			spellSelected = spell;
			state.set(BattleState.PERSONAATTACKSELECTION);
		}
	}
	
	public void characterClicked(int index) {
		switch (state.get()) {
			case ATTACKSELECTION:
//				if (isAlly(index)) TODO a remettre quand les tests sont finis 
//					return;
				switch (charactersInBattle.get(playingIndex).getAttackType()) {
					case PHYSICAL:
						Spell.PHYSIQUE.spellEffect(charactersInBattle.get(playingIndex), new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					case GUN:
						Spell.PISTOLET.spellEffect(charactersInBattle.get(playingIndex), new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					default:
				}
				nextTurn();
				break;
			case PERSONAATTACKSELECTION:
				if (spellSelected.isGlobal()) {
					if (spellSelected.targetAllies() && isAlly(index)) {
						spellSelected.spellEffect(charactersInBattle.get(playingIndex), new ArrayList<>(List.of(charactersInBattle.get(0), charactersInBattle.get(2), charactersInBattle.get(4))));
						nextTurn();
					}
					if (!spellSelected.targetAllies() && !isAlly(index)) {
						spellSelected.spellEffect(charactersInBattle.get(playingIndex), new ArrayList<>(List.of(charactersInBattle.get(1), charactersInBattle.get(3), charactersInBattle.get(5))));
						nextTurn();
					}
				} else {
					if ((spellSelected.targetAllies() && isAlly(index)) || (!spellSelected.targetAllies() && !isAlly(index))) {
						spellSelected.spellEffect(charactersInBattle.get(playingIndex), new ArrayList<>(List.of(charactersInBattle.get(index))));
						nextTurn();
					}
				}
				break;
			default:
				return;
		}
	}
}
