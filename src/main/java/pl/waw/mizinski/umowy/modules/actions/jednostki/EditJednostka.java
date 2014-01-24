package pl.waw.mizinski.umowy.modules.actions.jednostki;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.web.mvc.MVCContext;

import pl.waw.mizinski.umowy.assembler.JednostkaOrganizacyjnaAssembler;
import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.intake.JednostkaOrganizacyjnaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;

public class EditJednostka implements Valve{

	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final JednostkaOrganizacyjnaAssembler jednostkaOrganizacyjnaAssembler;	
	
	public EditJednostka(final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao, final JednostkaOrganizacyjnaAssembler jednostkaOrganizacyjnaAssembler) {
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.jednostkaOrganizacyjnaAssembler = jednostkaOrganizacyjnaAssembler;
	}



	@Override
	public void process(Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;
		if (intake.isAllValid()) {
			try {
				final Group jednostkaGroup = intake.get(JednostkaOrganizacyjnaIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				JednostkaOrganizacyjnaIntake jednostkaOrganizacyjnaIntake = new JednostkaOrganizacyjnaIntake();
				jednostkaGroup.setProperties(jednostkaOrganizacyjnaIntake);
				JednostkaOrganizacyjna jednostkaOrganizacyjna = jednostkaOrganizacyjnaAssembler.asJednostkaOrganizacyjnaEntity(jednostkaOrganizacyjnaIntake);
				transaction = session.beginTransaction();
				jednostkaOrganizacyjnaDao.save(jednostkaOrganizacyjna);
				intake.removeAll();
				transaction.commit();
			} catch (final Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new ProcessingException(e);
			}
		} else {
			MVCContext.getMVCContext(context).setView("jednostki.EditJednostka");
		}
		
	}

}
