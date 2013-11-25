package pl.waw.mizinski.umowy.modules.views.pracownicy;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.PanstwoDao;

public class DodawaniePracownika extends AbstractBuilder {

	private final PanstwoDao panstwoDao;
	
	public DodawaniePracownika(final Context context, final PanstwoDao panstwoDao) {
		super(context);
		this.panstwoDao = panstwoDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("panstwa", panstwoDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
}
