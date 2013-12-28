package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeException;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.assembler.UmowaAssembler;
import pl.waw.mizinski.umowy.dao.PlatnoscDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.TypUmowyDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.intake.UmowaIntake;
import pl.waw.mizinski.umowy.model.Umowa;

public class EditUmowa extends AbstractBuilder {

	private final PracownikDao pracownikDao;
	private final ZadanieDao zadanieDao;
	private final TypUmowyDao typUmowyDao;
	private final PlatnoscDao platnoscDao;
	private final UmowaDao umowaDao;

	private final UmowaAssembler umowaAssembler;

	public EditUmowa(final Context context, final PracownikDao pracownikDao, final ZadanieDao zadanieDao,
			final TypUmowyDao typUmowyDao, final PlatnoscDao platnoscDao, final UmowaDao umowaDao,
			final UmowaAssembler umowaAssembler) {
		super(context);
		this.pracownikDao = pracownikDao;
		this.zadanieDao = zadanieDao;
		this.typUmowyDao = typUmowyDao;
		this.platnoscDao = platnoscDao;
		this.umowaDao = umowaDao;
		this.umowaAssembler = umowaAssembler;
	}

	@Override
	public String build(final Template template, final String embeddedBuildResults) throws BuildException, ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		
		if (requestParameters.isDefined("nrUmowy")) {
			final String nrUmowy = requestParameters.get("nrUmowy");
			final Umowa umowa = umowaDao.getById(nrUmowy);
			final UmowaIntake umowaIntake = umowaAssembler.asUmowaIntake(umowa);
			try {
				final Group umowaGroup = intake.get(UmowaIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				umowaGroup.getProperties(umowaIntake);
			} catch (final IntakeException e) {
				throw new ProcessingException(e);
			}
		}
		
		templatingContext.put("pracownicy", pracownikDao.getAll());
		templatingContext.put("zadania", zadanieDao.getAll());
		templatingContext.put("typyUmowy", typUmowyDao.getAll());
		templatingContext.put("platnosci", platnoscDao.getAll());
		return super.build(template, embeddedBuildResults);
	}

}
