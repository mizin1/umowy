package pl.waw.mizinski.umowy.util;

import java.util.Comparator;

import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;

public class JednostkaOrganizacyjnaComprarator implements Comparator<JednostkaOrganizacyjna>{

	@Override
	public int compare(JednostkaOrganizacyjna o1, JednostkaOrganizacyjna o2) {
		return o1.getNazwa().compareTo(o2.getNazwa());
	}

}
