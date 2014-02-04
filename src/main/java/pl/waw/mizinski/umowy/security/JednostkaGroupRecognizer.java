package pl.waw.mizinski.umowy.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.objectledge.security.ResourceGroupRecognizer;
import org.objectledge.security.SecurityManager;
import org.objectledge.security.exception.DataBackendException;
import org.objectledge.security.exception.UnrecognizableResourceGroupException;
import org.objectledge.security.object.Group;
import org.objectledge.security.util.GroupSet;

import pl.waw.mizinski.umowy.dao.JednostkaOrganizacyjnaDao;
import pl.waw.mizinski.umowy.model.JednostkaOrganizacyjna;
import pl.waw.mizinski.umowy.pojo.SimpleUmowaPOJO;
import pl.waw.mizinski.umowy.pojo.TypZadaniaPOJO;
import pl.waw.mizinski.umowy.pojo.ZadaniePOJO;

public class JednostkaGroupRecognizer implements ResourceGroupRecognizer {

	private final SecurityManager securityManager;
	private final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao;
	
	public JednostkaGroupRecognizer(final SecurityManager securityManager, final JednostkaOrganizacyjnaDao jednostkaOrganizacyjnaDao) {
		this.securityManager = securityManager;
		this.jednostkaOrganizacyjnaDao = jednostkaOrganizacyjnaDao;
	}

	final protected static List<Class<?>> RECOGNIZED_OBJECTS = Collections
			.unmodifiableList(Arrays
					.<Class<?>> asList(JednostkaOrganizacyjna.class, SimpleUmowaPOJO.class, ZadaniePOJO.class, TypZadaniaPOJO.class) );

	@Override
	public GroupSet resourceGroupByObject(Object object) throws UnrecognizableResourceGroupException {
		try {
			if (object instanceof JednostkaOrganizacyjna){
				JednostkaOrganizacyjna jednostkaOrganizacyjna = (JednostkaOrganizacyjna) object;
	            return recognizeByJednostkaOrganizacyjna(jednostkaOrganizacyjna);
			} else if (object instanceof SimpleUmowaPOJO){
				SimpleUmowaPOJO pojo = (SimpleUmowaPOJO) object;
				String nazwaJednostki = pojo.getJednostkaOrganizacyjna();
				JednostkaOrganizacyjna jednostkaOrganizacyjna= jednostkaOrganizacyjnaDao.getById(nazwaJednostki);
				return recognizeByJednostkaOrganizacyjna(jednostkaOrganizacyjna);
			} else if (object instanceof ZadaniePOJO){
				ZadaniePOJO pojo = (ZadaniePOJO) object;
				String nazwaJednostki = pojo.getJednostkaOrganizacyjna();
				JednostkaOrganizacyjna jednostkaOrganizacyjna= jednostkaOrganizacyjnaDao.getById(nazwaJednostki);
				return recognizeByJednostkaOrganizacyjna(jednostkaOrganizacyjna);
			} else if (object instanceof TypZadaniaPOJO){
				TypZadaniaPOJO pojo = (TypZadaniaPOJO) object;
				String nazwaJednostki = pojo.getJednostkaOrganizacyjna();
				JednostkaOrganizacyjna jednostkaOrganizacyjna= jednostkaOrganizacyjnaDao.getById(nazwaJednostki);
				return recognizeByJednostkaOrganizacyjna(jednostkaOrganizacyjna);
			} 
		} catch (DataBackendException e) {
			throw new RuntimeException(e);
		}
		 throw new UnrecognizableResourceGroupException("Cannot recognize " +
	                "object: " + object.getClass().getCanonicalName());
	}

	private GroupSet recognizeByJednostkaOrganizacyjna(JednostkaOrganizacyjna jednostkaOrganizacyjna) throws DataBackendException {
		GroupSet groupSet = new GroupSet();
		while(jednostkaOrganizacyjna != null) {
			Group group = securityManager.getGroupByName(jednostkaOrganizacyjna.getNazwa());
			groupSet.add(group);
			jednostkaOrganizacyjna = jednostkaOrganizacyjna.getJednostkaNadrzedna();
		}
		return groupSet;
	}

	@Override
	public List<Class<?>> registerRecognizedObjects() {
		return RECOGNIZED_OBJECTS;
	}

}
