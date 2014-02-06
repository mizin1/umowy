package pl.waw.mizinski.umowy.modules.actions.pracownicy;

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

import pl.waw.mizinski.umowy.assembler.PracownikAssembler;
import pl.waw.mizinski.umowy.dao.AdresPracownikaDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.intake.PracownikIntake;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.validation.PracownikValidator;
import pl.waw.mizinski.umowy.validation.ValidationException;

@AccessConditions({
    @AccessCondition(permissions = {"PRACOWNIK_C"}),
    @AccessCondition(permissions = {"PRACOWNIK_U"})
})
public class EditPracownik implements Valve {

	private final PracownikAssembler pracownikAssembler;
	private final PracownikDao pracownikDao;
	private final AdresPracownikaDao adresPracownikaDao;
	private final PracownikValidator pracownikValidator;

	public EditPracownik(final PracownikAssembler pracownikAssembler, final PracownikDao pracownikDao,
			final AdresPracownikaDao adresPracownikaDao) {
		super();
		this.pracownikAssembler = pracownikAssembler;
		this.pracownikDao = pracownikDao;
		this.adresPracownikaDao = adresPracownikaDao;
		this.pracownikValidator = new PracownikValidator();
	}

	@Override
	public void process(final Context context) throws ProcessingException {

		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		Transaction transaction = null;

		if (intake.isAllValid()) {
			try {
				final Group pracownikGroup = intake.get(PracownikIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				PracownikIntake pracownikIntake = new PracownikIntake();
				pracownikGroup.setProperties(pracownikIntake);
				Pracownik pracownik = pracownikAssembler.asPracownikEntity(pracownikIntake);
				pracownikValidator.validate(pracownik);
				transaction = session.beginTransaction();
				deleteUnusedAddresses(pracownik);
				pracownikDao.saveOrUpdate(pracownik);
				intake.removeAll();
				transaction.commit();
			} catch (final ValidationException e) {
				MVCContext.getMVCContext(context).setView("pracownicy.EditPracownik");
				final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
				templatingContext.put("errorResult", e.getMessage());
			} catch (final Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new ProcessingException(e);
			}
		} else {
			MVCContext.getMVCContext(context).setView("pracownicy.EditPracownik");
		}
	}

	private void deleteUnusedAddresses(Pracownik pracownik) {
		if (pracownik.getId()!= null && !pracownik.hasAdresKorespondencyjny() && adresPracownikaDao.getAdresKorespondencyjny(pracownik) != null) {
			adresPracownikaDao.remove(adresPracownikaDao.getAdresKorespondencyjny(pracownik));
		}
		if (pracownik.getId()!= null && !pracownik.hasAdresZameldowania() && adresPracownikaDao.getAdresZameldowania(pracownik) != null) {
			adresPracownikaDao.remove(adresPracownikaDao.getAdresZameldowania(pracownik));
		}
	}
}
