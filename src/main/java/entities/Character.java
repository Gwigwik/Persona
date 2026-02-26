package entities;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import entities.resistances.Resistance;
import entities.resistances.ResistanceFactory;
import entities.spells.Spell;
import entities.spells.SpellElement;
import entities.stats.Stat;
import entities.stats.StatStatus;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Character {

	private String name;
	private int maxHP;
	private IntegerProperty currentHP = new SimpleIntegerProperty();
	private BooleanProperty isAlive = new SimpleBooleanProperty();
	private int maxAP;
	private IntegerProperty currentAP = new SimpleIntegerProperty();
	private SpellElement attackType;
	private ListProperty<Spell> spells = new SimpleListProperty<>(FXCollections.observableArrayList());
	private Map<SpellElement, Resistance> resistances;
	private Map<SpellElement, Boolean> discoveredResistances;
	private Map<Stat, Double> stats;
	private Map<Stat, Integer> remainingTurnsStats;
	private BooleanProperty isStun = new SimpleBooleanProperty();
	private BooleanProperty isParrying = new SimpleBooleanProperty();
	private BooleanProperty isPlaying = new SimpleBooleanProperty();
	private ObjectProperty<Resistance> attackEffect = new SimpleObjectProperty<>(Resistance.NONE); 
	private String imagePath;
	
	public Character(String name, int maxHP, int maxAP, boolean isAlive, SpellElement attackType, List<Spell> spells, Map<SpellElement, Resistance> resistances, Map<Stat, Double> stats, String imagePath) {
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP.set(maxHP);
		this.isAlive.set(isAlive);
		this.maxAP = maxAP;
		this.currentAP.set(maxAP);
		this.attackType = attackType;
		this.spells.setAll(spells);
		this.resistances = resistances;
		this.discoveredResistances = ResistanceFactory.initialDiscoveredResistancesTrue();
		this.stats = stats;
		this.remainingTurnsStats = new EnumMap<>(Stat.class);
		remainingTurnsStats.put(Stat.ATTACK, 0);
		remainingTurnsStats.put(Stat.DEFENSE, 0);
		remainingTurnsStats.put(Stat.AGILITY, 0);
		remainingTurnsStats.put(Stat.CRITICAL, 0);
		isStun.set(false);
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}
	
	public void resetCharacter() {
    	currentHP.set(getMaxHP());
    	currentAP.set(getMaxAP());
    	setIsPlaying(false);
    	setIsParrying(false);
    	setIsStun(false);
		setIsAlive(true);
		stats.replaceAll(((stat, _) -> stat.getDefaultValue()));
		remainingTurnsStats.replaceAll((_, _) -> 0);
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP.get();
	}

	public void setCurrentHP(int currentHP) {
		if (isAlive.get()) {
			if (currentHP <= 0) {
				this.currentHP.set(0);
				this.isAlive.set(false);
				stats.replaceAll(((stat, _) -> stat.getDefaultValue()));
				remainingTurnsStats.replaceAll((_, _) -> 0);
				isStun.set(false);
			} else
				this.currentHP.set(Math.min(currentHP, maxHP));
		}
	}
	
	public void res(int hp) {
		this.isAlive.set(true);
    	setIsParrying(false);
    	setIsStun(false);
		setIsAlive(true);
		this.currentHP.set(hp);
	}
	
	public IntegerProperty currentHPProperty() {
		return currentHP;
	}

	public int getMaxAP() {
		return maxAP;
	}

	public int getCurrentAP() {
		return currentAP.get();
	}

	public void setCurrentAP(int currentAP) {
		if (isAlive.get()) {
			this.currentAP.set(Math.min(currentAP, maxAP));
		}
	}
	
	public IntegerProperty currentAPProperty() {
		return currentAP;
	}

	public SpellElement getAttackType() {
		return attackType;
	}
	
	public ObservableList<Spell> getSpells() {
	    return spells.get();
	}

	public ListProperty<Spell> spellsProperty() {
	    return spells;
	}
	
	public void setSpellKit(List<Spell> spells) {
	    this.spells.setAll(spells);
	}
	
	public Resistance getResistanceForElement(SpellElement element) {
		return resistances.get(element);
	}
	
	public boolean isAlive() {
		return isAlive.get();
	}

	public BooleanProperty isAliveProperty() {
		return isAlive;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive.set(isAlive);
	}

	public boolean isParrying() {
		return isParrying.get();
	}
	
	public void setIsParrying(boolean isParrying) {
		this.isParrying.set(isParrying);;
	}

	public BooleanProperty isParryingProperty() {
		return isParrying;
	}

	public void setIsPlaying(boolean isPlaying) {
		this.isPlaying.set(isPlaying);;
	}

	public BooleanProperty isPlayingProperty() {
		return isPlaying;
	}
	
	public ObjectProperty<Resistance> getAttackEffect() {
		return attackEffect;
	}

	public void setAttackEffect(Resistance attackEffect) {
		this.attackEffect.set(attackEffect);
	}

	public double getValueForStat(Stat stat) {
		return stats.get(stat);
	}
	
	public StatStatus getStatStatus(Stat stat) {
		if (stats.get(stat) == stat.getDefaultValue()) {
			return StatStatus.DEFAULT;
		} else if (stats.get(stat) > stat.getDefaultValue()) {
			return StatStatus.UPGRADED;
		} else {
			return StatStatus.DECREASED;
		}
	}
	
	public void upgradeStat(Stat stat) {
		if (stats.get(stat) >= stat.getDefaultValue()) {
			stats.put(stat, stat.getUpgradedValue());
		} else {
			stats.put(stat, stat.getDefaultValue());
		}
	}

	public void decreaseStat(Stat stat) {
		if (stats.get(stat) <= stat.getDefaultValue()) {
			stats.put(stat, stat.getDecreasedValue());
		} else {
			stats.put(stat, stat.getDefaultValue());
		}
	}
	
	public int getRemainingTurnsStat(Stat stat) {
		return remainingTurnsStats.get(stat);
	}
	
	public void setRemainingTurnsStat(Stat stat, int turns) {
		if (stats.get(stat) != stat.getDefaultValue())
			remainingTurnsStats.put(stat, turns);
	}
	
	public List<Stat> removeOneTurnFromStats() {
		List<Stat> statsToDefault = new ArrayList<>();
		remainingTurnsStats.replaceAll((_, turn) -> turn-1);
		remainingTurnsStats.forEach((stat, turn) -> {
			if (turn == 0) {
				stats.put(stat, stat.getDefaultValue());
				statsToDefault.add(stat);
			}
		});
		remainingTurnsStats.replaceAll((_, turns) -> turns>0 ? turns : 0);
		return statsToDefault;
	}
	
	public boolean getIsStun() {
		return isStun.get();
	}
	
	public BooleanProperty isStunProperty() {
		return isStun;
	}

	public void setIsStun(boolean isStun) {
		this.isStun.set(isStun);
	}

	public Resistance getDiscoveredResistance(SpellElement element) {
		if (discoveredResistances.get(element)) {
			return resistances.get(element);
		} else {
			return Resistance.UNKNOWN;
		}
	}
	
	public void setDiscoveredResistance(SpellElement element, boolean discovered) {
		discoveredResistances.put(element, discovered);
	}
	
	public void hideResistances() {
		discoveredResistances = ResistanceFactory.initialDiscoveredResistancesFalse();
	}

	public String getFileName() {
		return imagePath;
	}
	
	
}
