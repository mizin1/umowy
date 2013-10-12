package pl.waw.mizinski.umowy.model;

public class Reprezentant {
	
	private String nazwa;
	private String biernik;
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getBiernik() {
		return biernik;
	}
	
	public void setBiernik(String biernik) {
		this.biernik = biernik;
	}
	
	@Override
	public String toString() {
		return nazwa;
	}
}

