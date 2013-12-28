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
import org.objectledge.web.mvc.MVCContext;

import pl.waw.mizinski.umowy.assembler.PracownikAssembler;
import pl.waw.mizinski.umowy.dao.AdresDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.intake.PracownikIntake;
import pl.waw.mizinski.umowy.model.Pracownik;

public class EditPracownik implements Valve {

	private final PracownikAssembler pracownikAssembler;
	private final PracownikDao pracownikDao;
	private final AdresDao adresDao;

	public EditPracownik(final PracownikAssembler pracownikAssembler, final PracownikDao pracownikDao, final AdresDao adresDao) {
		super();
		this.pracownikAssembler = pracownikAssembler;
		this.pracownikDao = pracownikDao;
		this.adresDao = adresDao;
	}

	@Override
	public void process(final Context context) throws ProcessingException {

		final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
//		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		Transaction transaction = null;

		if (intake.isAllValid()) {
			try {
				final Group pracownikGroup = intake.get(PracownikIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				PracownikIntake pracownikIntake = new PracownikIntake();
				pracownikGroup.setProperties(pracownikIntake);
				Pracownik pracownik = pracownikAssembler.asPracownikEntity(pracownikIntake);

				transaction = session.beginTransaction();
				adresDao.remove(adresDao.getAdresByPracownik(pracownik));
				pracownikDao.saveOrUpdate(pracownik);
				transaction.commit();

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
}
