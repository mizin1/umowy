package pl.waw.mizinski.umowy.modules.actions.admin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

import pl.waw.mizinski.umowy.dao.PlatnoscDao;
import pl.waw.mizinski.umowy.model.Platnosc;

public class AddPlatnosc implements Valve {

	private final PlatnoscDao platnoscDao;

	public AddPlatnosc(PlatnoscDao platnoscDao) {
		super();
		this.platnoscDao = platnoscDao;
	}
	
	@Override
	public void process(Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;

		if (intake.isAllValid()) {
			try {
				final Group platnoscGroup = intake.get(Platnosc.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				Platnosc platnosc = new Platnosc();
				platnoscGroup.setProperties(platnosc);
				transaction = session.beginTransaction();
				platnoscDao.saveOrUpdate(platnosc);
				intake.removeAll();
				transaction.commit();
			} catch (final Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new ProcessingException(e);
			}
		} else {
			//FIXME
		}
		
	}

}
