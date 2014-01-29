package pl.waw.mizinski.umowy.filter.security;

import java.util.LinkedList;
import java.util.List;

import org.objectledge.context.Context;
import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.SecurityContext;
import org.objectledge.security.SecurityManager;
import org.objectledge.security.exception.DataBackendException;
import org.objectledge.security.exception.UnknownEntityException;
import org.objectledge.security.exception.UnrecognizableResourceGroupException;
import org.objectledge.security.object.Group;
import org.objectledge.security.util.AccessControlList;
import org.objectledge.security.util.GroupSet;

import pl.waw.mizinski.umowy.filter.Filter;

public abstract class AbstractSecurityFilter<E> implements Filter<E> {

	private final Context context;
	private final SecurityManager securityManager;
	private final ResourceGroupRecognizer resourceGroupRecognizer;

	public AbstractSecurityFilter(final Context context, final SecurityManager securityManager, final ResourceGroupRecognizer resourceGroupRecognizer) {
		this.context = context;
		this.securityManager = securityManager;
		this.resourceGroupRecognizer = resourceGroupRecognizer;
	}

	@Override
	public List<E> applyFilter(List<E> list) {
		List<E> ret = new LinkedList<E>();
		for (E e : list) {
			if (checkSecurity(e)) {
				ret.add(e);
			}
		}
		return ret;
	}

	public boolean checkSecurity(E e) {
		try {
			final SecurityContext securityContext = SecurityContext
					.getSecurityContext(context);
			if(isSuperUser(securityContext)){
				return true;
			}
			final AccessControlList acl = securityContext.getUserACL();
			GroupSet groupSet;
			groupSet = resourceGroupRecognizer
					.resourceGroupByObject(e);
			for (Group group : acl.getAllUserGroups()) {
				if (groupSet.contains(group)) {
					return true;
				}
			}
			return false;
		} catch (UnrecognizableResourceGroupException e1) {
			return false;
		}
	}

	private boolean isSuperUser(SecurityContext securityContext) {
		try {
			return securityContext.getUser().equals(securityManager.getSuperuserAccount());
		} catch (DataBackendException | UnknownEntityException e) {
			return false;
		}
	}

}
