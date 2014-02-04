package pl.waw.mizinski.umowy.modules.views.zadania;

import java.util.List;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;
import pl.waw.mizinski.umowy.filter.TypZadaniaFilter;
import pl.waw.mizinski.umowy.filter.security.JednostkaSecurityFilter;
import pl.waw.mizinski.umowy.filter.security.TypZadaniaSecurityFilter;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.pojo.TypZadaniaPOJO;

@AccessConditions({
	 @AccessCondition(permissions = {"TYP_ZADANIA_R"})
})
public class TypyZadan extends AbstractBuilder{

	private final TypZadaniaDao typZadaniaDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final JednostkaSecurityFilter jednostkaSecurityFilter;
	private final TypZadaniaSecurityFilter typZadaniaSecurityFilter;
	
	

	
	public TypyZadan(final Context context, final TypZadaniaDao typZadaniaDao, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao,
			final JednostkaSecurityFilter jednostkaSecurityFilter, final TypZadaniaSecurityFilter typZadaniaSecurityFilter) {
		super(context);
		this.typZadaniaDao = typZadaniaDao;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.jednostkaSecurityFilter = jednostkaSecurityFilter;
		this.typZadaniaSecurityFilter = typZadaniaSecurityFilter;
	}



	@Override
	public String build(Template template, String embeddedBuildResults)
			throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<TypZadaniaPOJO> typyZadan = typZadaniaDao.getAllTypZadaniaPOJOs();
		typyZadan = typZadaniaSecurityFilter.applyFilter(typyZadan);
		List<JednostkaOrganizacyjna> jednostki = jednostkaOrganizacyjnaDao.getAll();
		jednostki = jednostkaSecurityFilter.applyFilter(jednostki);
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final TypZadaniaFilter filter = new TypZadaniaFilter(filterString);
			typyZadan = filter.applyFilter(typyZadan);
		}
		
		templatingContext.put("typy", typyZadan);
		templatingContext.put("jednostki", jednostki);
		return super.build(template, embeddedBuildResults);
	}
}
