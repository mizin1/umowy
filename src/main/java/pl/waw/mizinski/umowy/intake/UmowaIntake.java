package pl.waw.mizinski.umowy.intake;

import java.math.BigDecimal;
import java.util.Date;

public class UmowaIntake {
	
	private String nrUmowy;
	private String typUmowy;
	private Long pracownik;
	private Long zadanie;
	private String platnosc;
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

	public String getTypUmowy() {
		return typUmowy;
	}

	public void setTypUmowy(String typUmowy) {
		this.typUmowy = typUmowy;
	}

	public Long getPracownik() {
		return pracownik;
	}

	public void setPracownik(Long pracownik) {
		this.pracownik = pracownik;
	}

	public Long getZadanie() {
		return zadanie;
	}

	public void setZadanie(Long zadanie) {
		this.zadanie = zadanie;
	}

	public String getPlatnosc() {
		return platnosc;
	}

	public void setPlatnosc(String platnosc) {
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
	
	
}
