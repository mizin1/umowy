package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Rachunek;
import pl.waw.mizinski.umowy.model.RachunekPK;
import pl.waw.mizinski.umowy.model.Umowa;

public class RachunekDao extends AbstractDao<RachunekPK, Rachunek> {

	public RachunekDao(Context context) {
		super(context);
	}

	public List<Rachunek> getRachunekListByUmowa(Umowa umowa) {
		Query query = session().createQuery("from Rachunek r where r.rachunekPK.umowa=?");
		query.setParameter(0, umowa);
		return HibernateUtils.queryResult(session(), query);
	}
}
