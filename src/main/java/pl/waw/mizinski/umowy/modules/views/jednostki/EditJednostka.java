package pl.waw.mizinski.umowy.modules.views.jednostki;

import java.util.LinkedList;
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

import pl.waw.mizinski.umowy.assembler.JednostkaOrganizacyjnaAssembler;
import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.ReprezentantDao;
import pl.waw.mizinski.umowy.dao.TypJednostkiDao;
import pl.waw.mizinski.umowy.intake.JednostkaOrganizacyjnaIntake;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.TypJednostki;
import pl.waw.mizinski.umowy.util.TypJednostkiComparator;

@AccessConditions({
	 @AccessCondition(permissions = {"JEDNOSTKA_C"}),
	 @AccessCondition(permissions = {"JEDNOSTKA_U"})
})
public class EditJednostka extends AbstractBuilder {

	private final TypJednostkiDao typJednostkiDao;
	private final ReprezentantDao reprezentantDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final JednostkaOrganizacyjnaAssembler jednostkaOrganizacyjnaAssembler;
	
	public EditJednostka(final Context context, final TypJednostkiDao typJednostkiDao, final ReprezentantDao reprezentantDao, 
			final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao, final JednostkaOrganizacyjnaAssembler jednostkaOrganizacyjnaAssembler,
			final ResourceGroupRecognizer resourceGroupRecognizer) {
		super(context);
		this.typJednostkiDao = typJednostkiDao;
		this.reprezentantDao = reprezentantDao;
		this.jednostkaOrganizacyjnaDao	= jednostkaOrganizacyjnaDao;
		this.jednostkaOrganizacyjnaAssembler = jednostkaOrganizacyjnaAssembler;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		
		if (requestParameters.isDefined("nazwa")) {
			final String nazwa = requestParameters.get("nazwa");
			final JednostkaOrganizacyjna jednostkaOrganizacyjna = jednostkaOrganizacyjnaDao.getById(nazwa);
			final JednostkaOrganizacyjnaIntake jednostkaOrganizacyjnaIntake = jednostkaOrganizacyjnaAssembler.asJednostkaOrganizacyjnaIntake(jednostkaOrganizacyjna);
			try {
				final Group jednostkaGroup = intake.get(JednostkaOrganizacyjnaIntake.class.getSimpleName(), IntakeTool.DEFAULT_KEY);
				jednostkaGroup.getProperties(jednostkaOrganizacyjnaIntake);
			} catch (final IntakeException e) {
				throw new ProcessingException(e);
			}
		}
		
		Map<TypJednostki, List<TypJednostki>> typyNadrzedne = new TreeMap<>(new TypJednostkiComparator());
		for(TypJednostki typJednostki : typJednostkiDao.getAll()) {
			List<TypJednostki> typy= new LinkedList<>();
			TypJednostki typNadrzedny = typJednostki.getTypNadrzedny();
			while (typNadrzedny != null) {
				typy.add(typNadrzedny);
				typNadrzedny = typNadrzedny.getTypNadrzedny();
			}
			typyNadrzedne.put(typJednostki, typy);
		}
		templatingContext.put("reprezentanci", reprezentantDao.getAll());
		templatingContext.put("typyJednostki", typyNadrzedne);
		templatingContext.put("jednostki", jednostkaOrganizacyjnaDao.getAll());
		return super.build(template, embeddedBuildResults);
	}

	
	
}
