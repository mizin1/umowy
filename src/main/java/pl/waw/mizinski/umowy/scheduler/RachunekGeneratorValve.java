package pl.waw.mizinski.umowy.scheduler;

import java.util.List;

import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.RachunekDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;

public class RachunekGeneratorValve implements Valve{

	private final UmowaDao umowaDao;
	private final RachunekDao rachunekDao;
	
	public RachunekGeneratorValve(final UmowaDao umowaDao, final RachunekDao rachunekDao) {
		super();
		this.umowaDao = umowaDao;
		this.rachunekDao = rachunekDao;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		List<Umowa> umowy = umowaDao.getAll();
	}
	
}
