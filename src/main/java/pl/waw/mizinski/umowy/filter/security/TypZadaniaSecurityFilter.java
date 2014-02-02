package pl.waw.mizinski.umowy.filter.security;

import org.objectledge.context.Context;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.SecurityManager;

import pl.waw.mizinski.umowy.pojo.TypZadaniaPOJO;

public class TypZadaniaSecurityFilter extends AbstractSecurityFilter<TypZadaniaPOJO> {

	public TypZadaniaSecurityFilter(Context context, SecurityManager securityManager, ResourceGroupRecognizer resourceGroupRecognizer) {
		super(context, securityManager, resourceGroupRecognizer);
	}

}
