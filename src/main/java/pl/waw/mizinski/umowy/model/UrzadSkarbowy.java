package pl.waw.mizinski.umowy.model;

public class UrzadSkarbowy {
	
	private String nazwa;
	private String miejscowosc;
	private String ulica;
	private String nrDomu;
	private String nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
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

	@Override
	public String toString() {
		return nazwa;
	}
}
