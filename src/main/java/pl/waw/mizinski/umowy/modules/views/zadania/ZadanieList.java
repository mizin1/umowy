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

import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.filter.ZadanieFilter;
import pl.waw.mizinski.umowy.filter.security.ZadanieSecurityFilter;
import pl.waw.mizinski.umowy.pojo.ZadaniePOJO;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_R"})
})
public class ZadanieList extends AbstractBuilder {

	private final ZadanieDao zadanieDao;
	private final ZadanieSecurityFilter zadanieSecurityFilter;

	public ZadanieList(final Context context, final ZadanieDao zadanieDao,
			final ZadanieSecurityFilter zadanieSecurityFilter) {
		super(context);
		this.zadanieDao = zadanieDao;
		this.zadanieSecurityFilter = zadanieSecurityFilter;
	}
	
	@Override
	public String build(final Template template, final String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<ZadaniePOJO> zadania = zadanieDao.getAllZadaniePOJOs();
		zadania = zadanieSecurityFilter.applyFilter(zadania);
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final ZadanieFilter filter = new ZadanieFilter(filterString);
			zadania = filter.applyFilter(zadania);
		}
		
		templatingContext.put("zadania", zadania);
		return super.build(template, embeddedBuildResults);
	}
	
}
