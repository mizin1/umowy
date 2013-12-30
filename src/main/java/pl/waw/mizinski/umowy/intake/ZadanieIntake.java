package pl.waw.mizinski.umowy.intake;

import java.math.BigDecimal;
import java.util.Date;

public class ZadanieIntake {
	private Long id;
	private String nazwa;
	private String typZadania;
	private String jednostkaOrganizacyjna;
	private String opis;
	private BigDecimal budzet;
	private Date dataRozpoczecia;
	private Date dataZakonczenia;
	private Boolean rozliczone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getTypZadania() {
		return typZadania;
	}

	public void setTypZadania(String typZadania) {
		this.typZadania = typZadania;
	}

	public String getJednostkaOrganizacyjna() {
		return jednostkaOrganizacyjna;
	}

	public void setJednostkaOrganizacyjna(String jednostkaOrganizacyjna) {
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public BigDecimal getBudzet() {
		return budzet;
	}

	public void setBudzet(BigDecimal budzet) {
		this.budzet = budzet;
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

	public Boolean getRozliczone() {
		return rozliczone;
	}

	public void setRozliczone(Boolean rozliczone) {
		this.rozliczone = rozliczone;
	}
}
