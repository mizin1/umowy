package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.RachunekDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.Umowa;

public class Rachunki extends AbstractBuilder {

	private final RachunekDao rachunekDao;
	private final UmowaDao umowaDao;
	
	public Rachunki(final Context context, final RachunekDao rachunekDao, final UmowaDao umowaDao) {
		super(context);
		this.rachunekDao = rachunekDao;
		this.umowaDao = umowaDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		if (requestParameters.isDefined("nrUmowy")) {
			String nrUmowy = requestParameters.get("nrUmowy");
			Umowa umowa = umowaDao.getById(nrUmowy);
			templatingContext.put("rachunki", rachunekDao.getRachunekListByUmowa(umowa));
			templatingContext.put("nrUmowy", nrUmowy);
			return super.build(template, embeddedBuildResults);
		} else {
			throw new ProcessingException("Undefined nrUmowy!");
		}
	}
}
