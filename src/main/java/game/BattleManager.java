package game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import app.SceneManager;
import app.SceneType;
import entities.Character;
import entities.spells.Spell;
import entities.stats.Stat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

public class BattleManager {
	private SceneType nextScene;
	private final List<Character> charactersInBattle;
	private int playingIndex = 0;
	private Spell spellSelected;
	private final ObjectProperty<BattleState> state = new SimpleObjectProperty<>(BattleState.FIRSTCHOICE);
	private StringProperty message = new SimpleStringProperty("");
    private Timeline messageTimeline;
    private SceneManager sceneManager;
    private IntegerProperty spellActualizer = new SimpleIntegerProperty(0);

    private static boolean playAgain = false;

    public BattleManager(List<Character> charactersInBattle, SceneManager sceneManager, SceneType nextScene) {
    	this.nextScene = nextScene;
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
    		showMessage("Perdu !", 2, BattleState.PLAYERLOST, false);
    		sceneManager.switchTo(SceneType.MENU);
    	} else {
    		if (!anyEnnemyAlive()) {
    			charactersInBattle.get(0).resetCharacter();
    			charactersInBattle.get(2).resetCharacter();
    			charactersInBattle.get(4).resetCharacter();
    			playAgain = false;
        		sceneManager.switchTo(nextScene);
    			charactersInBattle.get(0).setIsPlaying(true);
    		} else {
		    	if (!playAgain) {
		    		charactersInBattle.get(playingIndex).setIsPlaying(false);    		
		    		playingIndex = (playingIndex + 1)%charactersInBattle.size();
		    		charactersInBattle.get(playingIndex).setIsPlaying(true);
		    		charactersInBattle.get(playingIndex).setIsStun(false);
		    		charactersInBattle.get(playingIndex).setIsParrying(false);
		    		if (charactersInBattle.get(playingIndex).isAlive()) {
			    		List<Stat> statsToDefault = charactersInBattle.get(playingIndex).removeOneTurnFromStats();
			    		String statsToPrint = statsToDefault.stream()
			    				.map(stat -> stat.getName())
			    				.collect(Collectors.joining(", "));
			    		if (statsToDefault.size() > 0)
			    			showMessage("Altération" + (statsToDefault.size() > 1?"s":"") + " sur " + statsToPrint + " de " + charactersInBattle.get(playingIndex).getName() + " terminée" + (statsToDefault.size() > 1?"s":"") + " !", 3, BattleState.FIRSTCHOICE, false);
		    		}
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
    }
    
    private void allyTurn() {
    	setSpellActualizer();
    }

    public IntegerProperty spellActualizer() {
        return spellActualizer;
    }

    public void setSpellActualizer() {
        spellActualizer.set((spellActualizer.get()+1%2));
    }
    
    private void ennemyTurn() {
    	Character ennemyPlaying = charactersInBattle.get(playingIndex);
    	switch (ennemyPlaying.getName()) {
    		case "Matt":
    			switch (ennemyPlaying.getSpells().get(ThreadLocalRandom.current().nextInt(2))) {
    				case EIGAON:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.EIGAON.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Matt lance Eigaon sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
    				case MAEIGAON:
    					Spell.MAEIGAON.spellEffect(ennemyPlaying, getAllAlliesAlive());
    					showMessage("Matt lance Maeigaon", 2, BattleState.FIRSTCHOICE, true);
    					break;
    				default:
    			}
    			break;
    		case "Sophie", "Manon":
    			Character matt = charactersInBattle.get(1);
    			if (matt.isAlive()) {
    				if (matt.getCurrentHP() < matt.getMaxHP()) {
    					Spell.DIARAMA.spellEffect(ennemyPlaying, new ArrayList<>(List.of(matt))); //heal
						showMessage(ennemyPlaying.getName() + " lance Diarama sur Matt", 2, BattleState.FIRSTCHOICE, true);
    				}
    				else {
    					ennemyPlaying.getSpells().get(1).spellEffect(ennemyPlaying, new ArrayList<>(List.of(matt))); //buff
						showMessage(ennemyPlaying.getName() + " lance " + ennemyPlaying.getSpells().get(1).getName() + " sur Matt", 2, BattleState.FIRSTCHOICE, true);
    				}
    			} else {
					Spell.RECARM.spellEffect(ennemyPlaying, new ArrayList<>(List.of(matt))); //revive
					showMessage(ennemyPlaying.getName() + " lance Recarm sur Matt", 2, BattleState.FIRSTCHOICE, true);
    			}
    			break;
    		case "Flo":
				switch (ThreadLocalRandom.current().nextInt(5)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.PSIODYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Flo lance " + Spell.PSIODYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MAPSIODYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Flo lance " + Spell.MAPSIODYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
				}
				break;
    		case "Hugo":
				switch (ThreadLocalRandom.current().nextInt(6)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.FREIDYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Hugo lance " + Spell.FREIDYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MAFREIDYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Hugo lance " + Spell.MAFREIDYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 5:
						Spell.MASUKUNDA.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Hugo lance " + Spell.MASUKUNDA.getName(), 2, BattleState.FIRSTCHOICE, true);
						break;
				}
    			break;
    		case "Mara":
				switch (ThreadLocalRandom.current().nextInt(6)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.GARUDYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Mara lance " + Spell.GARUDYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MAGARUDYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Mara lance " + Spell.MAGARUDYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 5:
						Spell.MASUKUKAJA.spellEffect(ennemyPlaying, new ArrayList<>(getAllEnnemiesAlive()));
    					showMessage("Mara lance " + Spell.MASUKUKAJA.getName(), 2, BattleState.FIRSTCHOICE, true);
						break;
				}
    			break;
    		case "Adrien":
				switch (ThreadLocalRandom.current().nextInt(6)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.AGIDYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Adrien lance " + Spell.AGIDYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MARAGIDYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Adrien lance " + Spell.MARAGIDYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 5:
						Spell.MATARUNDA.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Adrien lance " + Spell.MATARUNDA.getName(), 2, BattleState.FIRSTCHOICE, true);
						break;
				}
    			break;
    		case "Neoli":
				switch (ThreadLocalRandom.current().nextInt(6)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.ZIODYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Neoli lance " + Spell.ZIODYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MAZIODYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Neoli lance " + Spell.MAZIODYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 5:
						Spell.MARAKUKAJA.spellEffect(ennemyPlaying, new ArrayList<>(getAllEnnemiesAlive()));
    					showMessage("Neoli lance " + Spell.MARAKUKAJA.getName(), 2, BattleState.FIRSTCHOICE, true);
						break;
				}
    			break;
    		case "Brice":
				switch (ThreadLocalRandom.current().nextInt(5)) {
					case 0, 1:
    					Character allyTargeted = getRandomAllyAlive();
    					Spell.BUFUDYNE.spellEffect(ennemyPlaying, new ArrayList<>(List.of(allyTargeted)));
    					showMessage("Brice lance " + Spell.BUFUDYNE.getName() + " sur " + allyTargeted.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
					case 2, 3, 4:
						Spell.MABUFUDYNE.spellEffect(ennemyPlaying, new ArrayList<>(getAllAlliesAlive()));
    					showMessage("Brice lance " + Spell.MABUFUDYNE.getName(), 2, BattleState.FIRSTCHOICE, true);
    					break;
				}
    			break;
    		default:
    	}
    }
    
    private List<Character> getAllAlliesAlive() {
		List<Character> allies = IntStream.range(0, 6)
		        .filter(i -> i % 2 == 0)
		        .mapToObj(charactersInBattle::get)
		        .toList();   
		return allies.stream()
		        .filter(c -> c.isAlive())
		        .collect(Collectors.toList());
    }
    
    private Character getRandomAllyAlive() {
    	List<Character> alliesAlive = getAllAlliesAlive();
		return alliesAlive.get(ThreadLocalRandom.current().nextInt(alliesAlive.size()));
    }

    private List<Character> getAllEnnemiesAlive() {
		List<Character> ennemies = IntStream.range(0, 6)
		        .filter(i -> i % 2 != 0)
		        .mapToObj(charactersInBattle::get)
		        .toList();   
		return ennemies.stream()
		        .filter(c -> c.isAlive())
		        .collect(Collectors.toList());
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
    
    private boolean anyEnnemyAlive() {
    	for (int i = 0; i < charactersInBattle.size(); i++) {
    	    Character character = charactersInBattle.get(i);
    	    if (character.isAlive() && !isAlly(i)) {
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
	
	public void showMessage(String text, double duration, BattleState nextState, boolean nextTurn) {
		state.set(BattleState.MESSAGEDISPLAYING);
        message.set(text);
        if (messageTimeline != null)
        	messageTimeline.stop();
        messageTimeline = new Timeline(
            new KeyFrame(Duration.seconds(duration), _ -> { message.set(""); state.set(nextState); if (nextTurn) nextTurn(); })
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
		showMessage(charactersInBattle.get(playingIndex).getName() + " se prépare à parer", 2, BattleState.FIRSTCHOICE, true);
	}
	
	public void firstChoicePersona() {
		state.set(BattleState.PERSONASPELLSELECTION);
	}
	
	public void spellPersonaClicked(Spell spell) {
		if (spell.getAPCost() > charactersInBattle.get(playingIndex).getCurrentAP()) {
			showMessage("AP insuffisants !", 2, BattleState.PERSONASPELLSELECTION, false);
		} else {
			spellSelected = spell;
			state.set(BattleState.PERSONAATTACKSELECTION);
		}
	}
	
	public void characterClicked(int index) {
		Character playingCharacter = charactersInBattle.get(playingIndex);
		switch (state.get()) {
			case ATTACKSELECTION:
				if (isAlly(index))
					return;
				switch (playingCharacter.getAttackType()) {
					case PHYSICAL:
						Spell.PHYSIQUE.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					case GUN:
						Spell.PISTOLET.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						break;
					default:
				}
				showMessage(playingCharacter.getName() + " récupère des PA", 1.5, BattleState.FIRSTCHOICE, true);
				break;
			case PERSONAATTACKSELECTION:
				if ((spellSelected == Spell.RECARM || spellSelected == Spell.SAMARECARM) && charactersInBattle.get(index).isAlive() && isAlly(index)) {
					showMessage("Impossible de lancer ce sort sur un allié en vie !", 2, state.get(), false);
					break;
				}
				if (spellSelected != Spell.RECARM && spellSelected != Spell.SAMARECARM && !charactersInBattle.get(index).isAlive() && spellSelected.targetAllies() && isAlly(index)) {
					showMessage("Impossible de lancer ce sort sur un allié à terre !", 2, state.get(), false);
					break;
				}
				if (spellSelected.isGlobal()) {
					if (spellSelected.targetAllies() && isAlly(index)) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(0), charactersInBattle.get(2), charactersInBattle.get(4))));
						showMessage(playingCharacter.getName() + " lance " + spellSelected.getName(), 2, BattleState.FIRSTCHOICE, true);
					}
					if (!spellSelected.targetAllies() && !isAlly(index)) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(1), charactersInBattle.get(3), charactersInBattle.get(5))));
						showMessage(playingCharacter.getName() + " lance " + spellSelected.getName(), 2, BattleState.FIRSTCHOICE, true);
					}
				} else {
					if ((spellSelected.targetAllies() && isAlly(index)) || (!spellSelected.targetAllies() && !isAlly(index))) {
						spellSelected.spellEffect(playingCharacter, new ArrayList<>(List.of(charactersInBattle.get(index))));
						showMessage(playingCharacter.getName() + " lance " + spellSelected.getName() + " sur " + charactersInBattle.get(index).getName(), 2, BattleState.FIRSTCHOICE, true);
					}
				}
				break;
			default:
				return;
		}
	}
}
