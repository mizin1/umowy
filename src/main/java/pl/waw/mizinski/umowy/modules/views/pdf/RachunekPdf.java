package pl.waw.mizinski.umowy.modules.views.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.objectledge.context.Context;
import org.objectledge.fop.PdfGenerator;
import org.objectledge.fop.FopService.OutputType;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

import pl.waw.mizinski.umowy.dao.RachunekDao;
import pl.waw.mizinski.umowy.dao.UmowaDao;
import pl.waw.mizinski.umowy.model.RachunekPK;
import pl.waw.mizinski.umowy.model.Umowa;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class RachunekPdf extends AbstractBuilder {

	private final PdfGenerator pdfGenerator;
	private final UmowaDao umowaDao;
	private final RachunekDao rachunekDao;

	public RachunekPdf(final Context context, final PdfGenerator pdfGenerator, final UmowaDao umowaDao, final RachunekDao rachunekDao) {
		super(context);
		this.pdfGenerator = pdfGenerator;
		this.umowaDao = umowaDao;
		this.rachunekDao = rachunekDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		Integer nrRachunku = requestParameters.getInt("nrRachunku");
		String nrUmowy = requestParameters.get("nrUmowy");
		Umowa umowa = umowaDao.getById(nrUmowy);
		templatingContext.put("umowa", umowa);
		templatingContext.put("rachunek", rachunekDao.getById(new RachunekPK(umowa, nrRachunku)));
		try {
			ByteArrayInputStream xmlData = new ByteArrayInputStream(super.build(template, embeddedBuildResults).getBytes());
		    pdfGenerator.dumpData(xmlData, "rachunek", "rachunek.xsl", OutputType.PDF);
		} 
		catch (FOPException | IOException | TransformerException e) {
		    throw new ProcessingException(e);
		}
		return "";
	}
}	
