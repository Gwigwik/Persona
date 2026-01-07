package entities;

public enum Resistance {
	NEUTRAL(""),
	STRONG("Resiste"),
	WEAK("Faible"),
	NULL("Nul"),
	ABSORB("Abosrbe"),
	RETURN("Renvoie");
	
	private final String displayName;

    Resistance(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
