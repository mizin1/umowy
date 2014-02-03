package pl.waw.mizinski.umowy.modules.views.pracownicy;

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
import pl.waw.mizinski.umowy.dao.SkladkaDao;


@AccessConditions({
    @AccessCondition(auth = true, permissions = {"PRACOWNIK_R"})
})
public class PracownikDetails extends AbstractBuilder {

	private final PracownikDao pracownikDao;
	private final SkladkaDao skladkaDao;
	
	public PracownikDetails(final Context context, final PracownikDao pracownikDao, final SkladkaDao skladkaDao) {
		super(context);
		this.pracownikDao = pracownikDao;
		this.skladkaDao = skladkaDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		if (requestParameters.isDefined("id")) {
			Long id = requestParameters.getLong("id");
			templatingContext.put("pracownik", pracownikDao.getById(id));
			templatingContext.put("skladki", skladkaDao.getSkladkaOrderedList());
			return super.build(template, embeddedBuildResults);
		} else {
			throw new ProcessingException("Undefined pracownik id!");
		}
	}
}
