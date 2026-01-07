package entities.characters;

import java.util.Map;

public class Character {

	private String name;
	private int maxHP;
	private int currentHP;
	private int maxAP;
	private int currentAP;
	private Map<entities.CharacterElement, entities.Resistance> resistances;
	private String imagePath;
	
	
	public Character(String name, int maxHP, int maxAP, Map<entities.CharacterElement, entities.Resistance> resistances, String imagePath) {
		this.name = name;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.maxAP = maxAP;
		this.currentAP = maxAP;
		this.resistances = resistances;
		this.imagePath = imagePath;
	}


	public int getMaxHP() {
		return maxHP;
	}


	public int getCurrentHP() {
		return currentHP;
	}

	
	
}
