package pl.waw.mizinski.umowy.modules.views.platnosci;

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

import pl.waw.mizinski.umowy.dao.PlatnoscDao;
import pl.waw.mizinski.umowy.filter.PlatnoscFilter;
import pl.waw.mizinski.umowy.pojo.PlatnoscPOJO;

@AccessConditions({
	 @AccessCondition(permissions = {"PLATNOSC_R"})
})
public class Platnosci extends AbstractBuilder{

	private final PlatnoscDao platnoscDao;
	
	public Platnosci(final Context context, final PlatnoscDao platnoscDao) {
		super(context);
		this.platnoscDao = platnoscDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<PlatnoscPOJO> platnosci = platnoscDao.getAllPlatnoscPOJOs();
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final PlatnoscFilter filter = new PlatnoscFilter(filterString);
			platnosci = filter.applyFilter(platnosci);
		}
		
		templatingContext.put("platnosci", platnosci);
		return super.build(template, embeddedBuildResults);
	}
	
}
