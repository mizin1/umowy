package pl.waw.mizinski.umowy.model;

public class TypZadania {
	
	private String nazwa;
	private JednostkaOrganizacyjna jednostaOrganizacyjna;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public JednostkaOrganizacyjna getJednostaOrganizacyjna() {
		return jednostaOrganizacyjna;
	}

	public void setJednostaOrganizacyjna(JednostkaOrganizacyjna jednostaOrganizacyjna) {
		this.jednostaOrganizacyjna = jednostaOrganizacyjna;
	}

}
