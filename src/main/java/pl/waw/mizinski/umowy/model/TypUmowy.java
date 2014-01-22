package pl.waw.mizinski.umowy.model;

public class TypUmowy {
	
	private String nazwa;
	private String tytul;
	private Integer kosztUzyskaniaPrzychodu;
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public Integer getKosztUzyskaniaPrzychodu() {
		return kosztUzyskaniaPrzychodu;
	}

	public void setKosztUzyskaniaPrzychodu(Integer kosztUzyskaniaPrzychodu) {
		this.kosztUzyskaniaPrzychodu = kosztUzyskaniaPrzychodu;
	}

	@Override
	public String toString() {
		return nazwa;
	}
}
