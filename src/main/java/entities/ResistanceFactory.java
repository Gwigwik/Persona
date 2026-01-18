package entities;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
	
	public static Map<CharacterElement, Resistance> characterResistances() {
        Map<CharacterElement, Resistance> map = new EnumMap<>(CharacterElement.class);
        
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
    
    public static Map<CharacterElement, Resistance> getResistances(List<Resistance> resistances) {
        Map<CharacterElement, Resistance> resistancesMap = new EnumMap<>(CharacterElement.class);

        resistancesMap.put(CharacterElement.FIRE, resistances.get(0)); 
        resistancesMap.put(CharacterElement.ICE, resistances.get(1)); 
        resistancesMap.put(CharacterElement.ELECTRIC, resistances.get(2)); 
        resistancesMap.put(CharacterElement.WIND, resistances.get(3)); 
        resistancesMap.put(CharacterElement.PSY, resistances.get(4)); 
        resistancesMap.put(CharacterElement.NUCLEAR, resistances.get(5)); 
        resistancesMap.put(CharacterElement.DIVINE, resistances.get(6)); 
        resistancesMap.put(CharacterElement.CURSED, resistances.get(7)); 
        resistancesMap.put(CharacterElement.PHYSICAL, resistances.get(8)); 
        resistancesMap.put(CharacterElement.GUN, resistances.get(9)); 
        
    	return resistancesMap;
    }

    public static Map<CharacterElement, Boolean> initialDiscoveredResistancesFalse() {
    	Map<CharacterElement, Boolean> discoveredStatsMap = new EnumMap<>(CharacterElement.class);
    	discoveredStatsMap.put(CharacterElement.PHYSICAL, false);
    	discoveredStatsMap.put(CharacterElement.GUN, false);
    	discoveredStatsMap.put(CharacterElement.FIRE, false);
    	discoveredStatsMap.put(CharacterElement.ICE, false);
    	discoveredStatsMap.put(CharacterElement.ELECTRIC, false);
    	discoveredStatsMap.put(CharacterElement.WIND, false);
    	discoveredStatsMap.put(CharacterElement.PSY, false);
    	discoveredStatsMap.put(CharacterElement.NUCLEAR, false);
    	discoveredStatsMap.put(CharacterElement.DIVINE, false);
    	discoveredStatsMap.put(CharacterElement.CURSED, false);
    	
    	return discoveredStatsMap;
    }

    public static Map<CharacterElement, Boolean> initialDiscoveredResistancesTrue() {
    	Map<CharacterElement, Boolean> discoveredStatsMap = new EnumMap<>(CharacterElement.class);
    	discoveredStatsMap.put(CharacterElement.PHYSICAL, true);
    	discoveredStatsMap.put(CharacterElement.GUN, true);
    	discoveredStatsMap.put(CharacterElement.FIRE, true);
    	discoveredStatsMap.put(CharacterElement.ICE, true);
    	discoveredStatsMap.put(CharacterElement.ELECTRIC, true);
    	discoveredStatsMap.put(CharacterElement.WIND, true);
    	discoveredStatsMap.put(CharacterElement.PSY, true);
    	discoveredStatsMap.put(CharacterElement.NUCLEAR, true);
    	discoveredStatsMap.put(CharacterElement.DIVINE, true);
    	discoveredStatsMap.put(CharacterElement.CURSED, true);
    	
    	return discoveredStatsMap;
    }
}
