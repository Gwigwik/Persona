package entities;

import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Character {

	private static int nextCharacterId;
	
	private int id;
	private String name;
	private int maxHP;
	private IntegerProperty currentHP = new SimpleIntegerProperty();
	private BooleanProperty isAlive = new SimpleBooleanProperty();
	private int maxAP;
	private IntegerProperty currentAP = new SimpleIntegerProperty();
	private boolean isAlly;
	private Map<CharacterElement, Resistance> resistances;
	private Map<CharacterElement, Boolean> discoveredResistances;
	private Map<Stat, Double> stats;
	private boolean isStun;
	private String imagePath;
	
	
	public Character(String name, int maxHP, int maxAP, boolean isAlive, boolean isAlly, Map<CharacterElement, Resistance> resistances, Map<CharacterElement, Boolean> discoveredResistances, Map<Stat, Double> stats, String imagePath) {
		this.id = nextCharacterId;
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP.set(maxHP);
		this.isAlive.set(isAlive);
		this.maxAP = maxAP;
		this.currentAP.set(maxAP);;
		this.isAlly = isAlly;
		this.resistances = resistances;
		this.discoveredResistances = discoveredResistances;
		this.stats = stats;
		isStun = false;
		this.imagePath = imagePath;
		
		nextCharacterId++;
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

	public IntegerProperty currentHPProperty() {
		return currentHP;
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
	
	public StatStatus getStatStatus(Stat stat) {
		if (stats.get(stat) == stat.getDefaultValue()) {
			return StatStatus.DEFAULT;
		} else if (stats.get(stat) > stat.getDefaultValue()) {
			return StatStatus.UPGRADED;
		} else {
			return StatStatus.DECREASED;
		}
	}
	
	public Resistance getDiscoveredResistance(CharacterElement element) {
		if (discoveredResistances.get(element)) {
			return resistances.get(element);
		} else {
			return Resistance.UNKNOWN;
		}
	}
}
