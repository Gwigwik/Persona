package entities.spells;

import java.util.List;
import entities.Character;
import entities.resistances.Resistance;
import entities.stats.Stat;
import game.BattleManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

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

	public void spellEffect(Character sender, List<Character> receivers) {
		receivers.forEach((receiver) -> {
			switch (this) {
				case PHYSICALATTACK, GUNATTACK, FIREATTACK, ICEATTACK, ELECTRICATTACK, WINDATTACK, PSYATTACK, NUCLEARATTACK, DIVINEATTACK, CURSEDATTACK, TRUEDAMAGEATTACK:
					switch (receiver.getResistanceForElement(this.getElement())) {
						case NEUTRAL:
							neutralDamage(sender, receiver);
							break;
						case STRONG:
							strongDamage(sender, receiver);
							break;
						case WEAK:
							weakDamage(sender, receiver);
							break;
						case NULL:
							nullDamage(sender, receiver);
							break;
						case ABSORB:
							absorbDamage(sender, receiver);
							break;
						case RETURN:
							returnDamage(sender, receiver);
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
	
	public void showAttackEffect(Character character, Resistance attackEffect) {
		character.setAttackEffect(attackEffect);
	    Timeline timeline = new Timeline(
	            new KeyFrame(
	                Duration.seconds(1),
	                _ -> character.setAttackEffect(Resistance.UNKNOWN)
	            )
	        );
	    timeline.play();
	}
	
	private void neutralDamage(Character sender, Character receiver) {
		if (successHitting(sender, receiver)) {
			if (successCrit(sender)) {
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
				receiver.setIsStun(true);
				showAttackEffect(receiver, Resistance.WEAK);
			} else {				
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
				showAttackEffect(receiver, Resistance.NEUTRAL);
			}
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void strongDamage(Character sender, Character receiver) {
		if (successHitting(sender, receiver)) {			
			receiver.setCurrentHP((int) (receiver.getCurrentHP()-(12.5*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			showAttackEffect(receiver, Resistance.STRONG);
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void weakDamage(Character sender, Character receiver) {
		if (successHitting(sender, receiver)) {				
			receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
			showAttackEffect(receiver, Resistance.WEAK);
			receiver.setIsStun(true);
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void nullDamage(Character sender, Character receiver) {
		showAttackEffect(receiver, Resistance.NULL);
	}

	private void absorbDamage(Character sender, Character receiver) {
		receiver.setCurrentHP((int) (receiver.getCurrentHP()+(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE)))/(receiver.isParrying()?2:1));
		showAttackEffect(receiver, Resistance.ABSORB);
	}

	private void returnDamage(Character sender, Character receiver) {
		showAttackEffect(receiver, Resistance.RETURN);
		switch (sender.getResistanceForElement(this.getElement())) {
			case NEUTRAL:
				sender.setCurrentHP((int) (sender.getCurrentHP()-25*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				showAttackEffect(sender, Resistance.NEUTRAL);
				break;
			case STRONG:
				sender.setCurrentHP((int) (sender.getCurrentHP()-12.5*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				showAttackEffect(sender, Resistance.STRONG);
				break;
			case WEAK:
				sender.setCurrentHP((int) (sender.getCurrentHP()-50*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				showAttackEffect(sender, Resistance.WEAK);
				break;
			case ABSORB:
				sender.setCurrentHP((int) (sender.getCurrentHP()+25*receiver.getValueForStat(Stat.ATTACK)/sender.getValueForStat(Stat.DEFENSE)));
				showAttackEffect(sender, Resistance.ABSORB);
				break;
			case NULL:
				showAttackEffect(sender, Resistance.NULL);
				break;
			case RETURN:
				showAttackEffect(sender, Resistance.RETURN);
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
