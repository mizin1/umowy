package pl.waw.mizinski.umowy.dao;

import org.objectledge.context.Context;

import pl.waw.mizinski.umowy.model.Pracownik;

public class PracownikDao extends AbstractDao<Long, Pracownik> {

	public PracownikDao(Context context) {
		super(context);
	}

}
