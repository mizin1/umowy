package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.ZadaniePOJO;

public class ZadanieFilter extends AbstractFilter<ZadaniePOJO>{

	public ZadanieFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(ZadaniePOJO pojo, String expression) {
		return matches(expression, pojo.getNazwa(), pojo.getTypZadania(), pojo.getJednostkaOrganizacyjna());
	}

}
