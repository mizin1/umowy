package pl.waw.mizinski.umowy.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.SkladkaPracownika;
import pl.waw.mizinski.umowy.model.SkladkaPracownikaPK;
import pl.waw.mizinski.umowy.model.StatusPracownika;
import pl.waw.mizinski.umowy.model.Umowa;

public class SkladkaPracownikaDao extends AbstractDao<SkladkaPracownikaPK, SkladkaPracownika> {

	public SkladkaPracownikaDao(Context context) {
		super(context);
	}

	public List<SkladkaPracownika> getSkladkiByUmowa(Umowa umowa) {
		Query query = session().createQuery("from SkladkaPracownika s where s.skladkaPracownikaPK.typUmowy=? and s.skladkaPracownikaPK.statusPracownika=?");
		query.setParameter(0, umowa.getTypUmowy()).setParameter(1, umowa.getPracownik().getStatus());
		return HibernateUtils.queryResult(session(), query);
	}
	
	public List<SkladkaPracownika> getSkladkiByStatusPracownika(StatusPracownika statusPracownika) {
		Query query = session().createQuery("from SkladkaPracownika s where s.skladkaPracownikaPK.statusPracownika=?");
		query.setParameter(0, statusPracownika);
		return HibernateUtils.queryResult(session(), query);
	}

	
}
