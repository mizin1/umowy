package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;

public class UmowaFilter extends AbstractFilter<SimpleUmowaPOJO>{

	public UmowaFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(SimpleUmowaPOJO pojo, String expression) {
		return matches(expression, pojo.getNrUmowy(), pojo.getPracownikNazwisko(), pojo.getPracownikPierwszeImie(), pojo.getPracownikImionaPozostale(),
				pojo.getZadanie(), pojo.getTypUmowy(), pojo.getJednostkaOrganizacyjna());
	}

}
