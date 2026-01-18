package entities;

public enum Stat {
	ATTACK("Attaque", 1, 1.4, 0.6),
	DEFENSE("Defense", 1, 1.4, 0.6),
	ACCURACY("Precision", 0.98, 1, 0.75),
	EVASION("Evasion", 0.02, 0.05, 0),
	CRITICAL("Chances de critique", 0.05, 0.2, 0);
	
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
