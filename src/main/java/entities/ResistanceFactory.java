package entities;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import entities.spells.SpellElement;

public class ResistanceFactory {

//    public static Map<CharacterElement, Resistance> characterResistances() {
//        Map<CharacterElement, Resistance> map = new EnumMap<>(CharacterElement.class);
//        
//        map = getResistances(Arrays.asList(
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL,
//        		Resistance.NEUTRAL)
//        );
//        return map;
//    }
	
	public static Map<SpellElement, Resistance> characterResistances() {
        Map<SpellElement, Resistance> map = new EnumMap<>(SpellElement.class);
        
        map = getResistances(Arrays.asList(
        		Resistance.NEUTRAL,
        		Resistance.ABSORB,
        		Resistance.NULL,
        		Resistance.RETURN,
        		Resistance.STRONG,
        		Resistance.UNKNOWN,
        		Resistance.WEAK,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL)
        );
        return map;
    }
    
    public static Map<SpellElement, Resistance> getResistances(List<Resistance> resistances) {
        Map<SpellElement, Resistance> resistancesMap = new EnumMap<>(SpellElement.class);

        resistancesMap.put(SpellElement.FIRE, resistances.get(0)); 
        resistancesMap.put(SpellElement.ICE, resistances.get(1)); 
        resistancesMap.put(SpellElement.ELECTRIC, resistances.get(2)); 
        resistancesMap.put(SpellElement.WIND, resistances.get(3)); 
        resistancesMap.put(SpellElement.PSY, resistances.get(4)); 
        resistancesMap.put(SpellElement.NUCLEAR, resistances.get(5)); 
        resistancesMap.put(SpellElement.DIVINE, resistances.get(6)); 
        resistancesMap.put(SpellElement.CURSED, resistances.get(7)); 
        resistancesMap.put(SpellElement.PHYSICAL, resistances.get(8)); 
        resistancesMap.put(SpellElement.GUN, resistances.get(9)); 
        
    	return resistancesMap;
    }

    public static Map<SpellElement, Boolean> initialDiscoveredResistancesFalse() {
    	Map<SpellElement, Boolean> discoveredStatsMap = new EnumMap<>(SpellElement.class);
    	discoveredStatsMap.put(SpellElement.PHYSICAL, false);
    	discoveredStatsMap.put(SpellElement.GUN, false);
    	discoveredStatsMap.put(SpellElement.FIRE, false);
    	discoveredStatsMap.put(SpellElement.ICE, false);
    	discoveredStatsMap.put(SpellElement.ELECTRIC, false);
    	discoveredStatsMap.put(SpellElement.WIND, false);
    	discoveredStatsMap.put(SpellElement.PSY, false);
    	discoveredStatsMap.put(SpellElement.NUCLEAR, false);
    	discoveredStatsMap.put(SpellElement.DIVINE, false);
    	discoveredStatsMap.put(SpellElement.CURSED, false);
    	
    	return discoveredStatsMap;
    }

    public static Map<SpellElement, Boolean> initialDiscoveredResistancesTrue() {
    	Map<SpellElement, Boolean> discoveredStatsMap = new EnumMap<>(SpellElement.class);
    	discoveredStatsMap.put(SpellElement.PHYSICAL, true);
    	discoveredStatsMap.put(SpellElement.GUN, true);
    	discoveredStatsMap.put(SpellElement.FIRE, true);
    	discoveredStatsMap.put(SpellElement.ICE, true);
    	discoveredStatsMap.put(SpellElement.ELECTRIC, true);
    	discoveredStatsMap.put(SpellElement.WIND, true);
    	discoveredStatsMap.put(SpellElement.PSY, true);
    	discoveredStatsMap.put(SpellElement.NUCLEAR, true);
    	discoveredStatsMap.put(SpellElement.DIVINE, true);
    	discoveredStatsMap.put(SpellElement.CURSED, true);
    	
    	return discoveredStatsMap;
    }
}
