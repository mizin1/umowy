package pl.waw.mizinski.umowy.assembler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pl.waw.mizinski.umowy.dao.PlatnoscDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.TypUmowyDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.intake.UmowaIntake;
import pl.waw.mizinski.umowy.model.Platnosc;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.TypUmowy;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.model.Zadanie;

public class UmowaAssembler {

	private final UmowaDao umowaDao;
	private final PracownikDao pracownikDao;
	private final ZadanieDao zadanieDao;
	private final TypUmowyDao typUmowyDao;
	private final PlatnoscDao platnoscDao;

	public UmowaAssembler(final UmowaDao umowaDao, final PracownikDao pracownikDao, final ZadanieDao zadanieDao,
			final TypUmowyDao typUmowyDao, final PlatnoscDao platnoscDao) {
		this.umowaDao = umowaDao;
		this.pracownikDao = pracownikDao;
		this.zadanieDao = zadanieDao;
		this.typUmowyDao = typUmowyDao;
		this.platnoscDao = platnoscDao;
	}

	public Umowa asUmowaEntity(final UmowaIntake umowaIntake) {
		final Umowa umowa = new Umowa();
		if (umowaIntake.getNrUmowy() != null){
			umowa.setNrUmowy(umowaIntake.getNrUmowy());
		} else {
			umowa.setNrUmowy(generateNrUmowy(umowaIntake.getDataZawarcia()));
		}
		final TypUmowy typUmowy = typUmowyDao.getById(umowaIntake.getTypUmowy());
		umowa.setTypUmowy(typUmowy);
		final Pracownik pracownik = pracownikDao.getById(umowaIntake.getPracownik());
		umowa.setPracownik(pracownik);
		final Zadanie zadanie = zadanieDao.getById(umowaIntake.getZadanie());
		umowa.setZadanie(zadanie);
		umowa.setPrzedmiotUmowy(umowaIntake.getPrzedmiotUmowy());
		final Platnosc platnosc = platnoscDao.getById(umowaIntake.getPlatnosc());
		umowa.setPlatnosc(platnosc);
		umowa.setDataZawarcia(umowaIntake.getDataZawarcia());
		umowa.setDataRozpoczecia(umowaIntake.getDataRozpoczecia());
		umowa.setDataZakonczenia(umowaIntake.getDataZakonczenia());
		umowa.setWynagrodzenie(umowaIntake.getWynagrodzenie());
		umowa.setWykonywanaUZleceniodawcy(umowaIntake.getWykonywanaUZleceniodawcy());
		return umowa;
	}
	
	public UmowaIntake asUmowaIntake(final Umowa umowa){
		final UmowaIntake umowaIntake = new UmowaIntake();
		umowaIntake.setNrUmowy(umowa.getNrUmowy());
		umowaIntake.setTypUmowy(umowa.getTypUmowy().getNazwa());
		umowaIntake.setPracownik(umowa.getPracownik().getId());
		umowaIntake.setZadanie(umowa.getZadanie().getId());
		umowaIntake.setPrzedmiotUmowy(umowa.getPrzedmiotUmowy());
		umowaIntake.setPlatnosc(umowa.getPlatnosc().getNazwa());
		umowaIntake.setDataZawarcia(umowa.getDataZawarcia());
		umowaIntake.setDataRozpoczecia(umowa.getDataRozpoczecia());
		umowaIntake.setDataZakonczenia(umowa.getDataZakonczenia());
		umowaIntake.setWynagrodzenie(umowa.getWynagrodzenie());
		umowaIntake.setWykonywanaUZleceniodawcy(umowa.getWykonywanaUZleceniodawcy());
		return umowaIntake;
	}
	
	private String generateNrUmowy(final Date dataZawarcia) {
		final List<Umowa> umowy = umowaDao.getUmowyByDataZawarcia(dataZawarcia);
		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
		final String dateString = dateFormat.format(dataZawarcia);
		int no = umowy.size() + 1;
		do {
			final String nrUmowy = dateString + no;
			if (!containsNrUmowy(umowy, nrUmowy)) {
				return nrUmowy;
			}
			no--;
		} while(no > 0);
		throw new IllegalStateException("Should never happen!");
	}

	private boolean containsNrUmowy(final List<Umowa> umowy, final String nrUmowy) {
		for(final Umowa umowa : umowy) {
			if (umowa.getNrUmowy().equals(nrUmowy)){
				return true;
			}
		}
		return false;
	}
}
