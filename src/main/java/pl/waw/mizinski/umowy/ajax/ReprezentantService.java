package pl.waw.mizinski.umowy.ajax;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.pipeline.ProcessingException;

import pl.waw.mizinski.umowy.dao.ReprezentantDao;
import pl.waw.mizinski.umowy.model.Reprezentant;
import pl.waw.mizinski.umowy.util.Utils;

public class ReprezentantService {

	private final Context context;
	private final ReprezentantDao reprezentantDao;

	public ReprezentantService(final Context context, final ReprezentantDao reprezentantDao) {
		this.context = context;
		this.reprezentantDao = reprezentantDao;
	}
	
	public void addReprezentant(final String nazwa, final String biernik) throws ProcessingException {
		final Reprezentant reprezentant = new Reprezentant();
		reprezentant.setNazwa(Utils.trim(nazwa));
		reprezentant.setBiernik(Utils.trim(biernik));
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			reprezentantDao.add(reprezentant);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}
}
