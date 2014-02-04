package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.pojo.JednostkaPOJO;

public class JednostkaOrganizacyjnaDao extends AbstractDao<String, JednostkaOrganizacyjna>{

	private static final String JEDNOSTKA_POJO = "pl.waw.mizinski.umowy.pojo.JednostkaPOJO" +
			"(j.nazwa, j.typJednostki.nazwa, j.jednostkaNadrzedna.nazwa, j.reprezentant.nazwa, j.miejscowosc, j.ulica, " +
			"j.nrDomu, j.nrMieszkania, j.kodPocztowy, j.poczta, count(z))";
	
	public JednostkaOrganizacyjnaDao(Context context) {
		super(context);
	}
	
	public List<JednostkaPOJO> getAllJednostkaPOJOs() {
		Query query = session().createQuery("select new " + JEDNOSTKA_POJO 
				+ "from Zadanie z right join z.typZadania.typZadaniaPK.jednostkaOrganizacyjna j group by j.nazwa");
		return HibernateUtils.queryResult(session(), query);
	}
	
	public List<String> getJednostkiNadrzedne() {
		Query query = session().createQuery("select distinct j.jednostkaNadrzedna.nazwa from JednostkaOrganizacyjna j" );
		return HibernateUtils.queryResult(session(), query);
	}
	
}
