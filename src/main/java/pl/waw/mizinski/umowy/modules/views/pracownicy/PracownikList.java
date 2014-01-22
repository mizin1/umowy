package pl.waw.mizinski.umowy.modules.views.pracownicy;

import java.util.List;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.PracownikDao;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.pojo.PracownikImieNazwiskoPOJO;

public class PracownikList extends AbstractBuilder {

	private final PracownikDao pracownikDao;
	
	public PracownikList(Context context, PracownikDao pracownikDao) {
		super(context);
		this.pracownikDao = pracownikDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		List<PracownikImieNazwiskoPOJO> pracownicy = pracownikDao.getAllPracownikImieNazwiskoPOJOs();
		templatingContext.put("pracownicy", pracownicy);
		return super.build(template, embeddedBuildResults);
	}

}
