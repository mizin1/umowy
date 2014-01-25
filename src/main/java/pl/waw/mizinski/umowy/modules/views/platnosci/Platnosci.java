package pl.waw.mizinski.umowy.modules.views.platnosci;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.PlatnoscDao;

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
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("platnosci", platnoscDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
	
}
