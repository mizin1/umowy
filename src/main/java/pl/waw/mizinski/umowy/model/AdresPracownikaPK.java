package pl.waw.mizinski.umowy.model;

import java.io.Serializable;

import pl.waw.mizinski.umowy.model.enums.TypAdresu;

public class AdresPracownikaPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pracownik pracownik;
	private TypAdresu typAdresu;

	public AdresPracownikaPK() {

	}
	
	public AdresPracownikaPK(final Pracownik pracownik, final TypAdresu typAdresu) {
		this.pracownik = pracownik;
		this.typAdresu = typAdresu;
	}
	
	public Pracownik getPracownik() {
		return pracownik;
	}

	public void setPracownik(final Pracownik pracownik) {
		this.pracownik = pracownik;
	}

	public TypAdresu getTypAdresu() {
		return typAdresu;
	}

	public void setTypAdresu(final TypAdresu typAdresu) {
		this.typAdresu = typAdresu;
	}

	
}
