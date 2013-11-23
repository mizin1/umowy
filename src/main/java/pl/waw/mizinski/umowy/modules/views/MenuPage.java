package pl.waw.mizinski.umowy.modules.views;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;
import org.objectledge.modules.components.security.menu.SecureMenuCoolmenusParam;

public class MenuPage extends AbstractBuilder {
	
	private static final SecureMenuCoolmenusParam MENU_PARAM = new SecureMenuCoolmenusParam("default");
	
	public MenuPage(Context context) {
		super(context);
		
	}

	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("cparam", MENU_PARAM);
        return super.build(template, embeddedBuildResults);
	}
}
