package pl.waw.mizinski.umowy.dao;

import org.objectledge.hibernate.DAOTestAbstract;

public class DaoTestBase extends DAOTestAbstract {

	public DaoTestBase() {
		super("jdbc:hsqldb:mem:dao-db;shutdown=true", "sa", "", "src/test/resources/sql",
				"org.objectledge.example.dao");
	}

}
