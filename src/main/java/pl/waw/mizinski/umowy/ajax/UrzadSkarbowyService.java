package pl.waw.mizinski.umowy.ajax;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.pipeline.ProcessingException;

import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.UrzadSkarbowy;
import pl.waw.mizinski.umowy.util.Utils;

public class UrzadSkarbowyService {

	private final Context context;
	private final UrzadSkarbowyDao urzadSkarbowyDao;

	public UrzadSkarbowyService(final Context context, final UrzadSkarbowyDao urzadSkarbowyDao) {
		this.context = context;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
	}

	public void dodajUrzadSkarbowy(final String nazwa, final String miejscowosc, final String ulica,
			final String nrDomu, final String nrMieszkania, final String kodPocztowy, final String poczta) throws ProcessingException {
		UrzadSkarbowy urzadSkarbowy = new UrzadSkarbowy();
		urzadSkarbowy.setNazwa(Utils.trim(nazwa));
		urzadSkarbowy.setMiejscowosc(Utils.trim(miejscowosc));
		urzadSkarbowy.setUlica(Utils.trim(ulica));
		urzadSkarbowy.setNrDomu(Utils.trim(nrDomu));
		urzadSkarbowy.setNrMieszkania(Utils.trim(nrMieszkania));
		urzadSkarbowy.setKodPocztowy(Utils.trim(kodPocztowy));
		urzadSkarbowy.setPoczta(Utils.trim(poczta));
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			urzadSkarbowyDao.add(urzadSkarbowy);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}
}
