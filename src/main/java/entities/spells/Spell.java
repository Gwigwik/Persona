package entities.spells;

import java.util.List;
import entities.Character;
import entities.resistances.Resistance;
import entities.stats.Stat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public enum Spell {
	//Attaques sur un ennemi
	PHYSIQUE("Physique", SpellElement.PHYSICAL, 0, "Dégats physiques à un ennemi", false, false),
	PISTOLET("Pistolet", SpellElement.GUN, 0, "Dégats perforants à un ennemi", false, false),
	AGIDYNE("Agidyne", SpellElement.FIRE, 12, "Dégats de feu à un ennemi", false, false),
	BUFUDYNE("Bufudyne", SpellElement.ICE, 12, "Dégats de glace à un ennemi", false, false),
	ZIODYNE("Ziodyne", SpellElement.ELECTRIC, 12, "Dégats électriques à un ennemi", false, false),
	GARUDYNE("Garudyne", SpellElement.WIND, 12, "Dégats de vent à un ennemi", false, false),
	PSIODYNE("Psiodyne", SpellElement.PSY, 12, "Dégats psys à un ennemi", false, false),
	FREIDYNE("Freidyne", SpellElement.NUCLEAR, 12, "Dégats nucléaires à un ennemi", false, false),
	KOUGAON("Kougaon", SpellElement.DIVINE, 12, "Dégats divins à un ennemi", false, false),
	EIGAON("Eigaon", SpellElement.CURSED, 12, "Dégats maudits à un ennemi", false, false),
	GIDOLAON("Gidolaon", SpellElement.TRUEDAMAGE, 15, "Dégats bruts à un ennemi", false, false),
	//Attaques sur plusieurs ennemis
	MAPHYSIQUE("MaPhysique", SpellElement.PHYSICAL, 0, "Dégats physiques à tous les ennemis", false, true),
	MAPISTOLET("MaPistolet", SpellElement.GUN, 0, "Dégats perforants à tous les ennemis", false, true),
	MARAGIDYNE("Maragidyne", SpellElement.FIRE, 25, "Dégats de feu à tous les ennemis", false, true),
	MABUFUDYNE("Mabufudyne", SpellElement.ICE, 25, "Dégats de glace à tous les ennemis", false, true),
	MARIODYNE("Maziodyne", SpellElement.ELECTRIC, 25, "Dégats électriques à tous les ennemis", false, true),
	MAGARUDYNE("Magarudyne", SpellElement.WIND, 25, "Dégats de vent à tous les ennemis", false, true),
	MAPSIODYNE("Mapsiodyne", SpellElement.PSY, 25, "Dégats psys à tous les ennemis", false, true),
	MAFREIDYNE("Mafreidyne", SpellElement.NUCLEAR, 25, "Dégats nucléaires à tous les ennemis", false, true),
	MAKOUGAON("Makougaon", SpellElement.DIVINE, 25, "Dégats divins à tous les ennemis", false, true),
	MAEIGAON("Maeigaon", SpellElement.CURSED, 25, "Dégats maudits à tous les ennemis", false, true),
	MEGIDOLAON("Megidolaon", SpellElement.TRUEDAMAGE, 32, "Dégats bruts à tous les ennemis", false, true),
	//Heal
	DIARAMA("Diarama", SpellElement.HEAL, 6, "Rend la moitié de sa vie à un allié", true, false),
	DIARAHAN("Diarahan", SpellElement.HEAL, 18, "Rend toute sa vie à un allié", true, false),
	MEDIARAMA("Mediarama", SpellElement.HEAL, 12, "Rend la moitié de sa vie à tous les alliés", true, true),
	MEDIARAHAN("Mediarahan", SpellElement.HEAL, 30, "Rend toute sa vie à tous les alliés", true, true),
	RECARM("Recarm", SpellElement.HEAL, 8, "Réanime un allié avec la moitié de sa vie", true, false),
	SAMARECARM("Samarecarm", SpellElement.HEAL, 20, "Réanime un allié avec toute sa vie", true, false)
	//Buffs/Debuffs
	;
	private final String name;
	private final SpellElement element;
	private final int APCost;
	private final String description;
	private boolean targetAllies;
	private boolean global;
	
	Spell(String name, SpellElement element, int APCost, String description, boolean targetAllies, boolean global) {
		this.name = name;
		this.element = element;
		this.APCost = APCost;
		this.description = description;
		this.targetAllies = targetAllies;
		this.global = global;
	}
	
	public String getName() { return name; }
	
	public SpellElement getElement() { return element; }

	public int getAPCost() { return APCost; }
	
	public String getDescription() { return description; }
	
	public boolean isGlobal() { return global; }
	
	public boolean targetAllies() { return targetAllies; }
	
	public void spellEffect(Character sender, List<Character> receivers) {
		receivers.forEach((receiver) -> {
			switch (this) {
				//Attaque
				case PHYSIQUE, PISTOLET, AGIDYNE, BUFUDYNE, ZIODYNE, GARUDYNE, PSIODYNE, FREIDYNE, KOUGAON, EIGAON,
				MARAGIDYNE, MABUFUDYNE, MARIODYNE, MAGARUDYNE, MAPSIODYNE, MAFREIDYNE, MAKOUGAON, MAEIGAON:
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
					}
					receiver.setDiscoveredResistance(this.getElement(), true);
					break;
				case GIDOLAON, MEGIDOLAON:
					neutralDamage(sender, receiver);
					break;
				//Heal
				case DIARAMA, MEDIARAMA:
					if (receiver.isAlive())
						receiver.setCurrentHP(receiver.getCurrentHP() + receiver.getMaxHP()/2);
					break;
				case DIARAHAN, MEDIARAHAN:
					if (receiver.isAlive())
						receiver.setCurrentHP(receiver.getMaxHP());
					break;
				case RECARM:
					if (!receiver.isAlive()) {
						receiver.setIsAlive(true);
						receiver.setCurrentHP(receiver.getMaxHP()/2);
					}
					break;
				case SAMARECARM:
					if (!receiver.isAlive()) {
						receiver.setIsAlive(true);
						receiver.setCurrentHP(receiver.getMaxHP());
					}
					break;
				//Buff/Debuff
				default:
		            System.out.println("Spell not implemented");
			}
		});
		sender.setCurrentAP(sender.getCurrentAP() - this.APCost);
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
