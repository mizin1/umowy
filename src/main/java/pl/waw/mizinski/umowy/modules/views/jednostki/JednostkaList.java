package pl.waw.mizinski.umowy.modules.views.jednostki;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;

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
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("jednostki", jednostkaOrganizacyjnaDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
}
