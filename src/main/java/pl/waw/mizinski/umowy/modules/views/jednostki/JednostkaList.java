package pl.waw.mizinski.umowy.modules.views.jednostki;

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
import pl.waw.mizinski.umowy.filter.JednostkaFilter;
import pl.waw.mizinski.umowy.pojo.JednostkaPOJO;

@AccessConditions({
	 @AccessCondition(permissions = {"JEDNOSTKA_R"})
})
public class JednostkaList extends AbstractBuilder{

	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public JednostkaList(final Context context, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		super(context);
		this.jednostkaOrganizacyjnaDao	= jednostkaOrganizacyjnaDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults)
			throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<JednostkaPOJO> jednostki = jednostkaOrganizacyjnaDao.getAllJednostkaPOJOs();
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final JednostkaFilter filter = new JednostkaFilter(filterString);
			jednostki = filter.applyFilter(jednostki);
		}
		
		templatingContext.put("jednostki", jednostki);
		templatingContext.put("jednostkiNadrzedne", jednostkaOrganizacyjnaDao.getJednostkiNadrzedne());
		return super.build(template, embeddedBuildResults);
	}
	
	
}
