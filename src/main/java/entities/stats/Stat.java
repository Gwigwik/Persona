package entities.stats;

public enum Stat {
	ATTACK("Attaque", 1, 1.4, .6),
	DEFENSE("Defense", 1, 1.4, .6),
	ACCURACY("Precision", 0, .3, -.4),
	EVASION("Evasion", 0, .3, -.4),
	CRITICAL("Chances de critique", .05, .25, 0);
	
	private final String displayName;
	private final double defaultValue;
	private final double upgradedValue;
	private final double decreasedValue;

    Stat(String displayName, double defaultValue, double upgradedValue, double decreasedValue) {
        this.displayName = displayName;
        this.defaultValue = defaultValue;
        this.upgradedValue = upgradedValue;
        this.decreasedValue = decreasedValue;
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

	public String getDisplayName() {
        return displayName;
    }
}
