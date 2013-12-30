package pl.waw.mizinski.umowy.assembler;

import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.intake.ZadanieIntake;
import pl.waw.mizinski.umowy.model.TypZadania;
import pl.waw.mizinski.umowy.model.Zadanie;

public class ZadanieAssembler {

	private final TypZadaniaDao typZadaniaDao;

	public ZadanieAssembler(final TypZadaniaDao typZadaniaDao) {
		this.typZadaniaDao = typZadaniaDao;
	}

	public ZadanieIntake asZadanieIntake(Zadanie zadanie) {
		ZadanieIntake zadanieIntake = new ZadanieIntake();
		zadanieIntake.setId(zadanie.getId());
		zadanieIntake.setNazwa(zadanie.getNazwa());
		zadanieIntake.setTypZadania(zadanie.getTypZadania().getNazwa());
		zadanieIntake.setJednostkaOrganizacyjna(zadanie.getJednostkaOrganizacyjna().getNazwa());
		zadanieIntake.setOpis(zadanie.getOpis());
		zadanieIntake.setBudzet(zadanie.getBudzet());
		zadanieIntake.setDataRozpoczecia(zadanie.getDataRozpoczecia());
		zadanieIntake.setDataZakonczenia(zadanie.getDataZakonczenia());
		return zadanieIntake;
	}

	public Zadanie asZadanieEntity(ZadanieIntake zadanieIntake) {
		Zadanie zadanie = new Zadanie();
		zadanie.setId(zadanieIntake.getId());
		zadanie.setNazwa(zadanieIntake.getNazwa());
		TypZadania typZadania = typZadaniaDao.getTypZadania(zadanieIntake.getTypZadania(),
				zadanieIntake.getJednostkaOrganizacyjna());
		zadanie.setTypZadania(typZadania);
		zadanie.setOpis(zadanieIntake.getOpis());
		zadanie.setBudzet(zadanieIntake.getBudzet());
		zadanie.setDataRozpoczecia(zadanieIntake.getDataRozpoczecia());
		zadanie.setDataZakonczenia(zadanieIntake.getDataZakonczenia());
		return zadanie;
	}

}
