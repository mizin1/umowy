package pl.waw.mizinski.umowy.scheduler;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.RachunekDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Rachunek;
import pl.waw.mizinski.umowy.model.RachunekPK;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.model.enums.JednostkaOkresu;

public class RachunekGeneratorValve implements Valve{

	private static final long DAY = 1000 * 60 * 60 * 24; 
	
	private final UmowaDao umowaDao;
	private final RachunekDao rachunekDao;
	private final Logger logger;
	
	public RachunekGeneratorValve(final UmowaDao umowaDao, final RachunekDao rachunekDao,  final Logger logger) {
		super();
		this.umowaDao = umowaDao;
		this.rachunekDao = rachunekDao;
		this.logger = logger;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		List<Umowa> umowy = umowaDao.getUmowyToGenerateRachunki();
		for (Umowa umowa : umowy) {
			processUmowa(umowa, context);
		}
	}

	public void processUmowa(final Umowa umowa, final Context context) throws ProcessingException {
		logger.debug("Processing umowa with nr " + umowa.getNrUmowy());
		for(int i = umowa.getRachunki().size(); i < getLiczbaRachunkowNaDzien(umowa, today()); ++i){
			BigDecimal dividor = BigDecimal.valueOf(getLiczbaRachunkowNaZakonczenieUmowy(umowa));
			BigDecimal kwota = umowa.getWynagrodzenie().divide(dividor);
			final Rachunek rachunek = createRachunek(umowa, kwota);
			saveRachunek(rachunek, context);
		}
		if (getLiczbaRachunkowNaDzien(umowa, today()) == getLiczbaRachunkowNaZakonczenieUmowy(umowa)) {
			markWygenerowanoRachunki(umowa, context);
		}
	}

	private int getLiczbaRachunkowNaZakonczenieUmowy(Umowa umowa) {
		return  getLiczbaRachunkowNaDzien(umowa, null);
	}
	
	private int getLiczbaRachunkowNaDzien(Umowa umowa, Date date) {
		date = koniecOkresu(umowa, date);
		int ret = 0;
		if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.dni) {
			long czasTrwania =  date.getTime() - umowa.getDataRozpoczecia().getTime();
			long okres = umowa.getPlatnosc().getOkres() * DAY;
			ret = (int) (czasTrwania/okres);
		} else if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.miesiace) {
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(umowa.getDataRozpoczecia());
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(date);
			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
			ret = diffMonth/umowa.getPlatnosc().getOkres();
		}
		if(ret == 0) {
			return date.getTime() < umowa.getDataZakonczenia().getTime() ? 0 : 1;
		}
		return ret;
	}
	
	
	private Date koniecOkresu(Umowa umowa, Date date) {
		Date endDate = new Date(umowa.getDataZakonczenia().getTime() + DAY);
		if (date == null) {
			return endDate;
		}
		return date.getTime() < endDate.getTime() ? date : endDate;
	}

	private void saveRachunek(final Rachunek rachunek,  final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			rachunekDao.add(rachunek);
			transaction.commit();
		} catch(HibernateException e){
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

	private Rachunek createRachunek(final Umowa umowa, final BigDecimal kwota) {
		Rachunek rachunek = new Rachunek();
		umowa.getRachunki().add(rachunek);
		RachunekPK pk = new RachunekPK(umowa, umowa.getRachunki().size());
		rachunek.setRachunekPK(pk);
		rachunek.setKwota(kwota);
		rachunek.setDataWystawienia(today());
		return rachunek;
	}
	
	private void markWygenerowanoRachunki(Umowa umowa, Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			umowa.setWygenerowanoRachunki(true);
			umowaDao.save(umowa);
			transaction.commit();
		} catch(HibernateException e){
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
		
	}
	
	private static Date today() {
		return new Date();
	}
	
}
