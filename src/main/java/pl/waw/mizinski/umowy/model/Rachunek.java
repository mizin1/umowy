package pl.waw.mizinski.umowy.model;

import java.math.BigDecimal;
import java.util.Date;

public class Rachunek {
	
	private RachunekPK rachunekPK;
	private BigDecimal kwota;
	private Date dataWystawienia;

	public Umowa getUmowa() {
		return rachunekPK.getUmowa();
	}

	public void setUmowa(Umowa umowa) {
		rachunekPK.setUmowa(umowa);
	}

	public Integer getNrRachunku() {
		return rachunekPK.getNrRachunku();
	}

	public void setNrRachunku(Integer nrRachunku) {
		rachunekPK.setNrRachunku(nrRachunku);
	}

	public BigDecimal getKwota() {
		return kwota;
	}

	public void setKwota(BigDecimal kwota) {
		this.kwota = kwota;
	}

	public Date getDataWystawienia() {
		return dataWystawienia;
	}

	public void setDataWystawienia(Date dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}
	
	public RachunekPK getRachunekPK() {
		return rachunekPK;
	}
	
	public void setRachunekPK(RachunekPK rachunekPK) {
		this.rachunekPK = rachunekPK;
	}
	
	@Override
	public String toString() {
		return "Rachunek nr " + getNrRachunku() + "do " + getUmowa();
	}
}
