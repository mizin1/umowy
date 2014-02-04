package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Platnosc;
import pl.waw.mizinski.umowy.pojo.PlatnoscPOJO;

public class PlatnoscDao extends AbstractDao<String, Platnosc> {
	
	private static final String PLATNOSC_POJO = "pl.waw.mizinski.umowy.pojo.PlatnoscPOJO" +
			"(p.nazwa, p.okres, p.jednostkaOkresu, count(u))";

	public PlatnoscDao(Context context) {
		super(context);
	}

	
	public List<PlatnoscPOJO> getAllPlatnoscPOJOs(){
		Query query = session().createQuery(
				"select new " + PLATNOSC_POJO 
				+ "from Umowa u right join u.platnosc p group by p.nazwa");
		return HibernateUtils.queryResult(session(), query);
	}
}
