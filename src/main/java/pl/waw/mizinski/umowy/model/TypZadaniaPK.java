package pl.waw.mizinski.umowy.model;

import java.io.Serializable;

public class TypZadaniaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nazwa;
	private JednostkaOrganizacyjna jednostkaOrganizacyjna;
	
	public TypZadaniaPK() {
	}
	
	public TypZadaniaPK(String nazwa, JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		this.nazwa = nazwa;
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public JednostkaOrganizacyjna getJednostkaOrganizacyjna() {
		return jednostkaOrganizacyjna;
	}
	
	public void setJednostkaOrganizacyjna(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
	}

	
}