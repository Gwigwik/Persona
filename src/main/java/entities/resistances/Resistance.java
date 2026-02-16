package entities.resistances;

public enum Resistance {
	UNKNOWN("unknown"),
	NEUTRAL("neutral"),
	STRONG("strong"),
	WEAK("weak"),
	NULL("null"),
	ABSORB("absorb"),
	RETURN("return"),
	NONE("none");
	
	private final String fileName;

    Resistance(String fileName) {
    	this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
