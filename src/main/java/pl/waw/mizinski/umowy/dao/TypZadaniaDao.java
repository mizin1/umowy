package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypZadania;
import pl.waw.mizinski.umowy.model.TypZadaniaPK;

public class TypZadaniaDao extends AbstractDao<TypZadaniaPK, TypZadania> {

	public TypZadaniaDao(Context context) {
		super(context);
	}

	public TypZadania getTypZadania(String nazwa, String nazwaJednostkiOrganzacyjnej){
		Query query = session().createQuery("from TypZadania t where t.typZadaniaPK.nazwa=? and t.typZadaniaPK.jednostkaOrganizacyjna.nazwa=?");
		query.setParameter(0, nazwa).setParameter(1, nazwaJednostkiOrganzacyjnej);
		return HibernateUtils.queryUniqueResult(session(), query, TypZadania.class);
	}
	
	public List<TypZadania> getTypyZadaniaByJednostka(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		Query query = session().createQuery("from TypZadania t where t.typZadaniaPK.jednostkaOrganizacyjna=?");
		query.setParameter(0, jednostkaOrganizacyjna);
		return HibernateUtils.queryResult(session(), query);
	}
}
