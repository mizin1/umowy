package pl.waw.mizinski.umowy.ajax;

import pl.waw.mizinski.umowy.pojo.PracownikImieNazwiskoPOJO;

public class ExampleService {

	private static int count = 0;
	
	public Integer count() {
		return count++;
	}
	
	public PracownikImieNazwiskoPOJO getPracownik(String imie) {
		PracownikImieNazwiskoPOJO pojo = new PracownikImieNazwiskoPOJO();
		pojo.setImie(imie);
		pojo.setNazwisko(imie + "oswki");
		return pojo;
	}
}