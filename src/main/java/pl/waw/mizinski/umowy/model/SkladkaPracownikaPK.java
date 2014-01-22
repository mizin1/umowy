package pl.waw.mizinski.umowy.model;

import java.io.Serializable;

public class SkladkaPracownikaPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private StatusPracownika statusPracownika;
	private TypUmowy typUmowy;
	private Skladka skladka;

	public StatusPracownika getStatusPracownika() {
		return statusPracownika;
	}
	
	public void setStatusPracownika(StatusPracownika statusPracownika) {
		this.statusPracownika = statusPracownika;
	}

	public TypUmowy getTypUmowy() {
		return typUmowy;
	}

	public void setTypUmowy(TypUmowy typUmowy) {
		this.typUmowy = typUmowy;
	}

	public Skladka getSkladka() {
		return skladka;
	}

	public void setSkladka(Skladka skladka) {
		this.skladka = skladka;
	}
	
}
