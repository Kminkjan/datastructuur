import java.util.concurrent.Semaphore;


public class SintSim {
	
	private Semaphore werkoverleg, wakeSint;
	private Piet[] pietArray;
	
	public SintSim () {
		werkoverleg = new Semaphore(0, true);
		wakeSint = new Semaphore(0, true);
		pietArray = new Piet[10];
		Sint sint = new Sint(wakeSint, werkoverleg);
		
		sint.start();
		
		for (int i = 0; i < pietArray.length; i++) {
			pietArray[i] = new WerkPiet("Gijs " + i, "black", werkoverleg, wakeSint);
			pietArray[i].start();
		}
	}
}