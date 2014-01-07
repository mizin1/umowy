package pl.waw.mizinski.umowy.scheduler;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.scheduler.Job;

public class ValveJob extends Job{

	private final Context context;
	private final Valve valve;
	
	public ValveJob(final Context context, final Valve valve) {
		this.context = context;
		this.valve = valve;
	}
	
	@Override
	public void run(String[] arguments) {
		try {
			valve.process(context);
		} catch (ProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
