package pl.waw.mizinski.umowy.model;

import java.util.Date;

public class Rachunek {
	
	private Umowa umowa;
	private Integer nrRachunku;
	private Integer kwota;
	
	private Date dataWystawienia;

	public Umowa getUmowa() {
		return umowa;
	}

	public void setUmowa(Umowa umowa) {
		this.umowa = umowa;
	}

	public Integer getNrRachunku() {
		return nrRachunku;
	}

	public void setNrRachunku(Integer nrRachunku) {
		this.nrRachunku = nrRachunku;
	}

	public Integer getKwota() {
		return kwota;
	}

	public void setKwota(Integer kwota) {
		this.kwota = kwota;
	}

	public Date getDataWystawienia() {
		return dataWystawienia;
	}

	public void setDataWystawienia(Date dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}
}
