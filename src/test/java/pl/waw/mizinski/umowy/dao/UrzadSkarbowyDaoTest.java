package pl.waw.mizinski.umowy.dao;

import java.util.List;

import pl.waw.mizinski.umowy.model.UrzadSkarbowy;



public class UrzadSkarbowyDaoTest extends DaoTestBase{
	
	private final UrzadSkarbowyDao urzadSkarbowyDao = new UrzadSkarbowyDao(context);
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		executeScripts("urzad-skarbowy.sql");
	}
	
	public void testGetAll() throws Exception{	
		List<UrzadSkarbowy> all = urzadSkarbowyDao.getAll();
		assertEquals(2, all.size());
	}
	
	public void testGetUrzadSkarobowyByNazwa() throws Exception{	
		UrzadSkarbowy urzadSkarbowy = urzadSkarbowyDao
				.getById("Urzad Skarbowy w Grójcu");
		assertEquals("Urzad Skarbowy w Grójcu", urzadSkarbowy.getNazwa());
		assertEquals("Grójec", urzadSkarbowy.getMiejscowosc());
		assertEquals("grójecka", urzadSkarbowy.getUlica());
		assertEquals("1", urzadSkarbowy.getNrDomu());
		assertNull(urzadSkarbowy.getNrMieszkania());
		assertEquals("12-345", urzadSkarbowy.getKodPocztowy());
		assertEquals("Grójec", urzadSkarbowy.getPoczta());
	}
	
	
}
