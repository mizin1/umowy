package pl.waw.mizinski.umowy.model.enums;

public enum Plec {
	
	KOBIETA("K"), MEZCZYZNA("M");
	
	private String kod;

	private Plec(String kod) {
		this.kod = kod;
	}
	
}
