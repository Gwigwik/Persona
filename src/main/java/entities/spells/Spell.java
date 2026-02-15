package entities.spells;

import java.util.List;
import entities.Character;
import entities.resistances.Resistance;
import entities.stats.Stat;
import game.BattleManager;
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
	MARAGIDYNE("Maragidyne", SpellElement.FIRE, 25, "Dégats de feu à tous les ennemis", false, true),
	MABUFUDYNE("Mabufudyne", SpellElement.ICE, 25, "Dégats de glace à tous les ennemis", false, true),
	MAZIODYNE("Maziodyne", SpellElement.ELECTRIC, 25, "Dégats électriques à tous les ennemis", false, true),
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
	SAMARECARM("Samarecarm", SpellElement.HEAL, 20, "Réanime un allié avec toute sa vie", true, true),
	//Buffs/Debuffs
	TARUKAJA("Tarukaha", SpellElement.STAT, 8, "Augmente l'attaque d'un allié pour 3 tours", true, false),
	MATARUKAJA("Matarukaja", SpellElement.STAT, 24, "Augmente l'attaque de tous les alliés pour 3 tours", true, true),
	TARUNDA("Tarunda", SpellElement.STAT, 8, "Baisse l'attaque d'un ennemi pour 3 tours", false, false),
	MATARUNDA("Matarunda", SpellElement.STAT, 24, "Baisse l'attaque de tous les ennemis pour 3 tours", false, true),
	RAKUKAJA("Rakukaja", SpellElement.STAT, 8, "Augmente la défense d'un allié pour 3 tours", true, false),
	MARAKUKAJA("Marakukaja", SpellElement.STAT, 24, "Augmente la défense de tous les alliés pour 3 tours", true, true),
	RAKUNDA("Rakunda", SpellElement.STAT, 8, "Baisse la défense d'un ennemi pour 3 tours", false, false),
	MARAKUNDA("Marakunda", SpellElement.STAT, 24, "Baisse la défense de tous les ennemis pour 3 tours", false , true),
	SUKUKAJA("Sukukaja", SpellElement.STAT, 8, "Augmente l'agilité d'un allié pour 3 tours", true, false),
	MASUKUKAJA("Masukukaja", SpellElement.STAT, 24, "Augmente l'agilité de tous les alliés pour 3 tours", true, true),
	SUKUNDA("Sukunda", SpellElement.STAT, 8, "Baisse l'agilité d'un ennemi pour 3 tours", false, false),
	MASUKUNDA("Masukunda", SpellElement.STAT, 24, "Baisse l'agilité de tous les ennemis pour 3 tours", false, true),
	REBELLION("Rebellion", SpellElement.STAT, 8, "Augmente les chances de critique d'un allié pour 3 tours", true, false),
	REVOLUTION("Revolution", SpellElement.STAT, 24, "Augmente les chances de critique de tous les alliés pour 3 tours", true, true),
	HEATRISER("Heat Riser", SpellElement.STAT, 30, "Augmente l'attaque, la défense et l'agilité d'un allié pour 3 tours", true, false),
	DEBILITATE("Debilitate", SpellElement.STAT, 30, "Baisse l'attaque, la défense et l'agilité d'un ennemi pour 3 tours", false, false),
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
				case PHYSIQUE, PISTOLET:
					sender.setCurrentAP(sender.getCurrentAP() + sender.getMaxAP()/20);
				case AGIDYNE, BUFUDYNE, ZIODYNE, GARUDYNE, PSIODYNE, FREIDYNE, KOUGAON, EIGAON,
				MARAGIDYNE, MABUFUDYNE, MAZIODYNE, MAGARUDYNE, MAPSIODYNE, MAFREIDYNE, MAKOUGAON, MAEIGAON:
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
					receiver.setCurrentHP(receiver.getCurrentHP() + receiver.getMaxHP()/2);
					break;
				case DIARAHAN, MEDIARAHAN:
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
				case TARUKAJA, MATARUKAJA:
					receiver.upgradeStat(Stat.ATTACK);
					receiver.setRemainingTurnsStat(Stat.ATTACK, 4);
					break;
				case TARUNDA, MATARUNDA:
					receiver.decreaseStat(Stat.ATTACK);
					receiver.setRemainingTurnsStat(Stat.ATTACK, 4);
					break;
				case RAKUKAJA, MARAKUKAJA:
					receiver.upgradeStat(Stat.DEFENSE);
					receiver.setRemainingTurnsStat(Stat.DEFENSE, 4);
					break;
				case RAKUNDA, MARAKUNDA:
					receiver.decreaseStat(Stat.DEFENSE);
					receiver.setRemainingTurnsStat(Stat.DEFENSE, 4);
					break;
				case SUKUKAJA, MASUKUKAJA:
					receiver.upgradeStat(Stat.AGILITY);
					receiver.setRemainingTurnsStat(Stat.AGILITY, 4);
					break;
				case SUKUNDA, MASUKUNDA:
					receiver.decreaseStat(Stat.AGILITY);
					receiver.setRemainingTurnsStat(Stat.AGILITY, 4);
					break;
				case REBELLION, REVOLUTION:
					receiver.upgradeStat(Stat.CRITICAL);
					receiver.setRemainingTurnsStat(Stat.CRITICAL, 4);
					break;
				case HEATRISER:
					receiver.upgradeStat(Stat.ATTACK);
					receiver.upgradeStat(Stat.DEFENSE);
					receiver.upgradeStat(Stat.AGILITY);
					receiver.setRemainingTurnsStat(Stat.ATTACK, 4);
					receiver.setRemainingTurnsStat(Stat.DEFENSE, 4);
					receiver.setRemainingTurnsStat(Stat.AGILITY, 4);
					break;
				case DEBILITATE:
					receiver.decreaseStat(Stat.ATTACK);
					receiver.decreaseStat(Stat.DEFENSE);
					receiver.decreaseStat(Stat.AGILITY);
					receiver.setRemainingTurnsStat(Stat.ATTACK, 4);
					receiver.setRemainingTurnsStat(Stat.DEFENSE, 4);
					receiver.setRemainingTurnsStat(Stat.AGILITY, 4);
					break;
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
			if (successCrit(sender, receiver)) {
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE))/(receiver.isParrying()?2:1)));
				if (!receiver.isParrying()) {
					if (!receiver.getIsStun())
						BattleManager.setPlayAgain();
					receiver.setIsStun(true);
				}
				showAttackEffect(receiver, Resistance.WEAK);
			} else {				
				receiver.setCurrentHP((int) (receiver.getCurrentHP()-(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE))/(receiver.isParrying()?2:1)));
				showAttackEffect(receiver, Resistance.NEUTRAL);
			}
			receiver.setIsParrying(false);
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void strongDamage(Character sender, Character receiver) {
		if (successHitting(sender, receiver)) {			
			receiver.setCurrentHP((int) (receiver.getCurrentHP()-(12.5*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE))/(receiver.isParrying()?2:1)));
			showAttackEffect(receiver, Resistance.STRONG);
			receiver.setIsParrying(false);
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void weakDamage(Character sender, Character receiver) {
		if (successHitting(sender, receiver)) {
			receiver.setCurrentHP((int) (receiver.getCurrentHP()-(50*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE))/(receiver.isParrying()?2:1)));
			if (!receiver.isParrying()) {
				if (!receiver.getIsStun())
					BattleManager.setPlayAgain();
				receiver.setIsStun(true);
			}
			showAttackEffect(receiver, Resistance.WEAK);
			receiver.setIsParrying(false);
		} else {
			showAttackEffect(receiver, Resistance.UNKNOWN);
		}
	}

	private void nullDamage(Character sender, Character receiver) {
		showAttackEffect(receiver, Resistance.NULL);
	}

	private void absorbDamage(Character sender, Character receiver) {
		receiver.setCurrentHP((int) (receiver.getCurrentHP()+(25*sender.getValueForStat(Stat.ATTACK)/receiver.getValueForStat(Stat.DEFENSE))));
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
		return Math.random() < .95 + sender.getValueForStat(Stat.AGILITY) - receiver.getValueForStat(Stat.AGILITY);
	}
	
	private boolean successCrit(Character sender, Character receiver) {
		return !receiver.isParrying() && Math.random() < sender.getValueForStat(Stat.CRITICAL);
	}
	
}
