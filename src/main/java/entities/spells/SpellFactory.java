package entities.spells;

import java.util.Arrays;
import java.util.List;

public class SpellFactory {

	public static List<Spell> characterSpells() {
		List<Spell> spells;
		spells = Arrays.asList(
				Spell.DEBILITATE,
				Spell.HEATRISER,
				Spell.MABUFUDYNE,
				Spell.AGIDYNE,
				Spell.SAMARECARM);
		return spells;
	}
	
	public static List<Spell> getISpells(int i) {
		List<Spell> spells;
		switch (i) {
			case 0:
				spells = Arrays.asList(
						Spell.DEBILITATE,
						Spell.HEATRISER,
						Spell.MABUFUDYNE,
						Spell.AGIDYNE,
						Spell.SAMARECARM);
				break;
			case 1:
				spells = Arrays.asList();
				break;
			case 2:
				spells = Arrays.asList();
				break;
			case 3:
				spells = Arrays.asList();
				break;
			case 4:
				spells = Arrays.asList();
				break;
			case 5:
				spells = Arrays.asList();
				break;
			case 6:
				spells = Arrays.asList();
				break;
			case 7:
				spells = Arrays.asList();
				break;
			case 8:
				spells = Arrays.asList();
				break;
			case 9:
				spells = Arrays.asList();
				break;
			default:
				spells = Arrays.asList();
		}
		return spells;
	}
}
