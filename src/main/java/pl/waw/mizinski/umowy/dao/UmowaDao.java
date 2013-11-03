package pl.waw.mizinski.umowy.dao;

import org.objectledge.context.Context;

import pl.waw.mizinski.umowy.model.Umowa;

public class UmowaDao extends AbstractDao<String, Umowa> {

	public UmowaDao(Context context) {
		super(context);
	}

}

