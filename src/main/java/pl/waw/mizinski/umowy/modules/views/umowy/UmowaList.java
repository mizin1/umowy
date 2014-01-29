package pl.waw.mizinski.umowy.modules.views.umowy;

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

import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.filter.UmowaFilter;
import pl.waw.mizinski.umowy.filter.security.UmowaSecurityFilter;
import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class UmowaList extends AbstractBuilder{

	private final UmowaDao umowaDao;
	private final UmowaSecurityFilter umowaSecurityFilter;

	public UmowaList(final Context context, final UmowaDao umowaDao, final UmowaSecurityFilter umowaSecurityFilter) {
		super(context);
		this.umowaDao = umowaDao;
		this.umowaSecurityFilter = umowaSecurityFilter;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<SimpleUmowaPOJO> umowy = umowaDao.getAllSimpleUmowaPOJOs();
		umowy = umowaSecurityFilter.applyFilter(umowy);
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final  UmowaFilter filter= new UmowaFilter(filterString);
			umowy = filter.applyFilter(umowy);
		}
		
		templatingContext.put("umowy", umowy);
		return super.build(template, embeddedBuildResults);
	}
	
}
