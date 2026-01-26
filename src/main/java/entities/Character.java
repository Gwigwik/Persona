package entities;

import java.util.EnumMap;
import java.util.Map;

import entities.resistances.Resistance;
import entities.spells.SpellElement;
import entities.stats.Stat;
import entities.stats.StatStatus;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Character {

	private String name;
	private int maxHP;
	private IntegerProperty currentHP = new SimpleIntegerProperty();
	private BooleanProperty isAlive = new SimpleBooleanProperty();
	private int maxAP;
	private IntegerProperty currentAP = new SimpleIntegerProperty();
	private SpellElement attackType;
	private Map<SpellElement, Resistance> resistances;
	private Map<SpellElement, Boolean> discoveredResistances;
	private Map<Stat, Double> stats;
	private Map<Stat, Integer> remainingTurnsStats;
	private BooleanProperty isStun = new SimpleBooleanProperty();
	private BooleanProperty isParrying = new SimpleBooleanProperty();
	private BooleanProperty isPlaying = new SimpleBooleanProperty();
	private ObjectProperty<Resistance> attackEffect = new SimpleObjectProperty<>(Resistance.UNKNOWN); 
	private String imagePath;
	
	public Character(String name, int maxHP, int maxAP, boolean isAlive, SpellElement attackType, Map<SpellElement, Resistance> resistances, Map<SpellElement, Boolean> discoveredResistances, Map<Stat, Double> stats, String imagePath) {
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP.set(maxHP);
		this.isAlive.set(isAlive);
		this.maxAP = maxAP;
		this.currentAP.set(maxAP);
		this.attackType = attackType;
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

	public void setCurrentHP(int currentHP) {
		if (isAlive.get()) {
			if (currentHP <= 0) {
				this.currentHP.set(0);
				this.setIsAlive(false);
			} else if (currentHP > maxHP) {
				this.currentHP.set(maxHP);
			} else {
				this.currentHP.set(currentHP);
			}
		}
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

	public SpellElement getAttackType() {
		return attackType;
	}
	
	public Resistance getResistanceForElement(SpellElement element) {
		return resistances.get(element);
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
	
	public BooleanProperty getIsStun() {
		return isStun;
	}

	public void setIsStun(boolean isStun) {
		this.isStun.set(isStun);;
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

	public String getFileName() {
		return imagePath;
	}
	
	
}
