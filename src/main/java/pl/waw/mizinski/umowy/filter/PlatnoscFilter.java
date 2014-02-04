package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.PlatnoscPOJO;

public class PlatnoscFilter extends AbstractFilter<PlatnoscPOJO>{

	public PlatnoscFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(PlatnoscPOJO platnosc, String expression) {
		return matches(expression, platnosc.getNazwa());
	}
	
}
