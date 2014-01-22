package pl.waw.mizinski.umowy.ajax;

import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.SkladkaPracownika;

public class UmowaService {

	private final PracownikDao pracownikDao;

	public UmowaService(final PracownikDao pracownikDao) {
		super();
		this.pracownikDao = pracownikDao;
	}

	public boolean isUbezpieczenieDobrowolneActive(String typUmowy, Long idPracownika ) {
		Pracownik pracownik = pracownikDao.getById(idPracownika);
		for (SkladkaPracownika skladkaPracownika : pracownik.getStatus().getSkladki()) {
			if (skladkaPracownika.getDobrowolna() && skladkaPracownika.getTypUmowy().getNazwa().equals(typUmowy)){
				return true;
			}
		}
		return false;
	}
}
