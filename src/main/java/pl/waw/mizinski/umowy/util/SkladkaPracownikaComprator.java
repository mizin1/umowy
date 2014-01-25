package pl.waw.mizinski.umowy.util;

import java.util.Comparator;

import pl.waw.mizinski.umowy.model.SkladkaPracownika;

public class SkladkaPracownikaComprator implements Comparator<SkladkaPracownika>{

	@Override
	public int compare(SkladkaPracownika o1, SkladkaPracownika o2) {
		return o1.getSkladka().getNazwa().compareTo(o2.getSkladka().getNazwa());
	}

}
