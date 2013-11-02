package pl.waw.mizinski.umowy.model.enums;

public enum Plec {
	K("Kobieta"),
	M("Mężczyzna");
	
	private String nazwa;
	
	private Plec(String nazwa){
		this.nazwa = nazwa;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public static Plec fromNazwa(String nazwa) {
		for (Plec plec : Plec.values()) {
			if (plec.getNazwa().equals(nazwa)){
				return plec;
			}
		}
		return null;
	}
}
