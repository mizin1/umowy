package pl.waw.mizinski.umowy.model;

import java.io.Serializable;

public class RachunekPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Umowa umowa;
	private Integer nrRachunku;
	
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
	
	
}

