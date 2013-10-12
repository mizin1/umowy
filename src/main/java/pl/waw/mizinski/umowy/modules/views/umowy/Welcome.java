package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;

public class Welcome extends AbstractBuilder {

	private JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public Welcome(Context context, JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		super(context);
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
	}

	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		JednostkaOrganizacyjna jednostka = jednostkaOrganizacyjnaDao.getById("Politechnika Warszawska");
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("jednostka", jednostka);
		return super.build(template, embeddedBuildResults);
	}

}
