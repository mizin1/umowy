package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.pojo.PracownikImieNazwiskoPOJO;

public class PracownikDao extends AbstractDao<Long, Pracownik> {

	private final static String PRACOWNIK_IMIE_NAZWISKO_POJO = 
			"pl.waw.mizinski.umowy.pojo"
			+ ".PracownikImieNazwiskoPOJO(p.id, p.pierwszeImie, p.nazwisko)";

	public PracownikDao(Context context) {
		super(context);
	}

	public List<PracownikImieNazwiskoPOJO> getAllPracownikImieNazwiskoPOJOs() {
		Query query = session().createQuery(
				"select new " + PRACOWNIK_IMIE_NAZWISKO_POJO
						+ "from Pracownik p");
		return HibernateUtils.queryResult(session(), query);
	}
}
