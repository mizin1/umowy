package pl.waw.mizinski.umowy.modules.actions.umowy;

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
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.MVCContext;

import pl.waw.mizinski.umowy.assembler.UmowaAssembler;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.intake.UmowaIntake;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.validation.UmowaValidator;
import pl.waw.mizinski.umowy.validation.ValidationException;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_W"})
})
public class EditUmowa implements Valve {

	private final UmowaDao umowaDao;
	private final UmowaAssembler umowaAssembler;
	private final UmowaValidator umowaValidator;

	public EditUmowa(final UmowaDao umowaDao, final UmowaAssembler umowaAssembler) {
		super();
		this.umowaDao = umowaDao;
		this.umowaAssembler = umowaAssembler;
		this.umowaValidator = new UmowaValidator();
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;
		
		if (intake.isAllValid()) {
			try {
				final Group umowaGroup = intake.get(UmowaIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				UmowaIntake umowakIntake = new UmowaIntake();
				umowaGroup.setProperties(umowakIntake);
				Umowa umowa = umowaAssembler.asUmowaEntity(umowakIntake);
				umowaValidator.validate(umowa);
				transaction = session.beginTransaction();
				umowaDao.saveOrUpdate(umowa);
				intake.removeAll();
				transaction.commit();
			} catch (final ValidationException e) {
				MVCContext.getMVCContext(context);MVCContext.getMVCContext(context).setView("umowy.EditUmowa");
				final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
				templatingContext.put("errorResult", e.getMessage());
			} catch (final Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new ProcessingException(e);
			}

		} else {
			MVCContext.getMVCContext(context).setView("umowy.EditUmowa");
		}
	}

}
