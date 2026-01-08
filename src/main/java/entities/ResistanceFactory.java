package entities;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResistanceFactory {

    public static Map<CharacterElement, Resistance> characterResistances() {
        Map<CharacterElement, Resistance> map = new EnumMap<>(CharacterElement.class);
        
        map = getResistances(Arrays.asList(
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
        		Resistance.NEUTRAL,
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

    public static Map<CharacterElement, Boolean> initialDiscoveredResistances() {
    	Map<CharacterElement, Boolean> discoveredStatsMap = new EnumMap<>(CharacterElement.class);
    	discoveredStatsMap.put(CharacterElement.FIRE, false);
    	discoveredStatsMap.put(CharacterElement.ICE, false);
    	discoveredStatsMap.put(CharacterElement.ELECTRIC, false);
    	discoveredStatsMap.put(CharacterElement.WIND, false);
    	discoveredStatsMap.put(CharacterElement.PSY, false);
    	discoveredStatsMap.put(CharacterElement.NUCLEAR, false);
    	discoveredStatsMap.put(CharacterElement.DIVINE, false);
    	discoveredStatsMap.put(CharacterElement.CURSED, false);
    	discoveredStatsMap.put(CharacterElement.PHYSICAL, false);
    	discoveredStatsMap.put(CharacterElement.GUN, false);
    	
    	return discoveredStatsMap;
    }
}
