package pl.waw.mizinski.umowy.modules.actions.umowy;

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

import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;
@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_D"})
})
public class DeleteUmowa implements Valve, GroupSecurityChecking{

	private final UmowaDao umowaDao;
	private final ResourceGroupRecognizer resourceGroupRecognizer;
	
	public DeleteUmowa(UmowaDao umowaDao, final ResourceGroupRecognizer resourceGroupRecognizer) {
		this.umowaDao = umowaDao;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		String nrUmowy = requestParameters.get("nrUmowy");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			umowaDao.remove(nrUmowy);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

	@Override
	public GroupSet getResourceGroup(final Context context) throws ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final String nrUmowy = requestParameters.get("nrUmowy");
		final Umowa umowa = umowaDao.getById(nrUmowy);
		return resourceGroupRecognizer.resourceGroupByObject(umowa.getJednostkaOrganizacyjna());
	}

}
