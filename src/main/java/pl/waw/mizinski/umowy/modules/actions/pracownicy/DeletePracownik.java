package pl.waw.mizinski.umowy.modules.actions.pracownicy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;

import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.model.Pracownik;

@AccessConditions({
    @AccessCondition(permissions = {"PRACOWNIK_D"})
})
public class DeletePracownik implements Valve {

	private final PracownikDao pracownikDao;

	public DeletePracownik(final PracownikDao pracownikDao) {
		this.pracownikDao = pracownikDao;
	}

	@Override
	public void process(Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		Long id = requestParameters.getLong("id");
		Pracownik pracownik = pracownikDao.getById(id);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			pracownikDao.remove(pracownik);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

}
