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

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypZadania;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class DeleteTypZadania implements Valve, GroupSecurityChecking {

	private final TypZadaniaDao typZadaniaDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final ResourceGroupRecognizer resourceGroupRecognizer;
	
	public DeleteTypZadania(final TypZadaniaDao typZadaniaDao, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao,
			final ResourceGroupRecognizer resourceGroupRecognizer) {
		super();
		this.typZadaniaDao = typZadaniaDao;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
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

	@Override
	public GroupSet getResourceGroup(Context context) throws ProcessingException {
	
			final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
			final String nazwaJednostki = requestParameters.get("jednostka");
			JednostkaOrganizacyjna jednostkaOrganizacyjna = jednostkaOrganizacyjnaDao.getById(nazwaJednostki);
			return resourceGroupRecognizer.resourceGroupByObject(jednostkaOrganizacyjna);
		
	}
}
