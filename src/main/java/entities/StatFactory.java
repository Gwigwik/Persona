package entities;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StatFactory {

//    public static Map<Stat, Double> CharacterStats() {
//        Map<Stat, Double> map = new EnumMap<>(Stat.class);
//        
//        map = getStats(Arrays.asList(
//        		1.,
//        		1.,
//        		0.98,
//        		0.02,
//        		0.05)
//        );
//        return map;
//    }
	
	public static Map<Stat, Double> CharacterStats() {
        Map<Stat, Double> map = new EnumMap<>(Stat.class);
        
        map = getStats(Arrays.asList(
        		1.1,
        		1.,
        		0.979,
        		0.021,
        		0.051)
        );
        return map;
    }
    
    public static Map<Stat, Double> getStats(List<Double> stats) {
        Map<Stat, Double> statsMap = new EnumMap<>(Stat.class);

        statsMap.put(Stat.ATTACK, stats.get(0)); 
        statsMap.put(Stat.DEFENSE, stats.get(1)); 
        statsMap.put(Stat.ACCURACY, stats.get(2)); 
        statsMap.put(Stat.EVASION, stats.get(3)); 
        statsMap.put(Stat.CRITICAL, stats.get(4)); 
        
    	return statsMap;
    }
}
