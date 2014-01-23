package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.SkladkaPracownikaDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;

public class UmowaDetails extends AbstractBuilder {
	
	private final UmowaDao umowaDao;
	private final SkladkaPracownikaDao skladkaPracownikaDao;
	
	public UmowaDetails(final Context context, final UmowaDao umowaDao, final SkladkaPracownikaDao skladkaPracownikaDao) {
		super(context);
		this.umowaDao = umowaDao;
		this.skladkaPracownikaDao = skladkaPracownikaDao;
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
}
