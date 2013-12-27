package pl.waw.mizinski.umowy.modules.actions.intake;

import org.objectledge.context.Context;
import org.objectledge.intake.IntakeContext;
import org.objectledge.intake.IntakeTool;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.MVCContext;

public class IntakeSample implements Valve {

	@Override
	public void process(Context context) throws ProcessingException {
		final IntakeTool intake = IntakeContext.getIntakeContext(context).getIntakeTool();
		final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
		if (intake.isAllValid()) {
			// OK
		} else {
			templatingContext.put("errorResult", "An error occured");
			templatingContext.put("errorResultData", "Check form for details");
			MVCContext.getMVCContext(context).setView("intake.Example");
		}
	}

}
