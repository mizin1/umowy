package pl.waw.mizinski.umowy.modules.actions.umowy;


import static org.mockito.Mockito.*;
import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;

import pl.waw.mizinski.umowy.dao.UmowaDao;

public class DeleteUmowaTest extends TestCase{
	
	public void testDeleteUmowa() throws Exception{
		
		//definicja mocków
		String nrUmowy = "EXAMPLE";
		UmowaDao umowaDao = mock(UmowaDao.class);
		DeleteUmowa deleteUmowa = new DeleteUmowa(umowaDao, null);
		RequestParameters requestParameters = mock(RequestParameters.class);
		when(requestParameters.get("nrUmowy")).thenReturn(nrUmowy);
		Context context = mock(Context.class);
		when(context.getAttribute(RequestParameters.class))
			.thenReturn(requestParameters);
		Session session = mock(Session.class);
		when(session.beginTransaction()).thenReturn(mock(Transaction.class));
		when(context.getAttribute(HibernateSessionContext.class))
			.thenReturn(new HibernateSessionContext(session));
		
		//testowana metoda
		deleteUmowa.process(context);
		
		//weryfikacja
		verify(umowaDao).remove(nrUmowy);
		
	}
}

