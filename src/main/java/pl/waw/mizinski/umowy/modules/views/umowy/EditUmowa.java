package pl.waw.mizinski.umowy.modules.views.umowy;

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
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.security.util.GroupSet;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;
import org.objectledge.web.mvc.pipeline.GroupSecurityChecking;

import pl.waw.mizinski.umowy.assembler.UmowaAssembler;
import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.PlatnoscDao;
import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.dao.TypUmowyDao;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.filter.security.JednostkaSecurityFilter;
import pl.waw.mizinski.umowy.intake.UmowaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypZadania;
import pl.waw.mizinski.umowy.model.Umowa;
import pl.waw.mizinski.umowy.util.JednostkaOrganizacyjnaComprarator;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_C"}),
	 @AccessCondition(permissions = {"UMOWA_U"})
})
public class EditUmowa extends AbstractBuilder implements GroupSecurityChecking{

	private final PracownikDao pracownikDao;
	private final TypZadaniaDao typZadaniaDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final TypUmowyDao typUmowyDao;
	private final PlatnoscDao platnoscDao;
	private final UmowaDao umowaDao;
	private final UmowaAssembler umowaAssembler;
	private final JednostkaSecurityFilter jednostkaSecurityFilter;
	private final ResourceGroupRecognizer resourceGroupRecognizer;

	public EditUmowa(final Context context, final PracownikDao pracownikDao, final TypZadaniaDao typZadaniaDao,
			final TypUmowyDao typUmowyDao, final PlatnoscDao platnoscDao, final UmowaDao umowaDao,
			final UmowaAssembler umowaAssembler, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao,
			final JednostkaSecurityFilter jednostkaSecurityFilter, final ResourceGroupRecognizer resourceGroupRecognizer) {
		super(context);
		this.pracownikDao = pracownikDao;
		this.typZadaniaDao = typZadaniaDao;
		this.typUmowyDao = typUmowyDao;
		this.platnoscDao = platnoscDao;
		this.umowaDao = umowaDao;
		this.umowaAssembler = umowaAssembler;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.jednostkaSecurityFilter = jednostkaSecurityFilter;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
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
			templatingContext.put("zadanieUmowy", umowa.getZadanie());
		}
		
		Map<JednostkaOrganizacyjna, List<TypZadania>> zadaniaJednostek = new TreeMap<>(new JednostkaOrganizacyjnaComprarator());
		for (JednostkaOrganizacyjna jednostka : jednostkaOrganizacyjnaDao.getAll()) {
			if (jednostkaSecurityFilter.checkSecurity(jednostka)) {
				List<TypZadania> typyZadan = typZadaniaDao.getTypyZadaniaByJednostka(jednostka);
				if (!typyZadan.isEmpty()) {
					zadaniaJednostek.put(jednostka, typyZadan);
				}
			}
		}
		templatingContext.put("pracownicy", pracownikDao.getAll());
		templatingContext.put("zadaniaJednostek", zadaniaJednostek);
		templatingContext.put("typyUmowy", typUmowyDao.getAll());
		templatingContext.put("platnosci", platnoscDao.getAll());
		return super.build(template, embeddedBuildResults);
	}

	@Override
	public GroupSet getResourceGroup(Context context)throws ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		if (requestParameters.isDefined("nrUmowy")) {
			final String nrUmowy = requestParameters.get("nrUmowy");
			final Umowa umowa = umowaDao.getById(nrUmowy);
			return resourceGroupRecognizer.resourceGroupByObject(umowa.getJednostkaOrganizacyjna());
		}
		return GroupSet.emptySet();
	}

}
