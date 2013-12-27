package pl.waw.mizinski.umowy.ajax;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.pipeline.ProcessingException;

import pl.waw.mizinski.umowy.dao.AdresDao;
import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.model.Adres;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.UrzadSkarbowy;

public class UrzadSkarbowyService {

	private final Context context;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	private final AdresDao adresDao;
	private final PanstwoDao panstwoDao;

	/*
	 * 
	 * private String miejscowosc; private String ulica; private Integer nrDomu;
	 * private Integer nrMieszkania; private String kodPocztowy; private String
	 * poczta; private Panstwo panstwo;
	 */

	public UrzadSkarbowyService(final Context context, final UrzadSkarbowyDao urzadSkarbowyDao,
			final AdresDao adresDao, final PanstwoDao panstwoDao) {
		this.context = context;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
		this.adresDao = adresDao;
		this.panstwoDao = panstwoDao;
	}

	public void dodajUrzadSkarbowy(final String nazwa, final String miejscowosc, final String ulica,
			final String nrDomu, final String nrMieszkania, final String kodPocztowy, final String poczta,
			final String kodPanstwa) throws ProcessingException {
		UrzadSkarbowy urzadSkarbowy = new UrzadSkarbowy();
		urzadSkarbowy.setNazwa(nazwa);
		Adres adres = new Adres();
		adres.setUrzadSkarbowy(urzadSkarbowy);
		adres.setMiejscowosc(miejscowosc);
		adres.setUlica(ulica);
		adres.setNrDomu(nrDomu);
		adres.setNrMieszkania(nrMieszkania);
		adres.setKodPocztowy(kodPocztowy);
		adres.setPoczta(poczta);
		Panstwo panstwo = panstwoDao.getById(kodPanstwa);
		adres.setPanstwo(panstwo);
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			urzadSkarbowyDao.add(urzadSkarbowy);
			adresDao.add(adres);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}
}
