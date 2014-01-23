package pl.waw.mizinski.umowy.model;

import java.util.List;



public class TypZadania {
	
	private TypZadaniaPK typZadaniaPK = new TypZadaniaPK();
	private List<Zadanie> zadania;

	public TypZadaniaPK getTypZadaniaPK() {
		return typZadaniaPK;
	}

	public void setTypZadaniaPK(TypZadaniaPK typZadaniaPK) {
		this.typZadaniaPK = typZadaniaPK;
	}

	public String getNazwa() {
		return typZadaniaPK.getNazwa();
	}
	
	public void setNazwa(String nazwa) {
		typZadaniaPK.setNazwa(nazwa);
	}

	public JednostkaOrganizacyjna getJednostkaOrganizacyjna() {
		return typZadaniaPK.getJednostkaOrganizacyjna();
	}

	public void setJednostkaOrganizacyjna(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		typZadaniaPK.setJednostkaOrganizacyjna(jednostkaOrganizacyjna);
	}
	
	public List<Zadanie> getZadania() {
		return zadania;
	}
	
	public void setZadania(List<Zadanie> zadania) {
		this.zadania = zadania;
	}
	
	@Override
	public String toString() {
		return getNazwa() + " dla " + getJednostkaOrganizacyjna();
	}
}
