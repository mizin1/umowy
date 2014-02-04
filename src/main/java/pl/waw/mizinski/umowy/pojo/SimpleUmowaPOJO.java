package pl.waw.mizinski.umowy.pojo;

import java.math.BigDecimal;


public class SimpleUmowaPOJO {
	
	private String nrUmowy;
	private String typUmowy;
	private String pracownikNazwisko;
	private String pracownikPierwszeImie;
	private String pracownikImionaPozostale;
	private String jednostkaOrganizacyjna;
	private String zadanie;
	private BigDecimal wynagrodzenie;
	private long liczbaRachunkow;

	public SimpleUmowaPOJO(String nrUmowy, String typUmowy,
			String pracownikNazwisko, String pracownikPierwszeImie,
			String pracownikImionaPozostale, String jednostkaOrganizacyjna,
			String zadanie, BigDecimal wynagrodzenie, long liczbaRachunkow) {
		super();
		this.nrUmowy = nrUmowy;
		this.typUmowy = typUmowy;
		this.pracownikNazwisko = pracownikNazwisko;
		this.pracownikPierwszeImie = pracownikPierwszeImie;
		this.pracownikImionaPozostale = pracownikImionaPozostale;
		this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
		this.zadanie = zadanie;
		this.wynagrodzenie = wynagrodzenie;
		this.liczbaRachunkow = liczbaRachunkow;
	}

	public String getNrUmowy() {
		return nrUmowy;
	}

	public String getTypUmowy() {
		return typUmowy;
	}

	public String getPracownikNazwisko() {
		return pracownikNazwisko;
	}

	public String getPracownikPierwszeImie() {
		return pracownikPierwszeImie;
	}

	public String getPracownikImionaPozostale() {
		return pracownikImionaPozostale;
	}

	public String getJednostkaOrganizacyjna() {
		return jednostkaOrganizacyjna;
	}

	public String getZadanie() {
		return zadanie;
	}

	public BigDecimal getWynagrodzenie() {
		return wynagrodzenie;
	}
	
	public Long getLiczbaRachunkow() {
		return liczbaRachunkow;
	}
	
}
