package entities.spells;

import java.util.List;

public class Spell {

	private String name;
	private SpellElement element;
	
	public Spell(String name, SpellElement element) {
		this.name = name;
		this.element = element;
	}
	
	public void spellEffect(Character sender, List<Character> receveir) {
		
	}
	
}
