package pl.waw.mizinski.umowy.model;

import pl.waw.mizinski.umowy.model.enums.JednostkaOkresu;

public class Platnosc {
	
	private String nazwa;
	private Integer okres;
	private JednostkaOkresu jednostkaOkresu;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public Integer getOkres() {
		return okres;
	}

	public void setOkres(Integer okres) {
		this.okres = okres;
	}

	public JednostkaOkresu getJednostkaOkresu() {
		return jednostkaOkresu;
	}

	public void setJednostkaOkresu(JednostkaOkresu jednostkaOkresu) {
		this.jednostkaOkresu = jednostkaOkresu;
	}

	@Override
	public String toString() {
		return nazwa;
	}
}
