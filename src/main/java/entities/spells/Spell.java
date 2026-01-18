package entities.spells;

import java.util.List;

public enum Spell {
	PHYSICALATTACK("", SpellElement.PHYSICAL),
	GUNATTACK("", SpellElement.PHYSICAL);
	
	private final String name;
	private final SpellElement element;
	
	Spell(String name, SpellElement element) {
		this.name = name;
		this.element = element;
	}
	
	public String getName() { return name; }
	
	public SpellElement getElement() { return element; }

	public void spellEffect(List<Character> receiver) {
		switch (this) {
			case PHYSICALATTACK:
				break;
			case GUNATTACK:
				break;
			default:
	            System.out.println("Spell not implemented");
		}
	}
	
}
