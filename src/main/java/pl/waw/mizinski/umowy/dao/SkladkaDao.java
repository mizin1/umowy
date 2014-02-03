package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;

import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Skladka;

public class SkladkaDao extends AbstractDao<String, Skladka>{

	public SkladkaDao(Context context) {
		super(context);
	}

	public List<Skladka> getSkladkaOrderedList() {
		Query query = session().createQuery("from Skladka s order by s.nazwa");
		return HibernateUtils.queryResult(session(), query);
	}
}
