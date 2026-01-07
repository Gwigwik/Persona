package entities.characters;

import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Character {

	private String name;
	private int maxHP;
	private IntegerProperty currentHP = new SimpleIntegerProperty();
	private int maxAP;
	private int currentAP;
	private Map<entities.CharacterElement, entities.Resistance> resistances;
	private String imagePath;
	
	
	public Character(String name, int maxHP, int maxAP, Map<entities.CharacterElement, entities.Resistance> resistances, String imagePath) {
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP.set(maxHP);
		this.maxAP = maxAP;
		this.currentAP = maxAP;
		this.resistances = resistances;
		this.imagePath = imagePath;
	}

	public void takeDamage(int dmg) {
        setCurrentHP(Math.max(0, getCurrentHP() - dmg));
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


	public void setCurrentHP(int currentHP) {
		this.currentHP.set(currentHP);
	}
	
	
	
}
