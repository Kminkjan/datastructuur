import java.util.concurrent.Semaphore;


public class SintSim {
	
	private Semaphore werkOverleg, verzamelOverleg, wakeSint, blackPete;
	private Piet[] pietArray;
	
	private final static int PIET_SIZE = 10;
	
	public SintSim () {
		werkOverleg = new Semaphore(0, true);
		verzamelOverleg = new Semaphore(0, true);
		wakeSint = new Semaphore(0, true);
		blackPete = new Semaphore(0, true);
		pietArray = new Piet[PIET_SIZE];
		Sint sint = new Sint(wakeSint, werkOverleg, verzamelOverleg, blackPete);
		
		sint.start();
		
		for (int i = 0; i < 2; i++) {
			pietArray[i] = new WerkPiet("Gijs " + i, "black", blackPete, wakeSint, sint);
			pietArray[i].start();
		}
		
		for (int i = 2; i < 5; i++) {
			pietArray[i] = new WerkPiet("Gijs " + i, "red", werkOverleg, wakeSint, sint);
			pietArray[i].start();
		}
		
		for (int i = 5; i < 10; i++) {
			pietArray[i] = new VerzamelPiet("Bob " + i, "black", verzamelOverleg, wakeSint, sint);
			pietArray[i].start();
		}
	}
}