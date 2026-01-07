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
        resistancesMap.put(CharacterElement.ICE, resistances.get(0)); 
        resistancesMap.put(CharacterElement.ELECTRIC, resistances.get(0)); 
        resistancesMap.put(CharacterElement.WIND, resistances.get(0)); 
        resistancesMap.put(CharacterElement.PSY, resistances.get(0)); 
        resistancesMap.put(CharacterElement.NUCLEAR, resistances.get(0)); 
        resistancesMap.put(CharacterElement.DIVINE, resistances.get(0)); 
        resistancesMap.put(CharacterElement.CURSED, resistances.get(0)); 
        resistancesMap.put(CharacterElement.PHYSICAL, resistances.get(0)); 
        resistancesMap.put(CharacterElement.GUN, resistances.get(0)); 
        
    	return resistancesMap;
    }
}
