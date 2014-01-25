package pl.waw.mizinski.umowy.pojo;

//nieuzywane(ale pojawia sie jeszcze w tekscie pracy)
public class PracownikImieNazwiskoPOJO {
	
	private Long id;
	private String imie;
	private String nazwisko;

	public PracownikImieNazwiskoPOJO(Long id, String imie, String nazwisko) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public Long getId() {
		return id;
	}
	
	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}
}
