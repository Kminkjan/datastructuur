

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

public class SintSimTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
/*		Sint Sim Tests */ 
		SintSim sintSim = new SintSim("test", 30);
		assertNotNull(sintSim);
		sintSim.maakRodeWerkPieten(10);
		sintSim.maakZwarteWerkPieten(10);
		sintSim.maakVerzamelPieten(10);
		
//		check if array contains content
		assertNotNull(sintSim.getPieten());
		assertNotNull(sintSim.getPieten()[1]);
		assertEquals(30, sintSim.getPieten().length);
		sintSim.simulate();
	}
	
	@Test(expected = AssertionError.class)
	public void testLowAmountRedPieten(){
		SintSim sintSim = new SintSim("test", 10);
		sintSim.maakRodeWerkPieten(-1);
	}
	@Test(expected = AssertionError.class)
	public void testLowAmountBlackPieten(){
		SintSim sintSim = new SintSim("test", 10);
		sintSim.maakZwarteWerkPieten(-1);
		
	}
	@Test(expected = AssertionError.class)
	public void testLowAmountVerzamelPieten(){
		SintSim sintSim = new SintSim("test", 10);
		sintSim.maakVerzamelPieten(-1);

	}
	@Test
	public void testNoMeeting(){
		SintSim sintSim = new SintSim("test", 3);
		sintSim.maakRodeWerkPieten(1);
		sintSim.maakZwarteWerkPieten(1);
		sintSim.maakVerzamelPieten(1);
		sintSim.simulate();
		
		assertFalse(sintSim.getSint().isVerzameHasHappend());
		assertFalse(sintSim.getSint().isWerkHasHappend());
		sintSim.stopSintThread();
	}
	@Test
	public void testNoMeetingVerzamelOverleg(){
		SintSim sintSim = new SintSim("test", 20);
		sintSim.maakRodeWerkPieten(10);
		sintSim.maakZwarteWerkPieten(10);
		sintSim.simulate();
		
		assertFalse(sintSim.getSint().isVerzameHasHappend());
		sintSim.stopSintThread();
	}
	@Test
	public void testNoMeetingWerkOverleg(){
		SintSim sintSim = new SintSim("test", 20);
		sintSim.maakVerzamelPieten(10);
		sintSim.maakRodeWerkPieten(10);
		sintSim.simulate();
		
		assertFalse(sintSim.getSint().isWerkHasHappend());
		sintSim.stopSintThread();
	}
	@Test
	public void testNoBlackPeteVerzamelOverleg(){
		SintSim sintSim = new SintSim("test", 20);
		sintSim.maakRodeWerkPieten(10);
		sintSim.maakVerzamelPieten(10);
		sintSim.simulate();	
		
		assertFalse(sintSim.getSint().isVerzameHasHappend());
		sintSim.stopSintThread();
	}
}
