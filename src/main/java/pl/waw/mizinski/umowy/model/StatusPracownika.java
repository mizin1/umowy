package pl.waw.mizinski.umowy.model;

import java.util.LinkedList;
import java.util.List;


public class StatusPracownika {
	
	private String nazwa;
	private String opis;
	private List<SkladkaPracownika> skladki;
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<SkladkaPracownika> getSkladki() {
		return skladki;
	}

	public void setSkladki(List<SkladkaPracownika> skladki) {
		this.skladki = skladki;
	}
	
	public List<SkladkaPracownika> getSkladki(Skladka skladka) {
		List<SkladkaPracownika> ret = new LinkedList<>();
		for(SkladkaPracownika skladkaPracownika : skladki) {
			if(skladkaPracownika.getSkladka().equals(skladka)) {
				ret.add(skladkaPracownika);
			}
		}
		return ret;
 	}
	
	@Override
	public String toString() {
		return nazwa + "(odprowadza skladki" + skladki + ")";
	}
	
}
