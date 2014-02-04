package pl.waw.mizinski.umowy.modules.views.pracownicy;

import org.objectledge.context.Context;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeException;
import org.objectledge.intake.IntakeTool;
import org.objectledge.intake.model.Group;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.assembler.PracownikAssembler;
import pl.waw.mizinski.umowy.dao.AdresPracownikaDao;
import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.SkladkaDao;
import pl.waw.mizinski.umowy.dao.StatusPracownikaDao;
import pl.waw.mizinski.umowy.dao.TypUmowyDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.intake.PracownikIntake;
import pl.waw.mizinski.umowy.model.Pracownik;

@AccessConditions({
	 @AccessCondition(permissions = {"PRACOWNIK_C"}),
	 @AccessCondition(permissions = {"PRACOWNIK_U"}),
})
public class EditPracownik extends AbstractBuilder {

	private final PanstwoDao panstwoDao;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	private final PracownikDao pracownikDao;
	private final StatusPracownikaDao statusPracownikaDao;
	private final SkladkaDao skladkaDao;
	private final TypUmowyDao typUmowyDao;

	private final PracownikAssembler pracownikAssembler;
	
	public EditPracownik(final Context context, final PanstwoDao panstwoDao, final UrzadSkarbowyDao urzadSkarbowyDao,
			final PracownikDao pracownikDao, final AdresPracownikaDao adresDao, final PracownikAssembler pracownikAssembler, 
			final StatusPracownikaDao statusPracownikaDao, final TypUmowyDao typUmowyDao, final SkladkaDao skladkaDao) {
		super(context);
		this.panstwoDao = panstwoDao;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
		this.pracownikDao = pracownikDao;
		this.pracownikAssembler = pracownikAssembler;
		this.statusPracownikaDao = statusPracownikaDao;
		this.typUmowyDao = typUmowyDao;
		this.skladkaDao = skladkaDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);

		if (requestParameters.isDefined("id")) {
			final long id = requestParameters.getInt("id");
			final Pracownik pracownik = pracownikDao.getById(id);
			final PracownikIntake pracownikIntake = pracownikAssembler.asPracownikIntake(pracownik);
			try {
				final Group pracownikGroup = intake.get(PracownikIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				pracownikGroup.getProperties(pracownikIntake);
			} catch (IntakeException e) {
				throw new ProcessingException(e);
			}
		}
		
		templatingContext.put("panstwa", panstwoDao.getAll());
		templatingContext.put("urzedySkarobowe", urzadSkarbowyDao.getAllUrzadSkarbowyNazwaPOJOs());
		templatingContext.put("statusy", statusPracownikaDao.getAll());
		templatingContext.put("skladki", skladkaDao.getSkladkaOrderedList());
		templatingContext.put("typy", typUmowyDao.getAll());
		return super.build(template, embeddedBuildResults);
	} 
}
