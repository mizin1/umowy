package pl.waw.mizinski.umowy.filter.security;

import org.objectledge.context.Context;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.SecurityManager;

import pl.waw.mizinski.umowy.pojo.ZadaniePOJO;

public class ZadanieSecurityFilter extends AbstractSecurityFilter<ZadaniePOJO>{

	public ZadanieSecurityFilter(Context context, SecurityManager securityManager, ResourceGroupRecognizer resourceGroupRecognizer) {
		super(context, securityManager, resourceGroupRecognizer);
	}

}
