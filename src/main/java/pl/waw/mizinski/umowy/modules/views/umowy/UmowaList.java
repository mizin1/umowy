package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.UmowaDao;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class UmowaList extends AbstractBuilder{

	private final UmowaDao umowaDao;

	public UmowaList(final Context context, final UmowaDao umowaDao) {
		super(context);
		this.umowaDao = umowaDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("umowy", umowaDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
	
}
