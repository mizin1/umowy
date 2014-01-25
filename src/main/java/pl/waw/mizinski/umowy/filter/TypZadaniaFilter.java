package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.TypZadaniaPOJO;

public class TypZadaniaFilter extends AbstractFilter<TypZadaniaPOJO>{

	public TypZadaniaFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(TypZadaniaPOJO pojo, String expression) {
		return matches(expression, pojo.getNazwa(), pojo.getJednostkaOrganizacyjna());
	}
	
}
