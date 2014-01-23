package pl.waw.mizinski.umowy.assembler;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.intake.TypZadaniaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypZadania;

public class TypZadaniaAssembler {
	
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public TypZadaniaAssembler(final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
	}

	public TypZadaniaIntake asTypZadaniaIntake(TypZadania typZadania) {
		TypZadaniaIntake typZadaniaIntake = new TypZadaniaIntake();
		typZadaniaIntake.setNazwa(typZadania.getNazwa());
		typZadaniaIntake.setJednostkaOrganizacyjna(typZadania.getJednostkaOrganizacyjna().getNazwa());
		return typZadaniaIntake;
	}
	
	public TypZadania asTypZadaniaEntity(TypZadaniaIntake typZadaniaIntake) {
		TypZadania typZadania = new TypZadania();
		typZadania.setNazwa(typZadaniaIntake.getNazwa());
		JednostkaOrganizacyjna jednostkaOrganizacyjna = jednostkaOrganizacyjnaDao.getById(typZadaniaIntake.getJednostkaOrganizacyjna());
		typZadania.setJednostkaOrganizacyjna(jednostkaOrganizacyjna);
		return typZadania;
	}
	
}
