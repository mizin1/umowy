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

import pl.waw.mizinski.umowy.dao.AdresPracownikaDao;
import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.StatusPracownikaDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.model.AdresPracownika;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.StatusPracownika;
import pl.waw.mizinski.umowy.model.UrzadSkarbowy;
import pl.waw.mizinski.umowy.model.enums.Plec;
import pl.waw.mizinski.umowy.model.enums.TypDokumentuTozsamosci;
import pl.waw.mizinski.umowy.util.RequestParametersWrapper;
import pl.waw.mizinski.umowy.util.Utils;

public class AddPracownik implements Valve {

	private final PracownikDao pracownikDao;
	private final PanstwoDao panstwoDao;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	private final StatusPracownikaDao statusPracownikaDao;
	private final AdresPracownikaDao adresDao;
	
	public AddPracownik(final PracownikDao pracownikDao, final PanstwoDao panstwoDao,
			final UrzadSkarbowyDao urzadSkarbowyDao, final StatusPracownikaDao statusPracownikaDao,
			final AdresPracownikaDao adresDao) {
		this.pracownikDao = pracownikDao;
		this.panstwoDao = panstwoDao;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
		this.statusPracownikaDao = statusPracownikaDao;
		this.adresDao = adresDao;
	}

	@Override
	public void process(Context context) throws ProcessingException {
		RequestParameters parameters = RequestParameters.getRequestParameters(context);
		Pracownik pracownik = createPracownik(parameters);
		AdresPracownika adres = createAdres(parameters, pracownik);
		AdresPracownika adresKorespondencyjny = createAdresKorespondencyjny(parameters, pracownik);
		save(pracownik, adres, adresKorespondencyjny, context);
	}
	
	private void save(Pracownik pracownik, AdresPracownika adres, AdresPracownika adresKorespondencyjny, Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			pracownikDao.add(pracownik);
			adresDao.add(adres);
			if(adresKorespondencyjny != null) {
				adresDao.add(adresKorespondencyjny);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
		
	}

	private AdresPracownika createAdresKorespondencyjny(RequestParameters parameters, Pracownik pracownik) {
		boolean adresKorespondencyjny = parameters.getBoolean("adresKorespondencyjny", false);
		if (!adresKorespondencyjny) {
			return null;
		}
		RequestParametersWrapper wrapper = new RequestParametersWrapper(parameters);
		String miejscowosc = wrapper.get("akMiejscowosc");
		String ulica = wrapper.get("akUlica");
		Integer nrDomu = toInteger(parameters.get("akNrDomu"));
		Integer nrMieszkania = toInteger(parameters.get("akNrMieszkania"));
		String kodPocztowy = wrapper.get("akKodPocztowy");
		String poczta = wrapper.get("akPoczta");
		Panstwo panstwo = panstwoDao.getById(wrapper.get("akPanstwo"));
		AdresPracownika adres = new AdresPracownika();
		adres.setPracownik(pracownik);
		adres.setMiejscowosc(miejscowosc);
		adres.setUlica(ulica);
//		adres.setNrDomu(nrDomu);
//		adres.setNrMieszkania(nrMieszkania);
		adres.setKodPocztowy(kodPocztowy);
		adres.setPoczta(poczta);
		adres.setPanstwo(panstwo);
		return adres;
	}

	private AdresPracownika createAdres(RequestParameters parameters, Pracownik pracownik) {
		RequestParametersWrapper wrapper = new RequestParametersWrapper(parameters);
		String miejscowosc = wrapper.get("miejscowosc");
		String ulica = wrapper.get("ulica");
		Integer nrDomu = toInteger(parameters.get("nrDomu"));
		Integer nrMieszkania = toInteger(parameters.get("nrMieszkania"));
		String kodPocztowy = wrapper.get("kodPocztowy");
		String poczta = wrapper.get("poczta");
		Panstwo panstwo = panstwoDao.getById(wrapper.get("panstwo"));
		AdresPracownika adres = new AdresPracownika();
		adres.setPracownik(pracownik);
		adres.setMiejscowosc(miejscowosc);
		adres.setUlica(ulica);
//		adres.setNrDomu(nrDomu);
//		adres.setNrMieszkania(nrMieszkania);
		adres.setKodPocztowy(kodPocztowy);
		adres.setPoczta(poczta);
		adres.setPanstwo(panstwo);
		return adres;
	}

	private Pracownik createPracownik(RequestParameters parameters) {
		RequestParametersWrapper wrapper = new RequestParametersWrapper(parameters);
		String nazwisko = wrapper.get("nazwisko");
		String pierwszeImie = wrapper.get("pierwszeImie");
		String imionaPozostale = wrapper.get("imionaPozostale");
		Plec plec = Plec.valueOf(wrapper.get("plec"));
		String dataUrodzeniaParam = wrapper.get("dataUrodzenia");
		Date dataUrodzenia = Utils.toDate(dataUrodzeniaParam);
		String miejsceUrodzenia = wrapper.get("miejsceUrodzenia");
		Panstwo obywatelstwo = panstwoDao.getById(wrapper.get("obywatelstwo"));
		UrzadSkarbowy urzadSkarbowy = urzadSkarbowyDao.getById(wrapper.get("urzadSkarbowy"));
		BigDecimal pesel = toBigDecimal(wrapper.get("pesel"));
		BigDecimal nip = toBigDecimal(wrapper.get("nip"));
		String nrDokumentuTozsamosci = wrapper.get("nrDokumentuTozsamosci");
		TypDokumentuTozsamosci typDokumentuTozsamosci = TypDokumentuTozsamosci.valueOf(wrapper
				.get("typDokumentuTozsamosci"));
		String nrKonta = parameters.get("nrKonta");
		StatusPracownika status = statusPracownikaDao.getById(wrapper.get("status"));
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
//		pracownik.setPesel(pesel);
//		pracownik.setNip(nip);
		pracownik.setNrDokumentuTozsamosci(nrDokumentuTozsamosci);
		pracownik.setTypDokumentuTozsamosci(typDokumentuTozsamosci);
		pracownik.setNrKonta(nrKonta);
		pracownik.setStatus(status);
		pracownik.setDobrowolneUbezpieczenieChorobowe(dobrowolneUbezpieczenieChorobowe);
		return pracownik;
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
	
	private Integer toInteger(String stringValue) {
		if (stringValue == null || stringValue.isEmpty()) {
			return null;
		}
		try {
			return Integer.valueOf(stringValue);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
