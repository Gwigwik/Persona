package entities.stats;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StatFactory {

    public static Map<Stat, Double> characterStats() {
        Map<Stat, Double> map = new EnumMap<>(Stat.class);
        
        map = getStats(Arrays.asList(
        		Stat.ATTACK.getDefaultValue(),
        		Stat.DEFENSE.getDefaultValue(),
        		Stat.AGILITY.getDefaultValue(),
        		Stat.CRITICAL.getDefaultValue())
        );
        return map;
    }
    
    public static Map<Stat, Double> getStats(List<Double> stats) {
        Map<Stat, Double> statsMap = new EnumMap<>(Stat.class);

        statsMap.put(Stat.ATTACK, stats.get(0)); 
        statsMap.put(Stat.DEFENSE, stats.get(1)); 
        statsMap.put(Stat.AGILITY, stats.get(2)); 
        statsMap.put(Stat.CRITICAL, stats.get(3)); 
        
    	return statsMap;
    }
}
