package pl.waw.mizinski.umowy.filter.security;

import org.objectledge.context.Context;
import org.objectledge.security.SecurityManager;

import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.security.JednostkaGroupRecognizer;

public class JednostkaSecurityFilter extends AbstractSecurityFilter<JednostkaOrganizacyjna>{

	public JednostkaSecurityFilter(Context context,
			SecurityManager securityManager,
			JednostkaGroupRecognizer jednostkaGroupRecognizer) {
		super(context, securityManager, jednostkaGroupRecognizer);
	}

}
