package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.security.util.GroupSet;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;
import org.objectledge.web.mvc.pipeline.GroupSecurityChecking;

import pl.waw.mizinski.umowy.dao.SkladkaPracownikaDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class UmowaDetails extends AbstractBuilder implements GroupSecurityChecking {
	
	private final UmowaDao umowaDao;
	private final SkladkaPracownikaDao skladkaPracownikaDao;
	private final ResourceGroupRecognizer resourceGroupRecognizer;
	
	public UmowaDetails(final Context context, final UmowaDao umowaDao, final SkladkaPracownikaDao skladkaPracownikaDao,
			final ResourceGroupRecognizer resourceGroupRecognizer) {
		super(context);
		this.umowaDao = umowaDao;
		this.skladkaPracownikaDao = skladkaPracownikaDao;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		if (requestParameters.isDefined("nrUmowy")) {
			String nrUmowy = requestParameters.get("nrUmowy");
			Umowa umowa = umowaDao.getById(nrUmowy);
			templatingContext.put("umowa", umowa);
			templatingContext.put("skladki", skladkaPracownikaDao.getSkladkiByUmowa(umowa));
			return super.build(template, embeddedBuildResults);
		} else {
			throw new ProcessingException("Undefined nr umowy!");
		}
	}
	
	@Override
	public GroupSet getResourceGroup(Context context) throws ProcessingException {
		final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		final String nrUmowy = requestParameters.get("nrUmowy");
		final Umowa umowa = umowaDao.getById(nrUmowy);
		return resourceGroupRecognizer.resourceGroupByObject(umowa.getJednostkaOrganizacyjna());
	}
}
