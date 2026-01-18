package entities;

public enum StatStatus {
	DEFAULT("default"),
	UPGRADED("upgraded"),
	DECREASED("decreased");
	
	private final String fileName;

	StatStatus(String fileName) {
        this.fileName = fileName;
    }

	public String getFileName() {
		return fileName;
	}
}
