package pl.waw.mizinski.umowy.modules.views.pracownicy;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;

public class DodawaniePracownika extends AbstractBuilder {

	private final PanstwoDao panstwoDao;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	
	public DodawaniePracownika(final Context context, final PanstwoDao panstwoDao, final UrzadSkarbowyDao urzadSkarbowyDao) {
		super(context);
		this.panstwoDao = panstwoDao;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
	}
	
	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("panstwa", panstwoDao.getAll());
		templatingContext.put("urzedySkarobowe", urzadSkarbowyDao.getAll());
		return super.build(template, embeddedBuildResults);
	}
}
