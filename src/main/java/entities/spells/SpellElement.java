package entities.spells;

public enum SpellElement {
	FIRE("Feu", "fire"),
	ICE("Glace", "ice"),
	ELECTRIC("Electrique", "electric"),
	WIND("Vent", "wind"),
	PSY("Psy", "psy"),
	NUCLEAR("Nucleaire", "nuclear"),
	DIVINE("Divin", "divine"),
	CURSED("Maudit", "cursed"),
	PHYSICAL("Physique", "physical"),
	GUN("Percant", "gun"),
	HEAL("Soin", "heal"),
	STAT("Stat", "stat");
	
	private final String displayName;
	private final String fileName;

    SpellElement(String displayName, String fileName) {
        this.displayName = displayName;
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

	public String getFileName() {
		return fileName;
	}
    
    
}
