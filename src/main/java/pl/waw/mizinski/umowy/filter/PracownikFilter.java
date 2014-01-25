package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.PracownikLiczbaUmowPOJO;

public class PracownikFilter extends AbstractFilter<PracownikLiczbaUmowPOJO>{

	public PracownikFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(PracownikLiczbaUmowPOJO pojo, String expression) {
		return matches(expression, pojo.getPierwszeImie(), pojo.getImionaPozostale(), pojo.getNazwisko());
	}
	
}
