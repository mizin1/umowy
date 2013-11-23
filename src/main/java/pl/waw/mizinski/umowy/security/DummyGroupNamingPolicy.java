package pl.waw.mizinski.umowy.security;

import org.objectledge.security.GroupNamingPolicy;
import org.objectledge.security.object.AbstractGroup;
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
    private final static GroupSet ALL_GROUPS = new GroupSet();
    
    static 
    {
        // groups can be delcared dynamicaly using some data sources eg. database
        // here is an example only
        ALL_GROUPS.add( new DeclaredResourceGroup("office", "Resources belong to the office"));
        ALL_GROUPS.add( new DeclaredResourceGroup("factory", "Resources belong to the factory"));
        ALL_GROUPS.add( new DeclaredResourceGroup("hr", "Resources belong to the human resources department"));
    }
    
    public GroupSet getAllGroups()
    {
        return ALL_GROUPS;
    }

    @SuppressWarnings("unused")
    public GroupSet getSubGroups(final Group root)
    {
        return GroupSet.emptySet();
    }

    
    static private class DeclaredResourceGroup extends AbstractGroup
    {
        private static final long serialVersionUID = 1L;
        DeclaredResourceGroup(final String name, final String description)
        {
            this.name = name;
            this.description = description;
        }
    }    
}
