package pl.waw.mizinski.umowy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.Umowa;

public class UmowaDao extends AbstractDao<String, Umowa> {

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
}

