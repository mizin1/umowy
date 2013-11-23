package pl.waw.mizinski.umowy.pojo;

import pl.waw.mizinski.umowy.model.Pracownik;

public class PracownikImieNazwiskoPOJO {
	
	private String imie;
	private String nazwisko;
	
	public PracownikImieNazwiskoPOJO() {
	}
	
	public PracownikImieNazwiskoPOJO(Pracownik pracownik) {
		imie = pracownik.getPierwszeImie();
		nazwisko = pracownik.getNazwisko();
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
}
