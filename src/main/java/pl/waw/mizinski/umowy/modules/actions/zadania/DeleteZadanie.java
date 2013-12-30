package pl.waw.mizinski.umowy.modules.actions.zadania;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.model.Pracownik;

public class DeleteZadanie implements Valve {

	private final ZadanieDao zadanieDao;
	
	public DeleteZadanie(final ZadanieDao zadanieDao) {
		super();
		this.zadanieDao = zadanieDao;
	}
	
	@Override
	public void process(final Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		Long id = requestParameters.getLong("id");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zadanieDao.remove(id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}



	

}
