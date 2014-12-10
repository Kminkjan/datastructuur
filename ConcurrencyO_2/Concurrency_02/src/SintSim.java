import java.util.concurrent.Semaphore;

public class SintSim {

	private Semaphore werkOverleg, verzamelOverleg, wakeSint, blackPete;
	private Piet[] pietArray;
	private Sint sint;
	private final static int PIET_SIZE = 10;
	private int arraySize;
	public SintSim() {
		werkOverleg = new Semaphore(0, true);
		verzamelOverleg = new Semaphore(0, true);
		wakeSint = new Semaphore(0, true);
		blackPete = new Semaphore(0, true);
		pietArray = new Piet[PIET_SIZE];
		this.sint = new Sint(wakeSint, werkOverleg, verzamelOverleg, blackPete);

		sint.start();

		maakZwarteWerkPieten(2);
		maakRodeWerkPieten(3);
		maakVerzamelPieten(5);

	}

	/**
	 * Empty constructor This constructor is used for testing purposes.
	 */
	public SintSim(String test, int arraySize) {
		assert arraySize > 0 : "arraySize is to low";
		assert test !=null : "string is null";
		assert !test.isEmpty(): "string is empty";
		
		if (test.equals("test")) {
			this.arraySize = arraySize;
			werkOverleg = new Semaphore(0, true);
			verzamelOverleg = new Semaphore(0, true);
			wakeSint = new Semaphore(0, true);
			blackPete = new Semaphore(0, true);
			pietArray = new Piet[arraySize];
			this.sint = new Sint(wakeSint, werkOverleg, verzamelOverleg, blackPete);
			sint.start();
		}
	}

	public void maakZwarteWerkPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Gijs " + i, "black", blackPete, wakeSint, this.sint);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if(!(currentLast <0)){
				for(int i = currentLast; i < amount ; i++){
					pietArray[i] = new WerkPiet("Gijs " + i, "black", blackPete, wakeSint, this.sint);
					pietArray[i].start();
				}	
			}
		}
	}

	public void maakRodeWerkPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Gijs " + i, "red", blackPete, wakeSint, this.sint);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if(!(currentLast <0)){
				for(int i = currentLast; i < amount ; i++){
					pietArray[i] = new WerkPiet("Gijs " + i, "red", blackPete, wakeSint, this.sint);
					pietArray[i].start();
				}	
			}
		}
	}

	public void maakVerzamelPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Bob " + i, "black", verzamelOverleg, wakeSint, this.sint);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if(!(currentLast <0)){
				for(int i = currentLast; i < amount ; i++){
					pietArray[i] = new VerzamelPiet("Bob " + i, "black", verzamelOverleg, wakeSint, this.sint);
					pietArray[i].start();
				}	
			}
		}
	}
	private int getCurrentLast(){
		for (int i = 0; i < pietArray.length; i++) {
			if(pietArray[i] == null){
				return i;
			}
		}
		return -1;
	}
	public Piet[] getPieten() {
		return this.pietArray;
	}

	private void stopAllPietThreads() {
		for (int i = 0; i < pietArray.length; i++) {
			pietArray[i] = null;
		}
	}
	public void stopSintThread(){
		sint = null;
	}

	public void simulate() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(80000);
					stopAllPietThreads();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public Sint getSint(){
		return this.sint;
	}	

	private boolean emptyArray() {
		if (pietArray.length == 0) {
			return true;
		}
		return false;
	}
}