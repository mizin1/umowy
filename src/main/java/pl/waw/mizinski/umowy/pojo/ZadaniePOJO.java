package pl.waw.mizinski.umowy.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ZadaniePOJO {
	
	private Long id;
	private String nazwa;
	private String typZadania;
	private String jednostkaOrganizacyjna;
	private String opis;
	private BigDecimal budzet;
	private Date dataRozpoczecia;
	private Date dataZakonczenia;
	private Boolean rozliczone;
	private long liczbaUmow;
	
	public ZadaniePOJO(Long id, String nazwa, String typZadania,
			String jednostkaOrganizacyjna, String opis, BigDecimal budzet,
			Date dataRozpoczecia, Date dataZakonczenia, Boolean rozliczone,
			long liczbaUmow) {
		super();
		this.id = id;
		this.nazwa = nazwa;
		this.typZadania = typZadania;
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
		this.opis = opis;
		this.budzet = budzet;
		this.dataRozpoczecia = dataRozpoczecia;
		this.dataZakonczenia = dataZakonczenia;
		this.rozliczone = rozliczone;
		this.liczbaUmow = liczbaUmow;
	}

	public Long getId() {
		return id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getTypZadania() {
		return typZadania;
	}

	public String getJednostkaOrganizacyjna() {
		return jednostkaOrganizacyjna;
	}

	public String getOpis() {
		return opis;
	}

	public BigDecimal getBudzet() {
		return budzet;
	}

	public Date getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public Date getDataZakonczenia() {
		return dataZakonczenia;
	}

	public Boolean getRozliczone() {
		return rozliczone;
	}

	public long getLiczbaUmow() {
		return liczbaUmow;
	}
}
