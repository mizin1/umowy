package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.PracownikImieNazwiskoPOJO;

public class PracownikFilter extends AbstractFilter<PracownikImieNazwiskoPOJO>{

	public PracownikFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(PracownikImieNazwiskoPOJO pojo, String expression) {
		return matches(pojo.getImie(), expression) || matches(pojo.getNazwisko(), expression);
	}
	
}
