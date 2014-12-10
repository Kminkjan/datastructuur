import java.util.concurrent.Semaphore;


public class SintSim {
	
	private Semaphore werkOverleg, verzamelOverleg, wakeSint;
	private Piet[] pietArray;
	
	public SintSim () {
		werkOverleg = new Semaphore(0, true);
		verzamelOverleg = new Semaphore(0, true);
		wakeSint = new Semaphore(0, true);
		pietArray = new Piet[10];
		Sint sint = new Sint(wakeSint, werkOverleg, verzamelOverleg);
		
		sint.start();
		
		for (int i = 0; i < 5; i++) {
			pietArray[i] = new WerkPiet("Gijs " + i, "black", werkOverleg, wakeSint);
			pietArray[i].start();
		}
		
		for (int i = 5; i < 5; i++) {
			pietArray[i] = new VerzamelPiet("Bob " + i, "black", verzamelOverleg, wakeSint);
			pietArray[i].start();
		}
	}
}