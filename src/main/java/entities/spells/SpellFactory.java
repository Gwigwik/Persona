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
	
	public static List<Spell> emptySpellKit() {
		return Arrays.asList();
	}

	public static List<Spell> getISpellKit(int i) {
		List<Spell> spells;
		switch (i) {
			case 0:
				spells = Arrays.asList(
						Spell.AGIDYNE,
						Spell.MARAGIDYNE,
						Spell.GARUDYNE,
						Spell.MATARUKAJA,
						Spell.RAKUNDA);
				break;
			case 1:
				spells = Arrays.asList(
						Spell.BUFUDYNE,
						Spell.MABUFUDYNE,
						Spell.ZIODYNE,
						Spell.MATARUNDA,
						Spell.SUKUNDA);
				break;
			case 2:
				spells = Arrays.asList(
						Spell.PSIODYNE,
						Spell.MAPSIODYNE,
						Spell.FREIDYNE,
						Spell.MARAKUNDA,
						Spell.MASUKUKAJA);
				break;
			case 3:
				spells = Arrays.asList(
						Spell.KOUGAON,
						Spell.MAKOUGAON,
						Spell.EIGAON,
						Spell.DIARAMA,
						Spell.TARUNDA);
				break;
			case 4:
				spells = Arrays.asList(
						Spell.GIDOLAON,
						Spell.AGIDYNE,
						Spell.REBELLION,
						Spell.RAKUKAJA,
						Spell.SUKUNDA);
				break;
			case 5:
				spells = Arrays.asList(
						Spell.GIDOLAON,
						Spell.MEGIDOLAON,
						Spell.AGIDYNE,
						Spell.HEATRISER,
						Spell.DEBILITATE);
				break;
			case 6:
				spells = Arrays.asList(
						Spell.BUFUDYNE,
						Spell.MAGARUDYNE,
						Spell.MARAKUKAJA,
						Spell.MEDIARAMA,
						Spell.RAKUNDA);
				break;
			case 7:
				spells = Arrays.asList(
						Spell.ZIODYNE,
						Spell.MAZIODYNE,
						Spell.MAFREIDYNE,
						Spell.TARUKAJA,
						Spell.MASUKUNDA);
				break;
			case 8:
				spells = Arrays.asList(
						Spell.EIGAON,
						Spell.MAEIGAON,
						Spell.PSIODYNE,
						Spell.MATARUKAJA,
						Spell.RECARM);
				break;
			case 9:
				spells = Arrays.asList(
						Spell.KOUGAON,
						Spell.MAKOUGAON,
						Spell.MEGIDOLAON,
						Spell.MEDIARAHAN,
						Spell.DEBILITATE);
				break;
			default:
				spells = Arrays.asList(
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM);
		}
		return spells;
	}
}
