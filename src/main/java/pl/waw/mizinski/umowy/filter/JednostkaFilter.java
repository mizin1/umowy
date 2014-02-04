package pl.waw.mizinski.umowy.filter;

import pl.waw.mizinski.umowy.pojo.JednostkaPOJO;

public class JednostkaFilter extends AbstractFilter<JednostkaPOJO>{

	public JednostkaFilter(String filterString) {
		super(filterString);
	}

	@Override
	protected boolean matches(JednostkaPOJO pojo, String expression) {
		return matches(expression, pojo.getNazwa(), pojo.getJednostkaNadrzedna(), pojo.getReprezentant(), pojo.getTypJednostki());
	}

}
