package pl.waw.mizinski.umowy.modules.actions.platnosci;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;

import pl.waw.mizinski.umowy.dao.PlatnoscDao;

@AccessConditions({
	 @AccessCondition(permissions = {"PLATNOSC_W"})
})
public class DeletePlatnosc implements Valve {

	private final PlatnoscDao platnoscDao;
	
	public DeletePlatnosc(final PlatnoscDao platnoscDao) {
		super();
		this.platnoscDao = platnoscDao;
	}
	
	@Override
	public void process(final Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		String nazwa = requestParameters.get("nazwa");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			platnoscDao.remove(nazwa);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}
}
