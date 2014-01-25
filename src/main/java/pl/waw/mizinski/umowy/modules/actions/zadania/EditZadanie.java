package pl.waw.mizinski.umowy.modules.actions.zadania;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.web.mvc.MVCContext;

import pl.waw.mizinski.umowy.assembler.ZadanieAssembler;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.intake.ZadanieIntake;
import pl.waw.mizinski.umowy.model.Zadanie;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class EditZadanie implements Valve {

	private final ZadanieDao zadanieDao;
	private final ZadanieAssembler zadanieAssembler;

	public EditZadanie(final ZadanieDao zadanieDao, final ZadanieAssembler zadanieAssembler) {
		this.zadanieDao = zadanieDao;
		this.zadanieAssembler = zadanieAssembler;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;

		if (intake.isAllValid()) {
			try {
				final Group zadanieGroup = intake.get(ZadanieIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				ZadanieIntake zadanieIntake = new ZadanieIntake();
				zadanieGroup.setProperties(zadanieIntake);
				Zadanie zadanie = zadanieAssembler.asZadanieEntity(zadanieIntake);
				
				transaction = session.beginTransaction();
				zadanieDao.saveOrUpdate(zadanie);
				intake.removeAll();
				transaction.commit();
			} catch (final Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new ProcessingException(e);
			}

		} else {
			MVCContext.getMVCContext(context).setView("zadania.EditZadanie");
		}
	}
}
