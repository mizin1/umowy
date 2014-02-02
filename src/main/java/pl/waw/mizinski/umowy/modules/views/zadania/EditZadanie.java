package pl.waw.mizinski.umowy.modules.views.zadania;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import pl.waw.mizinski.umowy.assembler.ZadanieAssembler;
import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.filter.security.JednostkaSecurityFilter;
import pl.waw.mizinski.umowy.intake.ZadanieIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypZadania;
import pl.waw.mizinski.umowy.model.Zadanie;
import pl.waw.mizinski.umowy.util.JednostkaOrganizacyjnaComprarator;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_W"})
})
public class EditZadanie extends AbstractBuilder {

	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final TypZadaniaDao typZadaniaDao;
	private final ZadanieDao zadanieDao;
	private final ZadanieAssembler zadanieAssembler;
	private final JednostkaSecurityFilter jednostkaSecurityFilter;

	public EditZadanie(final Context context, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao,
			final TypZadaniaDao typZadaniaDao, final ZadanieDao zadanieDao, final ZadanieAssembler zadanieAssembler,
			 final JednostkaSecurityFilter jednostkaSecurityFilter) {
		super(context);
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.typZadaniaDao = typZadaniaDao;
		this.zadanieDao = zadanieDao;
		this.zadanieAssembler = zadanieAssembler;
		this.jednostkaSecurityFilter = jednostkaSecurityFilter;
	}

	@Override
	public String build(final Template template, final String embeddedBuildResults) throws BuildException,
			ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);

		if (requestParameters.isDefined("id")) {
			final long id = requestParameters.getInt("id");
			final Zadanie zadanie = zadanieDao.getById(id);
			final ZadanieIntake zadanieIntake = zadanieAssembler.asZadanieIntake(zadanie);
			try {
				final Group zadanieGroup = intake.get(ZadanieIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				zadanieGroup.getProperties(zadanieIntake);
			} catch (final IntakeException e) {
				throw new ProcessingException(e);
			}
		}
		Map<JednostkaOrganizacyjna, List<TypZadania>> zadaniaJednostek = new TreeMap<>(new JednostkaOrganizacyjnaComprarator());
		for (JednostkaOrganizacyjna jednostka : jednostkaOrganizacyjnaDao.getAll()) {
			if(jednostkaSecurityFilter.checkSecurity(jednostka)) {
				List<TypZadania> typyZadan = typZadaniaDao.getTypyZadaniaByJednostka(jednostka);
				if (!typyZadan.isEmpty()) {
					zadaniaJednostek.put(jednostka, typyZadan);
				}
			}
		}
		templatingContext.put("zadaniaJednostek", zadaniaJednostek);
		return super.build(template, embeddedBuildResults);
	}
}
