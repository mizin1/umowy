package pl.waw.mizinski.umowy.model;

import java.io.Serializable;
import java.util.List;

public class JednostkaOrganizacyjna implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nazwa;
	private TypJednostki typJednostki;
	private JednostkaOrganizacyjna jednostkaNadrzedna;
	private Reprezentant reprezentant;
	private String miejscowosc;
	private String ulica;
	private String nrDomu;
	private String nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	private List<TypZadania> typyZadan;
	private List<JednostkaOrganizacyjna> jednostkiPodrzedne;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public TypJednostki getTypJednostki() {
		return typJednostki;
	}

	public void setTypJednostki(TypJednostki typJednostki) {
		this.typJednostki = typJednostki;
	}

	public JednostkaOrganizacyjna getJednostkaNadrzedna() {
		return jednostkaNadrzedna;
	}

	public void setJednostkaNadrzedna(JednostkaOrganizacyjna jednostkaNadrzedna) {
		this.jednostkaNadrzedna = jednostkaNadrzedna;
	}

	public Reprezentant getReprezentant() {
		return reprezentant;
	}

	public void setReprezentant(Reprezentant reprezentant) {
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
	
	public List<TypZadania> getTypyZadan() {
		return typyZadan;
	}
	
	public void setTypyZadan(List<TypZadania> typyZadan) {
		this.typyZadan = typyZadan;
	}
	
	public List<JednostkaOrganizacyjna> getJednostkiPodrzedne() {
		return jednostkiPodrzedne;
	}
	
	public void setJednostkiPodrzedne(
			List<JednostkaOrganizacyjna> jednostkiPodrzedne) {
		this.jednostkiPodrzedne = jednostkiPodrzedne;
	}
	
	@Override
	public String toString() {
		return nazwa;
	}
	
}
