package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Adres;
import pl.waw.mizinski.umowy.model.Pracownik;

public class AdresDao extends AbstractDao<Long, Adres>{

	public AdresDao(Context context) {
		super(context);
	}
	
	public List<Adres> getAdresByPracownik(Pracownik pracownik) {
		Query query = session().createQuery("from Adres a where a.pracownik=?");
		query.setParameter(0, pracownik);
		return HibernateUtils.queryResult(session(), query);
	}
}
