package pl.waw.mizinski.umowy.dao;

import org.objectledge.context.Context;

import pl.waw.mizinski.umowy.model.Zadanie;

public class ZadanieDao extends AbstractDao<Long, Zadanie>{

	public ZadanieDao(Context context) {
		super(context);
	}

}
