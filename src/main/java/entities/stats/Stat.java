package entities.stats;

public enum Stat {
	ATTACK("ATK", 1, 1.4, .6),
	DEFENSE("DEF", 1, 1.4, .6),
	AGILITY("PREC/ESQ", 0, .3, -.4),
	CRITICAL("CRIT", .05, .25, 0);
	
	private final String name;
	private final double defaultValue;
	private final double upgradedValue;
	private final double decreasedValue;

    Stat(String name, double defaultValue, double upgradedValue, double decreasedValue) {
    	this.name = name;
        this.defaultValue = defaultValue;
        this.upgradedValue = upgradedValue;
        this.decreasedValue = decreasedValue;
    }
    
    public String getName() {
    	return name;
    }
    
    public double getDefaultValue() {
		return defaultValue;
	}

	public double getUpgradedValue() {
		return upgradedValue;
	}
	public double getDecreasedValue() {
		return decreasedValue;
	}
	
	@Override
	public String toString() {
	    return name;
	}
}
