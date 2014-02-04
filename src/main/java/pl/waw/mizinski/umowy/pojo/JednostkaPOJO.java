package pl.waw.mizinski.umowy.pojo;


public class JednostkaPOJO {
	
	private String nazwa;
	private String typJednostki;
	private String jednostkaNadrzedna;
	private String reprezentant;
	private String miejscowosc;
	private String ulica;
	private String nrDomu;
	private String nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	private long liczbaZadan;

	public JednostkaPOJO(String nazwa, String typJednostki,
			String jednostkaNadrzedna, String reprezentant, String miejscowosc,
			String ulica, String nrDomu, String nrMieszkania,
			String kodPocztowy, String poczta, long liczbaZadan) {
		super();
		this.nazwa = nazwa;
		this.typJednostki = typJednostki;
		this.jednostkaNadrzedna = jednostkaNadrzedna;
		this.reprezentant = reprezentant;
		this.miejscowosc = miejscowosc;
		this.ulica = ulica;
		this.nrDomu = nrDomu;
		this.nrMieszkania = nrMieszkania;
		this.kodPocztowy = kodPocztowy;
		this.poczta = poczta;
		this.liczbaZadan = liczbaZadan;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getTypJednostki() {
		return typJednostki;
	}

	public void setTypJednostki(String typJednostki) {
		this.typJednostki = typJednostki;
	}

	public String getJednostkaNadrzedna() {
		return jednostkaNadrzedna;
	}

	public void setJednostkaNadrzedna(String jednostkaNadrzedna) {
		this.jednostkaNadrzedna = jednostkaNadrzedna;
	}

	public String getReprezentant() {
		return reprezentant;
	}

	public void setReprezentant(String reprezentant) {
		this.reprezentant = reprezentant;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNrDomu() {
		return nrDomu;
	}

	public void setNrDomu(String nrDomu) {
		this.nrDomu = nrDomu;
	}

	public String getNrMieszkania() {
		return nrMieszkania;
	}

	public void setNrMieszkania(String nrMieszkania) {
		this.nrMieszkania = nrMieszkania;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getPoczta() {
		return poczta;
	}

	public void setPoczta(String poczta) {
		this.poczta = poczta;
	}

	public long getLiczbaZadan() {
		return liczbaZadan;
	}

	public void setLiczbaZadan(long liczbaZadan) {
		this.liczbaZadan = liczbaZadan;
	}
}
