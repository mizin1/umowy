package pl.waw.mizinski.umowy.modules.views.umowy;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.StatusPracownikaDao;
import pl.waw.mizinski.umowy.dao.ZadanieDao;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.StatusPracownika;
import pl.waw.mizinski.umowy.model.Zadanie;

public class Welcome extends AbstractBuilder {

	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	private final ZadanieDao zadanieDao;
	private final PanstwoDao panstwoDao;
	private final StatusPracownikaDao statusPracownikaDao;

	public Welcome(Context context, JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao, ZadanieDao zadanieDao,
			PanstwoDao panstwoDao, StatusPracownikaDao statusPracownikaDao) {
		super(context);
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
		this.zadanieDao = zadanieDao;
		this.panstwoDao = panstwoDao;
		this.statusPracownikaDao = statusPracownikaDao;
	}

	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		JednostkaOrganizacyjna jednostka = jednostkaOrganizacyjnaDao.getById("Politechnika Warszawska");
		Zadanie zadanie = zadanieDao.getById(1L);
		Panstwo panstwo = panstwoDao.getById("PL");
		StatusPracownika statusPracownika = statusPracownikaDao.getById("student");
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		templatingContext.put("jednostka", jednostka);
		templatingContext.put("zadanie", zadanie);
		templatingContext.put("panstwo", panstwo);
		templatingContext.put("status", statusPracownika);
		return super.build(template, embeddedBuildResults);
	}

}
