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

import pl.waw.mizinski.umowy.dao.UmowaDao;

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class UmowaPdf extends AbstractBuilder {

	private final PdfGenerator pdfGenerator;
	private final UmowaDao umowaDao;

	public UmowaPdf(final Context context, final PdfGenerator pdfGenerator,
			final UmowaDao umowaDao) {
		super(context);
		this.pdfGenerator = pdfGenerator;
		this.umowaDao = umowaDao;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		String nrUmowy = requestParameters.get("nrUmowy");
		templatingContext.put("umowa", umowaDao.getById(nrUmowy));
		try {
			ByteArrayInputStream xmlData = new ByteArrayInputStream(super.build(template, embeddedBuildResults).getBytes());
		    pdfGenerator.dumpData(xmlData, "umowa", "umowa.xsl", OutputType.PDF);
		} 
		catch (FOPException | IOException | TransformerException e) {
		    throw new ProcessingException(e);
		}
		return "";
	}
}
