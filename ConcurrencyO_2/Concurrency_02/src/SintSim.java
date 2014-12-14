import java.util.concurrent.Semaphore;

public class SintSim {

	private Semaphore werkOverleg, verzamelOverleg, wakeSint, blackPete,
			submitMutex;
	private Piet[] pietArray;
	private Sint sint;
	private final static int PIET_SIZE = 10;
	
	/**
	 * Pete administration
	 */
	private int blackCount, workCount, collectCount;

	/**
	 * Default SintSim constructor
	 */
	public SintSim() {
		werkOverleg = new Semaphore(0, true);
		verzamelOverleg = new Semaphore(0, true);
		wakeSint = new Semaphore(0, true);
		blackPete = new Semaphore(0, true);
		this.submitMutex = new Semaphore(1);
		pietArray = new Piet[PIET_SIZE];
		this.sint = new Sint(wakeSint, werkOverleg, verzamelOverleg, blackPete,
				submitMutex, this);

		for (int i = 0; i < 5; i++) {
			pietArray[i] = new VerzamelPiet("ver" + i, "black",
					verzamelOverleg, wakeSint, sint, submitMutex, this);
		}
		for (int i = 5; i < 8; i++) {
			pietArray[i] = new WerkPiet("werkB" + i, "black", blackPete,
					wakeSint, sint, submitMutex, this);
		}
		for (int i = 8; i < 10; i++) {
			pietArray[i] = new WerkPiet("werkR" + i, "red", werkOverleg,
					wakeSint, sint, submitMutex, this);
		}
	}

	/**
	 * Starts simulation the simulation indefinitely
	 */
	public void startSimulatingForever() {
		sint.start();
		for (int i = 0; i < pietArray.length; i++) {
			pietArray[i].start();
		}
	}

	// ------ NOTE: THE FOLLOWING METHODS ARE FOR TESTING ----------

	/**
	 *  constructor This constructor is used for testing purposes.
	 */
	public SintSim(String test, int arraySize) {
		assert arraySize > 0 : "arraySize is to low";
		assert test != null : "string is null";
		assert !test.isEmpty() : "string is empty";

		if (test.equals("test")) {
			werkOverleg = new Semaphore(0, true);
			verzamelOverleg = new Semaphore(0, true);
			wakeSint = new Semaphore(0, true);
			blackPete = new Semaphore(0, true);
			submitMutex = new Semaphore(1);
			pietArray = new Piet[arraySize];
			this.sint = new Sint(wakeSint, werkOverleg, verzamelOverleg,
					blackPete, submitMutex, this);
			sint.start();
		}
	}

	public void maakZwarteWerkPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Gijs " + i, "black", blackPete,
						wakeSint, this.sint, submitMutex, this);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if (!(currentLast < 0)) {
				for (int i = currentLast; i < amount; i++) {
					pietArray[i] = new WerkPiet("Gijs " + i, "black",
							blackPete, wakeSint, this.sint, submitMutex, this);
					pietArray[i].start();
				}
			}
		}
	}

	public void maakRodeWerkPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Gijs " + i, "red", blackPete,
						wakeSint, this.sint, submitMutex, this);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if (!(currentLast < 0)) {
				for (int i = currentLast; i < amount; i++) {
					pietArray[i] = new WerkPiet("Gijs " + i, "red", blackPete,
							wakeSint, this.sint, submitMutex, this);
					pietArray[i].start();
				}
			}
		}
	}

	public void maakVerzamelPieten(int amount) {
		assert amount > 0 : "amount is to low";
		if (emptyArray()) {
			for (int i = 0; i < amount; i++) {
				pietArray[i] = new WerkPiet("Bob " + i, "black",
						verzamelOverleg, wakeSint, this.sint, submitMutex, this);
				pietArray[i].start();
			}
		} else {
			int currentLast = getCurrentLast();
			if (!(currentLast < 0)) {
				for (int i = currentLast; i < amount; i++) {
					pietArray[i] = new VerzamelPiet("Bob " + i, "black",
							verzamelOverleg, wakeSint, this.sint, submitMutex,
							this);
					pietArray[i].start();
				}
			}
		}
	}

	private int getCurrentLast() {
		for (int i = 0; i < pietArray.length; i++) {
			if (pietArray[i] == null) {
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

	public void stopSintThread() {
		sint = null;
	}

	/**
	 * Simulates the simulation for 30 seconds
	 */
	public void simulate() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(30000);
					stopAllPietThreads();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Sint getSint() {
		return this.sint;
	}

	private boolean emptyArray() {
		if (pietArray.length == 0) {
			return true;
		}
		return false;
	}

	public int getBlackCount() {
		return blackCount;
	}

	public int getWorkCount() {
		return workCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setBlackCount(int blackCount) {
		this.blackCount = blackCount;
	}

	public void setWorkCount(int workCount) {
		this.workCount = workCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
}