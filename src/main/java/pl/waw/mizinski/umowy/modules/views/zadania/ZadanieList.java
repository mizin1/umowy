package pl.waw.mizinski.umowy.modules.views.zadania;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.ZadanieDao;

@AccessConditions({
	 @AccessCondition(permissions = {"ZADANIE_R"})
})
public class ZadanieList extends AbstractBuilder {

	private final ZadanieDao zadanieDao;

	public ZadanieList(Context context, ZadanieDao zadanieDao) {
		super(context);
		this.zadanieDao = zadanieDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("zadania",zadanieDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
	
}
