package pl.waw.mizinski.umowy.model;



public class TypZadania {
	
	private TypZadaniaPK typZadaniaPK;

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

	public JednostkaOrganizacyjna getJednostaOrganizacyjna() {
		return typZadaniaPK.getJednostkaOrganizacyjna();
	}

	public void setJednostaOrganizacyjna(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		typZadaniaPK.setJednostkaOrganizacyjna(jednostkaOrganizacyjna);
	}
	
	@Override
	public String toString() {
		return getNazwa() + " dla " + getJednostaOrganizacyjna();
	}
}