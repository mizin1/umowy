package pl.waw.mizinski.umowy.scheduler;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;

public class ExampleValve implements Valve {

	@Override
	public void process(Context arg0) throws ProcessingException {
		int i = 1;
		i += i+1;
	}

}
