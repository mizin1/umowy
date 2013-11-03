package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;

import pl.waw.mizinski.umowy.model.Rachunek;
import pl.waw.mizinski.umowy.model.RachunekPK;
import pl.waw.mizinski.umowy.model.Umowa;

public class RachunekDao extends AbstractDao<RachunekPK, Rachunek> {

	public RachunekDao(Context context) {
		super(context);
	}

	public List<Rachunek> findByUmowa(Umowa umowa) {
		Query query = session().createQuery("from Rachunek r where r.rachunekPK.umowa=?");
		return query.setParameter(0, umowa).list();
	}
}
