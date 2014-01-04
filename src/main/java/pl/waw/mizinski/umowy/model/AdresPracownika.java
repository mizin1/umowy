package pl.waw.mizinski.umowy.model;

import pl.waw.mizinski.umowy.model.enums.TypAdresu;

public class AdresPracownika {
	
	private AdresPracownikaPK adresPracownikaPK = new AdresPracownikaPK();
	private String miejscowosc;
	private String ulica;
	private String nrDomu;
	private String nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	private Panstwo panstwo; 
	
	public AdresPracownikaPK getAdresPracownikaPK() {
		return adresPracownikaPK;
	}
	
	public void setAdresPracownikaPK(AdresPracownikaPK adresPracownikaPK) {
		this.adresPracownikaPK = adresPracownikaPK;
	}

	public TypAdresu getTypAdresu() {
		return adresPracownikaPK.getTypAdresu();
	}
	
	public void setTypAdresu(TypAdresu typAdresu) {
		adresPracownikaPK.setTypAdresu(typAdresu);
	}
	
	public Pracownik getPracownik() {
		return adresPracownikaPK.getPracownik();
	}

	public void setPracownik(Pracownik pracownik) {
		adresPracownikaPK.setPracownik(pracownik);
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

	public Panstwo getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(Panstwo panstwo) {
		this.panstwo = panstwo;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getPracownik()).append(", ")
			.append(miejscowosc).append(" ");
		if (ulica != null) {
			stringBuilder.append(ulica).append(" ");
		}
		stringBuilder.append(nrDomu);
		if (nrMieszkania != null) {
			stringBuilder.append(" m. ").append(nrMieszkania);
		}
		stringBuilder.append(", ").append(kodPocztowy).append(" ").append(poczta);
		return stringBuilder.toString();
	}
}
