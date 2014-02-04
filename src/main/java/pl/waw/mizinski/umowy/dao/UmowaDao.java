package pl.waw.mizinski.umowy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;

public class UmowaDao extends AbstractDao<String, Umowa> {

	public static final String SIMPLE_UMOWA_POJO = "pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO(" +
			"u.nrUmowy, u.typUmowy.nazwa, p.nazwisko, p.pierwszeImie, p.imionaPozostale, " +
			"z.typZadania.typZadaniaPK.jednostkaOrganizacyjna.nazwa, z.nazwa, u.wynagrodzenie, count(r.rachunekPK.nrRachunku))";
	
	public UmowaDao(Context context) {
		super(context);
	}

	public List<Umowa> getUmowyByDataZawarcia(Date dataZawarcia) {
		Query query = session().createQuery("from Umowa a where a.dataZawarcia=?");
		query.setParameter(0, dataZawarcia);
		return HibernateUtils.queryResult(session(), query);
	}
	
	public List<Umowa> getUmowyByPracownik(Pracownik pracownik){
		Query query = session().createQuery("from Umowa u where u.pracownik=?");
		query.setParameter(0, pracownik);
		return HibernateUtils.queryResult(session(), query);
	}
	
	public List<Umowa> getUmowyToGenerateRachunki() {
		Query query = session().createQuery("from Umowa u where u.wygenerowanoRachunki=?");
		query.setBoolean(0, false);
		return HibernateUtils.queryResult(session(), query);
	}
	
	public List<SimpleUmowaPOJO> getAllSimpleUmowaPOJOs() {
		Query query = session().createQuery(
				"select new " + SIMPLE_UMOWA_POJO
						+ "from Rachunek r right join r.rachunekPK.umowa u left join u.pracownik p left join u.zadanie z group by u.nrUmowy, p.id, z.id");
		return HibernateUtils.queryResult(session(), query);
	}

}

