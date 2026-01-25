package entities.spells;

import java.util.List;
import entities.Character;
import entities.stats.Stat;
import game.BattleManager;

public enum Spell {
	PHYSICALATTACK("Physique", SpellElement.PHYSICAL, 0),
	GUNATTACK("Pistolet", SpellElement.GUN, 0),
	FIREATTACK("Feu", SpellElement.FIRE, 20),
	ICEATTACK("Glace", SpellElement.ICE, 20),
	ELECTRICATTACK("Electrique", SpellElement.ELECTRIC, 20),
	WINDATTACK("Vent", SpellElement.WIND, 20),
	PSYATTACK("Psy", SpellElement.PSY, 20),
	NUCLEARATTACK("Nucleaire", SpellElement.NUCLEAR, 20),
	DIVINEATTACK("Divin", SpellElement.DIVINE, 20),
	CURSEDATTACK("Maudit", SpellElement.CURSED, 20),
	TRUEDAMAGEATTACK("Degats bruts", SpellElement.TRUEDAMAGE, 20);
	
	private final String name;
	private final SpellElement element;
	private final int APCost;
	
	Spell(String name, SpellElement element, int APCost) {
		this.name = name;
		this.element = element;
		this.APCost = APCost;
	}
	
	public String getName() { return name; }
	
	public SpellElement getElement() { return element; }

	public void spellEffect(Character sender, List<Character> receivers, BattleManager battleManager) {
		receivers.forEach((receiver) -> {
			switch (this) {
				case PHYSICALATTACK, GUNATTACK, FIREATTACK, ICEATTACK, ELECTRICATTACK, WINDATTACK, PSYATTACK, NUCLEARATTACK, DIVINEATTACK, CURSEDATTACK, TRUEDAMAGEATTACK:
					switch (receiver.getResistanceForElement(this.getElement())) {
						case NEUTRAL:
							neutralDamage(sender, receiver, battleManager);
							break;
						case STRONG:
							strongDamage(sender, receiver, battleManager);
							break;
						case WEAK:
							weakDamage(sender, receiver, battleManager);
							break;
						case NULL:
							nullDamage(sender, receiver, battleManager);
							break;
						case ABSORB:
							absorbDamage(sender, receiver, battleManager);
							break;
						case RETURN:
							returnDamage(sender, receiver, battleManager);
							break;
						default:
							return;
					}
					receiver.setDiscoveredResistance(this.getElement(), true);
					break;
				default:
		            System.out.println("Spell not implemented");
			}
		});
	}
	
	private void neutralDamage(Character sender, Character receiver, BattleManager battleManager) {
		if (successHitting(sender, receiver)) {
			if (successCrit(sender)) {
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " et effectue un coup critique !", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			} else {				
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " !", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			}
		} else {
			battleManager.showMessage(receiver.getName() + " esquive !", 3);
		}
	}

	private void strongDamage(Character sender, Character receiver, BattleManager battleManager) {
		if (successHitting(sender, receiver)) {
			if (successCrit(sender)) {
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " et effectue un coup critique ! ", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			} else {				
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " !", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(12.5*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			}
			receiver.setIsStun(true);
		} else {
			battleManager.showMessage(receiver.getName() + " esquive !", 3);
		}
	}

	private void weakDamage(Character sender, Character receiver, BattleManager battleManager) {
		if (successHitting(sender, receiver)) {
			if (successCrit(sender)) {
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " et effectue un coup critique !" + receiver.getName() + " est etourdi !", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(100*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			} else {				
				battleManager.showMessage(sender.getName() + " lance " + this.getName() + " !" + receiver.getName() + " est etourdi !", 3);
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			}
		} else {
			battleManager.showMessage(receiver.getName() + " esquive !", 3);
		}
	}

	private void nullDamage(Character sender, Character receiver, BattleManager battleManager) {
		battleManager.showMessage(sender.getName() + " lance " + this.getName() + " mais rien ne se produit!", 3);
	}

	private void absorbDamage(Character sender, Character receiver, BattleManager battleManager) {
		battleManager.showMessage(sender.getName() + " lance " + this.getName() + " et " + receiver.getName()+ " se soigne !", 3);
		receiver.setCurrentHP((int) (receiver.getCurrentHP()+(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
	}

	private void returnDamage(Character sender, Character receiver, BattleManager battleManager) {
		battleManager.showMessage(sender.getName() + " lance " + this.getName() + " et " + receiver.getName()+ " renvoie l'attaque !", 3);
		switch (sender.getResistanceForElement(this.getElement())) {
			case NEUTRAL:
				sender.setCurrentHP((int) (sender.getCurrentHP()-25*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				break;
			case STRONG:
				sender.setCurrentHP((int) (sender.getCurrentHP()-12.5*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				break;
			case WEAK:
				sender.setCurrentHP((int) (sender.getCurrentHP()-50*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				break;
			case ABSORB:
				sender.setCurrentHP((int) (sender.getCurrentHP()+25*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				break;
			default:
		}
	}

	private boolean successHitting(Character sender, Character receiver) {
		return Math.random() < .95 + sender.getValueForStat(Stat.ACCURACY) + receiver.getValueForStat(Stat.EVASION);
	}
	
	private boolean successCrit(Character sender) {
		return Math.random() < sender.getValueForStat(Stat.CRITICAL);
	}
	
}
