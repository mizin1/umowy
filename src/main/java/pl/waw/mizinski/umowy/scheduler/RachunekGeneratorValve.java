package pl.waw.mizinski.umowy.scheduler;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.apache.ecs.xhtml.u;
import org.apache.poi.hssf.record.SaveRecalcRecord;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	
	public RachunekGeneratorValve(final UmowaDao umowaDao, final RachunekDao rachunekDao) {
		super();
		this.umowaDao = umowaDao;
		this.rachunekDao = rachunekDao;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		List<Umowa> umowy = umowaDao.getAll();
		for (Umowa umowa : umowy) {
			processUmowa(umowa, context);
		}
	}

	private void processUmowa(final Umowa umowa, final Context context) throws ProcessingException {
		if(umowa.getPlatnosc().getOkres() == null) {
			processPlatnoscJednorazowa(umowa, context);
		} else if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.dni){
			processPlatnoscWDniach(umowa, context);
		} else if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.miesiace){
			processPlatnoscWMiesiacach(umowa, context);
		}
	}

	private void processPlatnoscWMiesiacach(final Umowa umowa, final Context context) {
		int liczbaRachunkowDoWygenerowania = getLiczbaRachunkowDoWygenerowania(umowa);
		
	}

	

	private void processPlatnoscWDniach(final Umowa umowa, final Context context) {
		int doWygenerowania = getLiczbaRachunkowDoWygenerowania(umowa);
		int wygenerowano = umowa.getRachunki().size();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 1);
//		calendar.
	}
	
	private int getLiczbaRachunkowDoWygenerowania(Umowa umowa) {
		if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.dni) {
			long czasTrwania = umowa.getDataZakonczenia().getTime() - umowa.getDataRozpoczecia().getTime();
			long okres = umowa.getPlatnosc().getOkres() * DAY;
			return (int) (czasTrwania/okres);
		} else if (umowa.getPlatnosc().getJednostkaOkresu() == JednostkaOkresu.miesiace) {
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(umowa.getDataRozpoczecia());
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(umowa.getDataZakonczenia());
			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
			return diffMonth/umowa.getPlatnosc().getOkres();
		}
		throw new IllegalStateException();
	}
	

	private void processPlatnoscJednorazowa(final Umowa umowa, final Context context) throws ProcessingException {
		if (umowa.getDataZakonczenia().getTime() < now() && umowa.getRachunki().isEmpty()) {
			final Rachunek rachunek = createRachunek(umowa, umowa.getWynagrodzenie());
			saveRachunek(rachunek, context);
		}
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
		RachunekPK pk = new RachunekPK(umowa, umowa.getRachunki().size() + 1);
		rachunek.setRachunekPK(pk);
		rachunek.setKwota(kwota);
		rachunek.setDataWystawienia(new Date());
		return rachunek;
	}
	
	private static long now() {
		return new Date().getTime();
	}
	
}
