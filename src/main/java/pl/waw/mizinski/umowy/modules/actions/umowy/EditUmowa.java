package pl.waw.mizinski.umowy.modules.actions.umowy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeException;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.security.util.GroupSet;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.MVCContext;
import org.objectledge.web.mvc.pipeline.GroupSecurityChecking;

import pl.waw.mizinski.umowy.assembler.UmowaAssembler;
import pl.waw.mizinski.umowy.dao.RachunekDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.intake.UmowaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.model.Zadanie;
import pl.waw.mizinski.umowy.scheduler.RachunekGeneratorValve;
import pl.waw.mizinski.umowy.validation.UmowaValidator;
import pl.waw.mizinski.umowy.validation.ValidationException;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_C"}),
	 @AccessCondition(permissions = {"UMOWA_U"})
})
public class EditUmowa implements Valve, GroupSecurityChecking{

	private final UmowaDao umowaDao;
	private final RachunekDao rachunekDao;
	private final UmowaAssembler umowaAssembler;
	private final UmowaValidator umowaValidator;
	private final RachunekGeneratorValve rachunekGeneratorValve;

	public EditUmowa(final UmowaDao umowaDao, final RachunekDao rachunekDao, final UmowaAssembler umowaAssembler, final RachunekGeneratorValve rachunekGeneratorValve) {
		super();
		this.umowaDao = umowaDao;
		this.rachunekDao = rachunekDao;
		this.umowaAssembler = umowaAssembler;
		this.umowaValidator = new UmowaValidator();
		this.rachunekGeneratorValve = rachunekGeneratorValve;
	}

	@Override
	public void process(final Context context) throws ProcessingException {
		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;
		
		if (intake.isAllValid()) {
			try {
				final Group umowaGroup = intake.get(UmowaIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				UmowaIntake umowaIntake = new UmowaIntake();
				umowaGroup.setProperties(umowaIntake);
				Umowa umowa = umowaAssembler.asUmowaEntity(umowaIntake);
				umowaValidator.validate(umowa);
				transaction = session.beginTransaction();
				umowaDao.saveOrUpdate(umowa);
				rachunekDao.remove(rachunekDao.getRachunekListByUmowa(umowa));
				intake.removeAll();
				transaction.commit();
				rachunekGeneratorValve.processUmowa(umowa, context);
			} catch (final ValidationException e) {
				MVCContext.getMVCContext(context).setView("umowy.EditUmowa");
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

	@Override
	public GroupSet getResourceGroup(Context context)
			throws ProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
