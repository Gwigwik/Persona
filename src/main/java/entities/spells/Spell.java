package entities.spells;

import java.util.List;
import entities.Character;
import entities.Stat;

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

	public void spellEffect(Character sender, List<Character> receivers) {
		receivers.forEach((receiver) -> {
		switch (this) {
			case PHYSICALATTACK:
				switch (receiver.getResistanceForElement(SpellElement.PHYSICAL)) {
					case NEUTRAL:
						neutralDamage(sender, receiver);
						break;
					case STRONG:
						break;
					case WEAK:
						break;
					case NULL:
						break;
					case ABSORB:
						break;
					case RETURN:
						break;
					default:
						return;
				}
				break;
			case GUNATTACK:
				break;
			default:
	            System.out.println("Spell not implemented");
		}
		});
	}
	
	private void neutralDamage(Character sender, Character receiver) {
		receiver.setCurrentHP((int) (receiver.getCurrentHP()-25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)));
	}
	
}
