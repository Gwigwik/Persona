package entities;

public enum Resistance {
	UNKNOWN("unknown"),
	NEUTRAL("neutral"),
	STRONG("resist"),
	WEAK("weak"),
	NULL("null"),
	ABSORB("absorb"),
	RETURN("return");
	
	private final String fileName;

    Resistance(String fileName) {
    	this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
