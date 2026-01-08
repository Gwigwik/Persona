package entities;

public enum CharacterElement {
	FIRE("Feu", "fire"),
	ICE("Glace", "ice"),
	ELECTRIC("Electrique", "electric"),
	WIND("Vent", "wind"),
	PSY("Psy", "psy"),
	NUCLEAR("Nucleaire", "nuclear"),
	DIVINE("Divin", "divine"),
	CURSED("Maudit", "cursed"),
	PHYSICAL("Physique", "physical"),
	GUN("Percant", "gun");
	
	private final String displayName;
	private final String fileName;

    CharacterElement(String displayName, String fileName) {
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
