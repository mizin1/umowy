package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.UrzadSkarbowy;
import pl.waw.mizinski.umowy.pojo.UrzadSkarbowyNazwaPOJO;

public class UrzadSkarbowyDao extends AbstractDao<String, UrzadSkarbowy>{

	public static final String URZAD_SKARBOWY_NAZWA_POJO = "pl.waw.mizinski.umowy.pojo.UrzadSkarbowyNazwaPOJO(u.nazwa)";
	
	public UrzadSkarbowyDao(Context context) {
		super(context);
	}

	public List<UrzadSkarbowyNazwaPOJO> getAllUrzadSkarbowyNazwaPOJOs() {
		Query query = session().createQuery(
				"select new " + URZAD_SKARBOWY_NAZWA_POJO
						+ "from UrzadSkarbowy u order by u.nazwa");
		return HibernateUtils.queryResult(session(), query);
	}
}
