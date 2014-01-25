package pl.waw.mizinski.umowy.pojo;

public class TypZadaniaPOJO {
	
	private String nazwa;
	private String jednostkaOrganizacyjna;
	private long liczbaZadan;
	
	public TypZadaniaPOJO(String nazwa, String jednostkaOrganizacyjna,
			long liczbaZadan) {
		super();
		this.nazwa = nazwa;
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
		this.liczbaZadan = liczbaZadan;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getJednostkaOrganizacyjna() {
		return jednostkaOrganizacyjna;
	}

	public long getLiczbaZadan() {
		return liczbaZadan;
	}
	
}
