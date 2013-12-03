package pl.waw.mizinski.umowy.modules.actions.pracownicy;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.StatusPracownikaDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.StatusPracownika;
import pl.waw.mizinski.umowy.model.UrzadSkarbowy;
import pl.waw.mizinski.umowy.model.enums.Plec;
import pl.waw.mizinski.umowy.model.enums.TypDokumentuTozsamosci;
import pl.waw.mizinski.umowy.util.Utils;

public class DodajPracownika implements Valve {
	
	private final PracownikDao pracownikDao;
	private final PanstwoDao panstwoDao;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	private final StatusPracownikaDao statusPracownikaDao;

	public DodajPracownika(final PracownikDao pracownikDao, final PanstwoDao panstwoDao,
			final UrzadSkarbowyDao urzadSkarbowyDao, final StatusPracownikaDao statusPracownikaDao) {
		this.pracownikDao = pracownikDao;
		this.panstwoDao = panstwoDao;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
		this.statusPracownikaDao = statusPracownikaDao;
	}

	@Override
	public void process(Context context) throws ProcessingException {
		RequestParameters parameters = RequestParameters.getRequestParameters(context);
		String nazwisko = parameters.get("nazwisko");
		String pierwszeImie = parameters.get("pierwszeImie");
		String imionaPozostale = parameters.get("imionaPozostale");
		Plec plec = Plec.valueOf(parameters.get("plec"));
		String dataUrodzeniaParam = parameters.get("dataUrodzenia");
		Date dataUrodzenia = Utils.toDate(dataUrodzeniaParam);
		String miejsceUrodzenia = parameters.get("miejsceUrodzenia");
		Panstwo obywatelstwo = panstwoDao.getById(parameters.get("obywatelstwo"));
		UrzadSkarbowy urzadSkarbowy = urzadSkarbowyDao.getById(parameters.get("urzadSkarbowy"));
		BigDecimal pesel = toBigDecimal(parameters.get("pesel"));
		BigDecimal nip = toBigDecimal(parameters.get("nip"));
		String nrDokumentuTozsamosci = parameters.get("nrDokumentuTozsamosci");
		TypDokumentuTozsamosci typDokumentuTozsamosci = TypDokumentuTozsamosci.valueOf(parameters
				.get("typDokumentuTozsamosci"));
		String nrKonta = parameters.get("nrKonta");
		StatusPracownika status = statusPracownikaDao.getById(parameters.get("status"));
		Boolean dobrowolneUbezpieczenieChorobowe = parameters.getBoolean("dobrowolneUbezpieczenieChorobowe", false);
		Pracownik pracownik = new Pracownik();
		pracownik.setNazwisko(nazwisko);
		pracownik.setPierwszeImie(pierwszeImie);
		pracownik.setImionaPozostale(imionaPozostale);
		pracownik.setPlec(plec);
		pracownik.setDataUrodzenia(dataUrodzenia);
		pracownik.setMiejsceUrodzenia(miejsceUrodzenia);
		pracownik.setObywatelstwo(obywatelstwo);
		pracownik.setUrzadSkarbowy(urzadSkarbowy);
		pracownik.setPesel(pesel);
		pracownik.setNip(nip);
		pracownik.setNrDokumentuTozsamosci(nrDokumentuTozsamosci);
		pracownik.setTypDokumentuTozsamosci(typDokumentuTozsamosci);
		pracownik.setNrKonta(nrKonta);
		pracownik.setStatus(status);
		pracownik.setDobrowolneUbezpieczenieChorobowe(dobrowolneUbezpieczenieChorobowe);
		dodajPracownika(pracownik, context);
	}

	private void dodajPracownika(Pracownik pracownik, Context context) throws ProcessingException {
		  final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			pracownikDao.add(pracownik);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

	private BigDecimal toBigDecimal(String stringValue) {
		if (stringValue == null || stringValue.isEmpty()) {
			return null;
		}
		try {
			Double doubleValue = Double.valueOf(stringValue);
			return BigDecimal.valueOf(doubleValue);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
