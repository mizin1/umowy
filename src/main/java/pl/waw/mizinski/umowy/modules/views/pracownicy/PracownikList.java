package pl.waw.mizinski.umowy.modules.views.pracownicy;

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

import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.filter.PracownikFilter;
import pl.waw.mizinski.umowy.pojo.PracownikLiczbaUmowPOJO;

@AccessConditions({
    @AccessCondition(auth = true, permissions = {"PRACOWNIK_R"}),
    @AccessCondition(auth = true, permissions = {"PRACOWNIK_F"})
})
public class PracownikList extends AbstractBuilder {

	private final PracownikDao pracownikDao;
	
	public PracownikList(Context context, PracownikDao pracownikDao) {
		super(context);
		this.pracownikDao = pracownikDao;
	}

	@Override
	public String build(final Template template, final String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		List<PracownikLiczbaUmowPOJO> pracownicy = pracownikDao.getAllPracownikLiczbaUmowPOJOs();
		
		if (requestParameters.isDefined("filter")) {
			final String filterString = requestParameters.get("filter");
			final PracownikFilter filter = new PracownikFilter(filterString);
			pracownicy = filter.applyFilter(pracownicy);
		}
		
		templatingContext.put("pracownicy", pracownicy);
		return super.build(template, embeddedBuildResults);
	}

}
