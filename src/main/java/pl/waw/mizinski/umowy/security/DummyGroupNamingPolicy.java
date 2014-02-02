package pl.waw.mizinski.umowy.security;

import org.objectledge.security.GroupNamingPolicy;
import org.objectledge.security.object.Group;
import org.objectledge.security.util.GroupSet;

/**
 * Example implementation of {@link GroupNamingPolicy} interface.
 *
 * <p>Created on 2009-03-24</p>
 * @author <a href="mailto:mgolebsk@elka.pw.edu.pl">Marcin Golebski</a>
 * @version $Id: DummyGroupNamingPolicy.java,v 1.1 2009-03-26 15:10:51 mgolebsk Exp $
 */
public class DummyGroupNamingPolicy implements GroupNamingPolicy
{
 
    public GroupSet getAllGroups()
    {
    	return GroupSet.emptySet();
    }

    public GroupSet getSubGroups(final Group root)
    {
        return GroupSet.emptySet();
    }

   
}
