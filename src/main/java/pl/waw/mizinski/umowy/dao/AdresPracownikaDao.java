package pl.waw.mizinski.umowy.dao;

import java.util.List;

import org.hibernate.Query;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateUtils;

import pl.waw.mizinski.umowy.model.AdresPracownika;
import pl.waw.mizinski.umowy.model.AdresPracownikaPK;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.enums.TypAdresu;

public class AdresPracownikaDao extends AbstractDao<AdresPracownikaPK, AdresPracownika>{

	public AdresPracownikaDao(Context context) {
		super(context);
	}
	
	public List<AdresPracownika> getAdresByPracownik(Pracownik pracownik) {
		Query query = session().createQuery("from AdresPracownika a where a.adresPracownikaPK.pracownik=?");
		query.setParameter(0, pracownik);
		return HibernateUtils.queryResult(session(), query);
	}
	
	public AdresPracownika getAdresPracownika(Pracownik pracownik, TypAdresu typAdresu) {
		return getById(new AdresPracownikaPK(pracownik,typAdresu));
	}
	
	public AdresPracownika getAdresKorespondencyjny(Pracownik pracownik){
		return getAdresPracownika(pracownik, TypAdresu.korespondencyjny);
	}
	
	public AdresPracownika getAdresWCelachPodatkowych(Pracownik pracownik){
		return getAdresPracownika(pracownik, TypAdresu.w_celach_podatkowych);
	}
}
