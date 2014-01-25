package pl.waw.mizinski.umowy.util;

import java.util.Comparator;

import pl.waw.mizinski.umowy.model.TypJednostki;

public class TypJednostkiComparator implements Comparator<TypJednostki>{

	@Override
	public int compare(TypJednostki o1, TypJednostki o2) {
		return o1.getNazwa().compareTo(o2.getNazwa());
	}

}
