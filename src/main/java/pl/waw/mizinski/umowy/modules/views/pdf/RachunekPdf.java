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

@AccessConditions({
	 @AccessCondition(permissions = {"UMOWA_R"})
})
public class RachunekPdf extends AbstractBuilder {

	private final PdfGenerator pdfGenerator;

	public RachunekPdf(Context context, final PdfGenerator pdfGenerator) {
		super(context);
		this.pdfGenerator = pdfGenerator;
	}

	@Override
	public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
		RequestParameters requestParameters = RequestParameters.getRequestParameters(context);
		TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		String nrRachunku = requestParameters.get("nrRachunku");
		String nrUmowy = requestParameters.get("nrUmowy");
		templatingContext.put("nrUmowy", nrUmowy);
		templatingContext.put("nrRachunku", nrRachunku);
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
