package model;

public enum DocumentType {
	//@formatter:off
	EMPTY("Empty"),
	BOOK("Book"),
	ARTICLE("Article"),
	LETTER("Letter"),
	REPORT("Report"),
	;
	//@formatter:on
	private String beautifulString;

	private DocumentType(String beautifulString) {
		this.beautifulString = beautifulString;

	}

	public String toBeautifulString() {
		return beautifulString;
	}
}
