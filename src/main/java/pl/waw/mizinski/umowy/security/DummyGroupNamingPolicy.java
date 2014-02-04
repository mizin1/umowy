package pl.waw.mizinski.umowy.security;

import org.objectledge.security.GroupNamingPolicy;
import org.objectledge.security.object.Group;
import org.objectledge.security.util.GroupSet;

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
