package pl.waw.mizinski.umowy.model;

public class SkladkaPracownika {
	
	private SkladkaPracownikaPK skladkaPracownikaPK;
	private Boolean dobrowolna;
	
	public StatusPracownika getStatusPracownika() {
		return skladkaPracownikaPK.getStatusPracownika();
	}
	
	public void setStatusPracownika(StatusPracownika statusPracownika) {
		skladkaPracownikaPK.setStatusPracownika(statusPracownika);
	}

	public TypUmowy getTypUmowy() {
		return skladkaPracownikaPK.getTypUmowy();
	}

	public void setTypUmowy(TypUmowy typUmowy) {
		skladkaPracownikaPK.setTypUmowy(typUmowy);
	}

	public Skladka getSkladka() {
		return skladkaPracownikaPK.getSkladka();
	}

	public void setSkladka(Skladka skladka) {
		skladkaPracownikaPK.setSkladka(skladka);
	}
	
	public Boolean getDobrowolna() {
		return dobrowolna;
	}
	
	public void setDobrowolna(Boolean dobrowolna) {
		this.dobrowolna = dobrowolna;
	}
	
	public SkladkaPracownikaPK getSkladkaPracownikaPK() {
		return skladkaPracownikaPK;
	}
	
	public void setSkladkaPracownikaPK(SkladkaPracownikaPK skladkaPracownikaPK) {
		this.skladkaPracownikaPK = skladkaPracownikaPK;
	}
}
