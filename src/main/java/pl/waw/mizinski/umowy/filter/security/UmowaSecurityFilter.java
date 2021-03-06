package pl.waw.mizinski.umowy.filter.security;

import org.objectledge.context.Context;
import org.objectledge.security.SecurityManager;

import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;
import pl.waw.mizinski.umowy.security.JednostkaGroupRecognizer;

public class UmowaSecurityFilter extends AbstractSecurityFilter<SimpleUmowaPOJO>{


	public UmowaSecurityFilter(Context context,
			SecurityManager securityManager,
			JednostkaGroupRecognizer jednostkaGroupRecognizer) {
		super(context, securityManager, jednostkaGroupRecognizer);
	}
	
}
