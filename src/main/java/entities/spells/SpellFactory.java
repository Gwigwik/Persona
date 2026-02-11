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
				Spell.AGIDYNE);
		return spells;
	}
}
