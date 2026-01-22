package entities;

import java.util.EnumMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Character {

	private String name;
	private int maxHP;
	private IntegerProperty currentHP = new SimpleIntegerProperty();
	private BooleanProperty isAlive = new SimpleBooleanProperty();
	private int maxAP;
	private IntegerProperty currentAP = new SimpleIntegerProperty();
	private Map<CharacterElement, Resistance> resistances;
	private Map<CharacterElement, Boolean> discoveredResistances;
	private Map<Stat, Double> stats;
	private Map<Stat, Integer> remainingTurnsStats;
	private BooleanProperty isStun = new SimpleBooleanProperty();
	private BooleanProperty isParrying = new SimpleBooleanProperty();
	private BooleanProperty isPlaying = new SimpleBooleanProperty();
	private String imagePath;
	
	public Character(String name, int maxHP, int maxAP, boolean isAlive, Map<CharacterElement, Resistance> resistances, Map<CharacterElement, Boolean> discoveredResistances, Map<Stat, Double> stats, String imagePath) {
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP.set(maxHP);
		this.isAlive.set(isAlive);
		this.maxAP = maxAP;
		this.currentAP.set(maxAP);;
		this.resistances = resistances;
		this.discoveredResistances = discoveredResistances;
		this.stats = stats;
		this.remainingTurnsStats = new EnumMap<>(Stat.class);
		remainingTurnsStats.put(Stat.ATTACK, 0);
		remainingTurnsStats.put(Stat.DEFENSE, 0);
		remainingTurnsStats.put(Stat.ACCURACY, 0);
		remainingTurnsStats.put(Stat.EVASION, 0);
		remainingTurnsStats.put(Stat.CRITICAL, 0);
		isStun.set(false);
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP.get();
	}

	public void setCurrentHP(IntegerProperty currentHP) {
		this.currentHP = currentHP;
	}

	public IntegerProperty currentHPProperty() {
		return currentHP;
	}

	public int getMaxAP() {
		return maxAP;
	}

	public void setCurrentAP(int currentAP) {
		this.currentHP.set(currentAP);
	}
	
	public int getCurrentAP() {
		return currentAP.get();
	}

	public IntegerProperty currentAPProperty() {
		return currentAP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP.set(currentHP);
	}

	public BooleanProperty isAliveProperty() {
		return isAlive;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive.set(isAlive);
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
	
	public StatStatus getStatStatus(Stat stat) {
		if (stats.get(stat) == stat.getDefaultValue()) {
			return StatStatus.DEFAULT;
		} else if (stats.get(stat) > stat.getDefaultValue()) {
			return StatStatus.UPGRADED;
		} else {
			return StatStatus.DECREASED;
		}
	}
	
	public int getRemainingTurnsStat(Stat stat) {
		return remainingTurnsStats.get(stat);
	}
	
	public void setRemainingTurnsStat(Stat stat, int turns) {
		remainingTurnsStats.put(stat, turns);
	}
	
	public void removeOneTurnFromStats() {
		remainingTurnsStats.replaceAll((_, turns) -> turns>0 ? turns-1 : 0);
		remainingTurnsStats.forEach((stat, turn) -> {
			if (turn == 0)
				stats.put(stat, stat.getDefaultValue());
		});
	}
	
	public Resistance getDiscoveredResistance(CharacterElement element) {
		if (discoveredResistances.get(element)) {
			return resistances.get(element);
		} else {
			return Resistance.UNKNOWN;
		}
	}

	public String getFileName() {
		return imagePath;
	}
	
	
}
