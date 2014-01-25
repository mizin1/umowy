package pl.waw.mizinski.umowy.modules.views.umowy;

import java.util.List;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.filter.UmowaFilter;
import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;

public class UmowaList extends AbstractBuilder{

	private final UmowaDao umowaDao;

	public UmowaList(final Context context, final UmowaDao umowaDao) {
		super(context);
		this.umowaDao = umowaDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<SimpleUmowaPOJO> umowy = umowaDao.getAllSimpleUmowaPOJOs();
	
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final  UmowaFilter filter= new UmowaFilter(filterString);
			umowy = filter.applyFilter(umowy);
		}
		
		templatingContext.put("umowy", umowy);
		return super.build(template, embeddedBuildResults);
	}
	
}
