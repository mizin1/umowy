package pl.waw.mizinski.umowy.pojo;

import pl.waw.mizinski.umowy.model.enums.JednostkaOkresu;

public class PlatnoscPOJO {
	
	private String nazwa;
	private Integer okres;
	private JednostkaOkresu jednostkaOkresu;
	private long liczbaUmow;

	public PlatnoscPOJO(String nazwa, Integer okres, JednostkaOkresu jednostkaOkresu, long liczbaUmow) {
		this.nazwa = nazwa;
		this.okres = okres;
		this.jednostkaOkresu = jednostkaOkresu;
		this.liczbaUmow = liczbaUmow;
	}

	public String getNazwa() {
		return nazwa;
	}

	public Integer getOkres() {
		return okres;
	}

	public JednostkaOkresu getJednostkaOkresu() {
		return jednostkaOkresu;
	}

	public long getLiczbaUmow() {
		return liczbaUmow;
	}
	
	
}
