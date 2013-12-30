package pl.waw.mizinski.umowy.model;

import java.math.BigDecimal;
import java.util.Date;

public class Umowa {
	
	private String nrUmowy;
	private TypUmowy typUmowy;
	private Pracownik pracownik;
	private Zadanie zadanie;
	private Platnosc platnosc;
	private Date dataZawarcia;
	private Date dataRozpoczecia;
	private Date dataZakonczenia;
	private BigDecimal wynagrodzenie;
	private Boolean wykonywanaUZleceniodawcy;

	public String getNrUmowy() {
		return nrUmowy;
	}

	public void setNrUmowy(String nrUmowy) {
		this.nrUmowy = nrUmowy;
	}

	public TypUmowy getTypUmowy() {
		return typUmowy;
	}

	public void setTypUmowy(TypUmowy typUmowy) {
		this.typUmowy = typUmowy;
	}

	public Pracownik getPracownik() {
		return pracownik;
	}

	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}

	public Zadanie getZadanie() {
		return zadanie;
	}

	public void setZadanie(Zadanie zadanie) {
		this.zadanie = zadanie;
	}

	public Platnosc getPlatnosc() {
		return platnosc;
	}

	public void setPlatnosc(Platnosc platnosc) {
		this.platnosc = platnosc;
	}

	public Date getDataZawarcia() {
		return dataZawarcia;
	}

	public void setDataZawarcia(Date dataZawarcia) {
		this.dataZawarcia = dataZawarcia;
	}

	public Date getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public void setDataRozpoczecia(Date dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}

	public Date getDataZakonczenia() {
		return dataZakonczenia;
	}

	public void setDataZakonczenia(Date dataZakonczenia) {
		this.dataZakonczenia = dataZakonczenia;
	}

	public BigDecimal getWynagrodzenie() {
		return wynagrodzenie;
	}

	public void setWynagrodzenie(BigDecimal wynagrodzenie) {
		this.wynagrodzenie = wynagrodzenie;
	}

	public Boolean getWykonywanaUZleceniodawcy() {
		return wykonywanaUZleceniodawcy;
	}

	public void setWykonywanaUZleceniodawcy(Boolean wykonywanaUZleceniodawcy) {
		this.wykonywanaUZleceniodawcy = wykonywanaUZleceniodawcy;
	}
	
	public JednostkaOrganizacyjna getJednostkaOrganizacyjna() {
		return zadanie.getJednostkaOrganizacyjna();
	}
	
	@Override
	public String toString() {
		return "umowa nr " + nrUmowy;
	}
}
