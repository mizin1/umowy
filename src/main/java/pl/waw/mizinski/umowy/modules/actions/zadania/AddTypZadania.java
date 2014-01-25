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

import pl.waw.mizinski.umowy.assembler.TypZadaniaAssembler;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.intake.TypZadaniaIntake;
import pl.waw.mizinski.umowy.model.TypZadania;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class AddTypZadania implements Valve{
	
	private final TypZadaniaDao typZadaniaDao;
	private final TypZadaniaAssembler typZadaniaAssembler;
	
	public AddTypZadania(final TypZadaniaDao typZadaniaDao, final TypZadaniaAssembler typZadaniaAssembler) {
		super();
		this.typZadaniaDao = typZadaniaDao;
		this.typZadaniaAssembler = typZadaniaAssembler;
	}
	
	@Override
	public void process(final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;
		
		if (intake.isAllValid()) {
			try {
				final Group typZadaniaGroup = intake.get(TypZadania.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				TypZadaniaIntake typZadaniaIntake = new TypZadaniaIntake();
				typZadaniaGroup.setProperties(typZadaniaIntake);
				TypZadania typZadania = typZadaniaAssembler.asTypZadaniaEntity(typZadaniaIntake);
				transaction = session.beginTransaction();
				typZadaniaDao.saveOrUpdate(typZadania);
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
