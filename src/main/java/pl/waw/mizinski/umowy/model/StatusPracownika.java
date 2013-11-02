package pl.waw.mizinski.umowy.model;

import java.util.Set;

public class StatusPracownika {
	
	private String nazwa;
	private String opis;
	private Set<Skladka> skladki;
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Skladka> getSkladki() {
		return skladki;
	}

	public void setSkladki(Set<Skladka> skladki) {
		this.skladki = skladki;
	}

	@Override
	public String toString() {
		return nazwa + "(odprowadza skladki" + skladki + ")";
	}
	
}
