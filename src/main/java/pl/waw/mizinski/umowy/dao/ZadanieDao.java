package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Zadanie;
import pl.waw.mizinski.umowy.pojo.ZadaniePOJO;

public class ZadanieDao extends AbstractDao<Long, Zadanie>{

	private static final String ZADANIE_POJO = "pl.waw.mizinski.umowy.pojo.ZadaniePOJO" +
			"(z.id, z.nazwa, z.typZadania.typZadaniaPK.nazwa,  z.typZadania.typZadaniaPK.jednostkaOrganizacyjna.nazwa, " +
			"z.opis, z.budzet, z.dataRozpoczecia, z.dataZakonczenia, z.rozliczone, " +
			"count(u.nrUmowy))";
			
	
	public ZadanieDao(Context context) {
		super(context);
	}

	public List<ZadaniePOJO> getAllZadaniePOJOs() {
		Query query = session().createQuery(
				"select new " + ZADANIE_POJO 
				+ "from Umowa u right join u.zadanie z group by z.id");
		return HibernateUtils.queryResult(session(), query);
	}
}
