package pl.waw.mizinski.umowy.modules.actions.umowy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;

public class DeleteUmowa implements Valve{

	private final UmowaDao umowaDao;
	
	public DeleteUmowa(UmowaDao umowaDao) {
		super();
		this.umowaDao = umowaDao;
	}

	@Override
	public void process(Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		String nrUmowy = requestParameters.get("nrUmowy");
		Umowa umowa = umowaDao.getById(nrUmowy);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			umowaDao.remove(umowa);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

}
