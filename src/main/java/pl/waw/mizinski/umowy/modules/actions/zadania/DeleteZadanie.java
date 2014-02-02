package pl.waw.mizinski.umowy.modules.actions.zadania;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.security.util.GroupSet;
import org.objectledge.web.mvc.pipeline.GroupSecurityChecking;

import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.model.Zadanie;


@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class DeleteZadanie implements Valve, GroupSecurityChecking {

	private final ZadanieDao zadanieDao;
	private final ResourceGroupRecognizer resourceGroupRecognizer;
	
	public DeleteZadanie(final ZadanieDao zadanieDao, final ResourceGroupRecognizer resourceGroupRecognizer) {
		super();
		this.zadanieDao = zadanieDao;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
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

	@Override
	public GroupSet getResourceGroup(Context context) throws ProcessingException {
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		Long id = requestParameters.getLong("id");
		Zadanie zadanie = zadanieDao.getById(id);
		return resourceGroupRecognizer.resourceGroupByObject(zadanie.getJednostkaOrganizacyjna());
	}



	

}
