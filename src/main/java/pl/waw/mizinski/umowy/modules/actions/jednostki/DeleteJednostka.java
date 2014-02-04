package pl.waw.mizinski.umowy.modules.actions.jednostki;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.SecurityManager;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;

@AccessConditions({
	 @AccessCondition(permissions = {"JEDNOSTKA_D"})
})
public class DeleteJednostka implements Valve {

	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final SecurityManager securityManager;

	public DeleteJednostka(final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao, final SecurityManager securityManager) {
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.securityManager = securityManager;
	}

	@Override
	public void process(Context context) throws ProcessingException {
		Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		String nazwa = requestParameters.get("nazwa");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			jednostkaOrganizacyjnaDao.remove(nazwa);
			securityManager.removeGroup(securityManager.getGroupByName(nazwa));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ProcessingException(e);
		}
	}

}
