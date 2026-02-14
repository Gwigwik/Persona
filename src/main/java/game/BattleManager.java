package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.SceneManager;
import app.SceneType;
import entities.Character;
import entities.spells.Spell;
import entities.stats.Stat;
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
    private SceneManager sceneManager;
    
    private static boolean playAgain = false;

    public BattleManager(List<Character> charactersInBattle, SceneManager sceneManager) {
        this.charactersInBattle = charactersInBattle;
        this.sceneManager = sceneManager;
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
    	if (!anyAllyAlive()) {
    		showMessage("Perdu !", 2, BattleState.PLAYERLOST);
    		sceneManager.switchTo(SceneType.MENU);
    	} else {
	    	if (!playAgain) {
	    		charactersInBattle.get(playingIndex).setIsPlaying(false);    		
	    		playingIndex = (playingIndex + 1)%charactersInBattle.size();
	    		charactersInBattle.get(playingIndex).setIsPlaying(true);
	    		charactersInBattle.get(playingIndex).setIsStun(false);
	    		charactersInBattle.get(playingIndex).setIsParrying(false);
	    		List<Stat> statsToDefault = charactersInBattle.get(playingIndex).removeOneTurnFromStats();
	    		String statsToPrint = statsToDefault.stream()
	    				.map(stat -> stat.getName())
	    				.collect(Collectors.joining(", "));
	    		if (statsToDefault.size() > 0)
	    			showMessage("Altération" + (statsToDefault.size() > 1?"s":"") + " sur " + statsToPrint + " de " + charactersInBattle.get(playingIndex).getName() + " terminée" + (statsToDefault.size() > 1?"s":"") + " !", 3, BattleState.FIRSTCHOICE);
	    	}
	    	playAgain = false;
			if (charactersInBattle.get(playingIndex).isAlive()) {
				if (isAlly(playingIndex))
					allyTurn();
				else
					ennemyTurn();
			} else {
				nextTurn();
			}
    	}
    }
    
    private void allyTurn() {
    }
    
    private void ennemyTurn() {
		nextTurn();
    }
    
    private boolean anyAllyAlive() {
    	for (int i = 0; i < charactersInBattle.size(); i++) {
    	    Character character = charactersInBattle.get(i);
    	    if (character.isAlive() && isAlly(i)) {
    	    	return true;
    	    }
    	}
    	return false;
    }
    
    public ObjectProperty<BattleState> getState() {
		return state;
	}
    
    public static void setPlayAgain() { playAgain = true; }
    
	public StringProperty getMessage() {
		return message;
	}
	
	public void showMessage(String text, double duration, BattleState nextState) {
		state.set(BattleState.MESSAGEDISPLAYING);
        message.set(text);
        if (messageTimeline != null)
        	messageTimeline.stop();
        messageTimeline = new Timeline(
            new KeyFrame(Duration.seconds(duration), _ -> { message.set(""); state.set(nextState); })
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
			showMessage("AP insuffisants !", 2, BattleState.PERSONASPELLSELECTION);
		} else {
			spellSelected = spell;
			state.set(BattleState.PERSONAATTACKSELECTION);
		}
	}
	
	public void characterClicked(int index) {
		Character playingCharacter = charactersInBattle.get(playingIndex);
		switch (state.get()) {
			case ATTACKSELECTION:
//				if (isAlly(index)) TODO a remettre quand les tests sont finis et ajouter les check de en vie ou non pour resurrect (et inverse pour le reste)
//					return;
				switch (playingCharacter.getAttackType()) {
					case PHYSICAL:
						Spell.PHYSIQUE.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					case GUN:
						Spell.PISTOLET.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					default:
				}
				showMessage(playingCharacter.getName() + " récupère des PA", 1.5, BattleState.FIRSTCHOICE);
				nextTurn();
				break;
			case PERSONAATTACKSELECTION:
				if ((spellSelected == Spell.RECARM || spellSelected == Spell.SAMARECARM) && charactersInBattle.get(index).isAlive()) {
					showMessage("Impossible de lancer ce sort sur un allié en vie !", 2, state.get());
					break;
				}
				if (spellSelected != Spell.RECARM && spellSelected != Spell.SAMARECARM && !charactersInBattle.get(index).isAlive() && spellSelected.targetAllies() && isAlly(index)) {
					showMessage("Impossible de lancer ce sort sur un allié à terre !", 2, state.get());
					break;
				}
				if (spellSelected.isGlobal()) {
					if (spellSelected.targetAllies() && isAlly(index)) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(0), charactersInBattle.get(2), charactersInBattle.get(4))));
						state.set(BattleState.FIRSTCHOICE);
						nextTurn();
					}
					if (!spellSelected.targetAllies() && !isAlly(index)) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(1), charactersInBattle.get(3), charactersInBattle.get(5))));
						state.set(BattleState.FIRSTCHOICE);
						nextTurn();
					}
				} else {
					if ((spellSelected.targetAllies() && isAlly(index)) || (!spellSelected.targetAllies() && !isAlly(index))) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						state.set(BattleState.FIRSTCHOICE);
						nextTurn();
					}
				}
				break;
			default:
				return;
		}
	}
}
