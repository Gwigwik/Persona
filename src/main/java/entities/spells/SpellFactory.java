package entities.spells;

import java.util.Arrays;
import java.util.List;

public class SpellFactory {

	public static List<Spell> characterSpells() {
		return Arrays.asList(
				Spell.DEBILITATE,
				Spell.HEATRISER,
				Spell.MABUFUDYNE,
				Spell.AGIDYNE,
				Spell.SAMARECARM);
	}
	
	public static List<Spell> emptySpellKit() {
		return Arrays.asList();
	}
	
	public static List<Spell> getMattSpellKit() {
		return Arrays.asList(
				Spell.EIGAON,
				Spell.MAEIGAON);
	}
	
	public static List<Spell> getSophieSpellKit() {
		return Arrays.asList(
				Spell.DIARAMA,
				Spell.TARUKAJA,
				Spell.RECARM);
	}

	public static List<Spell> getManonSpellKit() {
		return Arrays.asList(
				Spell.DIARAMA,
				Spell.RAKUKAJA,
				Spell.RECARM);
	}

	public static List<Spell> getFloSpellKit() {
		return Arrays.asList(
				Spell.PSIODYNE,
				Spell.MAPSIODYNE,
				Spell.MASUKUKAJA);
	}

	public static List<Spell> getHugoSpellKit() {
		return Arrays.asList(
				Spell.FREIDYNE,
				Spell.MAFREIDYNE,
				Spell.MASUKUNDA);
	}
	
	public static List<Spell> getISpellKit(int i) {
		switch (i) {
			case 0:
				return Arrays.asList(
						Spell.AGIDYNE,
						Spell.MARAGIDYNE,
						Spell.GARUDYNE,
						Spell.MATARUKAJA,
						Spell.RAKUNDA);
			case 1:
				return Arrays.asList(
						Spell.BUFUDYNE,
						Spell.MABUFUDYNE,
						Spell.ZIODYNE,
						Spell.MATARUNDA,
						Spell.SUKUNDA);
			case 2:
				return Arrays.asList(
						Spell.PSIODYNE,
						Spell.MAPSIODYNE,
						Spell.FREIDYNE,
						Spell.MARAKUNDA,
						Spell.MASUKUKAJA);
			case 3:
				return Arrays.asList(
						Spell.KOUGAON,
						Spell.MAKOUGAON,
						Spell.EIGAON,
						Spell.DIARAMA,
						Spell.TARUNDA);
			case 4:
				return Arrays.asList(
						Spell.GIDOLAON,
						Spell.AGIDYNE,
						Spell.REBELLION,
						Spell.RAKUKAJA,
						Spell.SUKUNDA);
			case 5:
				return Arrays.asList(
						Spell.GIDOLAON,
						Spell.MEGIDOLAON,
						Spell.AGIDYNE,
						Spell.HEATRISER,
						Spell.DEBILITATE);
			case 6:
				return Arrays.asList(
						Spell.BUFUDYNE,
						Spell.MAGARUDYNE,
						Spell.MEDIARAMA,
						Spell.MARAKUKAJA,
						Spell.RAKUNDA);
			case 7:
				return Arrays.asList(
						Spell.ZIODYNE,
						Spell.MAZIODYNE,
						Spell.MAFREIDYNE,
						Spell.TARUKAJA,
						Spell.MASUKUNDA);
			case 8:
				return Arrays.asList(
						Spell.EIGAON,
						Spell.MAEIGAON,
						Spell.PSIODYNE,
						Spell.MATARUKAJA,
						Spell.RECARM);
			case 9:
				return Arrays.asList(
						Spell.KOUGAON,
						Spell.MAKOUGAON,
						Spell.MEGIDOLAON,
						Spell.MEDIARAMA,
						Spell.DEBILITATE);
			default:
				return Arrays.asList(
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM,
						Spell.SAMARECARM);
		}
	}
}
