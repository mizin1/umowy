package pl.waw.mizinski.umowy.model;

import java.io.Serializable;

public class JednostkaOrganizacyjna implements Serializable{
	
	private String nazwa;
	private TypJednostki typJednostki;
	private JednostkaOrganizacyjna jednostkaNadrzedna;
	private Reprezentant reprezentant;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public TypJednostki getTypJednostki() {
		return typJednostki;
	}

	public void setTypJednostki(TypJednostki typJednostki) {
		this.typJednostki = typJednostki;
	}

	public JednostkaOrganizacyjna getJednostkaNadrzedna() {
		return jednostkaNadrzedna;
	}

	public void setJednostkaNadrzedna(JednostkaOrganizacyjna jednostkaNadrzedna) {
		this.jednostkaNadrzedna = jednostkaNadrzedna;
	}

	public Reprezentant getReprezentant() {
		return reprezentant;
	}

	public void setReprezentant(Reprezentant reprezentant) {
		this.reprezentant = reprezentant;
	}

	@Override
	public String toString() {
		return nazwa;
	}
	
}
