package pl.waw.mizinski.umowy.modules.views.zadania;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.TypZadaniaDao;

public class TypyZadan extends AbstractBuilder{

	private final TypZadaniaDao typZadaniaDao;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public TypyZadan(final Context context, final TypZadaniaDao typZadaniaDao, JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		super(context);
		this.typZadaniaDao = typZadaniaDao;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
	}

	
	@Override
	public String build(Template template, String embeddedBuildResults)
			throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("typy",typZadaniaDao.getAll());
		templatingContext.put("jednostki",jednostkaOrganizacyjnaDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
}
