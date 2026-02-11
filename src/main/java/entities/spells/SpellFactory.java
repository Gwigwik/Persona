package entities.spells;

import java.util.Arrays;
import java.util.List;

public class SpellFactory {

	public static List<Spell> characterSpells() {
		List<Spell> spells;
		spells = Arrays.asList(
        		Spell.TARUKAJA,
        		Spell.MATARUKAJA,
        		Spell.TARUNDA,
        		Spell.MATARUNDA,
        		Spell.RAKUKAJA);
		return spells;
	}
}
