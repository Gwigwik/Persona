package entities.spells;

import java.util.Arrays;
import java.util.List;

public class SpellFactory {

	public static List<Spell> characterSpells() {
		List<Spell> spells;
		spells = Arrays.asList(
        		Spell.AGIDYNE,
        		Spell.MABUFUDYNE,
        		Spell.RECARM,
        		Spell.SAMARECARM);
		return spells;
	}
}
