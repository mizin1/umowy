package pl.waw.mizinski.umowy.model;

public class Skladka {
	
	private String nazwa;
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	@Override
	public String toString() {
		return nazwa;
	}
}
