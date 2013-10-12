package pl.waw.mizinski.umowy.model;

public class Adres {
	
	private Long id;
	private Pracownik pracownik;
	private UrzadSkarbowy urzadSkarbowy;
	private String miejscowosc;
	private String ulica;
	private Integer nrDomu;
	private Integer nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	private Panstwo panstwo; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pracownik getPracownik() {
		return pracownik;
	}

	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}

	public UrzadSkarbowy getUrzadSkarbowy() {
		return urzadSkarbowy;
	}

	public void setUrzadSkarbowy(UrzadSkarbowy urzadSkarbowy) {
		this.urzadSkarbowy = urzadSkarbowy;
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

	public Integer getNrDomu() {
		return nrDomu;
	}

	public void setNrDomu(Integer nrDomu) {
		this.nrDomu = nrDomu;
	}

	public Integer getNrMieszkania() {
		return nrMieszkania;
	}

	public void setNrMieszkania(Integer nrMieszkania) {
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

	public Panstwo getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(Panstwo panstwo) {
		this.panstwo = panstwo;
	}

}
