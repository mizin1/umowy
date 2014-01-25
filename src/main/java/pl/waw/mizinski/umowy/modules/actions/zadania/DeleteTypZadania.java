package pl.waw.mizinski.umowy.modules.actions.zadania;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;

import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.model.TypZadania;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class DeleteTypZadania implements Valve {

	private final TypZadaniaDao typZadaniaDao;
	
	public DeleteTypZadania(final TypZadaniaDao typZadaniaDao) {
		super();
		this.typZadaniaDao = typZadaniaDao;
	}
	
	@Override
	public void process(final Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		String nazwa = requestParameters.get("nazwa");
		String jednostka = requestParameters.get("jednostka");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			TypZadania typZadania = typZadaniaDao.getTypZadania(nazwa, jednostka);
			typZadaniaDao.remove(typZadania);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}
}
