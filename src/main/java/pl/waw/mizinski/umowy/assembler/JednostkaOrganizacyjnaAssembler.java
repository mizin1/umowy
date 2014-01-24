package pl.waw.mizinski.umowy.assembler;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.ReprezentantDao;
import pl.waw.mizinski.umowy.dao.TypJednostkiDao;
import pl.waw.mizinski.umowy.intake.JednostkaOrganizacyjnaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.Reprezentant;
import pl.waw.mizinski.umowy.model.TypJednostki;

public class JednostkaOrganizacyjnaAssembler {
	
	private final TypJednostkiDao typJednostkiDao;
	private final ReprezentantDao reprezentantDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public JednostkaOrganizacyjnaAssembler(final TypJednostkiDao typJednostkiDao, final ReprezentantDao reprezentantDao,
			final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		this.typJednostkiDao = typJednostkiDao;
		this.reprezentantDao = reprezentantDao;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
	}
	
	public JednostkaOrganizacyjnaIntake asJednostkaOrganizacyjnaIntake(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
		JednostkaOrganizacyjnaIntake jednostkaOrganizacyjnaIntake = new JednostkaOrganizacyjnaIntake();
		jednostkaOrganizacyjnaIntake.setNazwa(jednostkaOrganizacyjna.getNazwa());
		jednostkaOrganizacyjnaIntake.setTypJednostki(jednostkaOrganizacyjna.getTypJednostki().getNazwa());
		if (jednostkaOrganizacyjna.getJednostkaNadrzedna() != null) {
			jednostkaOrganizacyjnaIntake.setJednostkaNadrzedna(jednostkaOrganizacyjna.getJednostkaNadrzedna().getNazwa());
		}
		jednostkaOrganizacyjnaIntake.setReprezentant(jednostkaOrganizacyjna.getReprezentant().getNazwa());
		jednostkaOrganizacyjnaIntake.setMiejscowosc(jednostkaOrganizacyjna.getMiejscowosc());
		jednostkaOrganizacyjnaIntake.setUlica(jednostkaOrganizacyjna.getUlica());
		jednostkaOrganizacyjnaIntake.setNrDomu(jednostkaOrganizacyjna.getNrDomu());
		jednostkaOrganizacyjnaIntake.setNrMieszkania(jednostkaOrganizacyjna.getNrMieszkania());
		jednostkaOrganizacyjnaIntake.setKodPocztowy(jednostkaOrganizacyjna.getKodPocztowy());
		jednostkaOrganizacyjnaIntake.setPoczta(jednostkaOrganizacyjna.getPoczta());
		return jednostkaOrganizacyjnaIntake;
	}
	
	
	
	
	public JednostkaOrganizacyjna asJednostkaOrganizacyjnaEntity(JednostkaOrganizacyjnaIntake jednostkaOrganizacyjnaIntake) {
		JednostkaOrganizacyjna jednostkaOrganizacyjna = new JednostkaOrganizacyjna();
		jednostkaOrganizacyjna.setNazwa(jednostkaOrganizacyjnaIntake.getNazwa());
		TypJednostki typJednostki = typJednostkiDao.getById(jednostkaOrganizacyjnaIntake.getTypJednostki());
		jednostkaOrganizacyjna.setTypJednostki(typJednostki);
		JednostkaOrganizacyjna jednostkaNadrzedna = jednostkaOrganizacyjnaDao.getById(jednostkaOrganizacyjnaIntake.getJednostkaNadrzedna());
		jednostkaOrganizacyjna.setJednostkaNadrzedna(jednostkaNadrzedna);
		Reprezentant reprezentant = reprezentantDao.getById(jednostkaOrganizacyjnaIntake.getReprezentant());
		jednostkaOrganizacyjna.setReprezentant(reprezentant);
		jednostkaOrganizacyjna.setMiejscowosc(jednostkaOrganizacyjnaIntake.getMiejscowosc());
		jednostkaOrganizacyjna.setUlica(jednostkaOrganizacyjnaIntake.getUlica());
		jednostkaOrganizacyjna.setNrDomu(jednostkaOrganizacyjnaIntake.getNrDomu());
		jednostkaOrganizacyjna.setNrMieszkania(jednostkaOrganizacyjnaIntake.getNrMieszkania());
		jednostkaOrganizacyjna.setKodPocztowy(jednostkaOrganizacyjnaIntake.getKodPocztowy());
		jednostkaOrganizacyjna.setPoczta(jednostkaOrganizacyjnaIntake.getPoczta());
		return jednostkaOrganizacyjna;
	}
}
