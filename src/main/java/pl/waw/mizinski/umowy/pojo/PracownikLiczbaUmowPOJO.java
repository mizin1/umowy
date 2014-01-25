package pl.waw.mizinski.umowy.pojo;

public class PracownikLiczbaUmowPOJO {
	
	private Long id;
	private String pierwszeImie;
	private String imionaPozostale;
	private String nazwisko;
	private long liczbaUmow;
	
	public PracownikLiczbaUmowPOJO(Long id, String pierwszeImie,
			String imionaPozostale, String nazwisko, long liczbaUmow) {
		super();
		this.id = id;
		this.pierwszeImie = pierwszeImie;
		this.imionaPozostale = imionaPozostale;
		this.nazwisko = nazwisko;
		this.liczbaUmow = liczbaUmow;
	}

	public Long getId() {
		return id;
	}
	
	public String getPierwszeImie() {
		return pierwszeImie;
	}
	
	public String getImionaPozostale() {
		return imionaPozostale;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	
	public long getLiczbaUmow() {
		return liczbaUmow;
	}
}
