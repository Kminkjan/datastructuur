import java.util.concurrent.Semaphore;

// TODO cannot use queue length

public class Sint extends Thread {
	private Semaphore wakeSint, werkOverleg, verzamelOverleg, blackPete, submitMutex;
	private boolean isBusy, verzameHasHappend, werkHasHappend;
	private SintSim sim;
	private int expectedWork, expectedCollect;

	/**
	 * Creates a sint
	 * 
	 * @param wakeSint
	 *            The Semaphore that wakes the Sint
	 * @param werkOverleg
	 *            The werk Semaphore
	 * @param verzamelOverleg
	 *            The verzamel Semaphore
	 * @param blackPete
	 *            The blackPete Semaphore
	 */
	public Sint(Semaphore wakeSint, Semaphore werkOverleg,
			Semaphore verzamelOverleg, Semaphore blackPete, Semaphore submitMutex, SintSim sim) {
		assert wakeSint != null : "wakeSint is null";
		assert wakeSint.availablePermits() == 0 : "wakeSint has initial permits";
		assert werkOverleg != null : "wakeSint is null";
		assert werkOverleg.availablePermits() == 0 : "werkOverleg has initial permits";
		assert verzamelOverleg != null : "wakeSint is null";
		assert verzamelOverleg.availablePermits() == 0 : "verzamelOverleg has initial permits";
		assert blackPete != null : "blackPete is null";
		assert blackPete.availablePermits() == 0 : "blackPete has initial permits";
		assert submitMutex != null : "submitMutex = null";
		assert sim != null : "sim = null";
 
		this.wakeSint = wakeSint;
		this.werkOverleg = werkOverleg;
		this.verzamelOverleg = verzamelOverleg;
		this.blackPete = blackPete;
		this.submitMutex = submitMutex;
		this.sim = sim;
	}

	@Override
	public void run() {
		while (true) {
			try {
				doSintStuff();
				wakeSint.acquire(); /* Sleep till the situation has changed */
				
				
				submitMutex.acquire();
				if (sim.getCollectCount() > 3
						&& sim.getBlackCount() > 0) {

					werkOverleg.release(sim.getWorkCount());

					/*
					 * If there is more then one black Pete available, release
					 * all but one
					 */
					if (sim.getBlackCount() > 1) {
						blackPete.release(sim.getBlackCount() - 1);
					}

					verzameHasHappend = true;
					System.out.println("DO VerzamelOverleg");
					doMeeting();

					/* Invite verzamelPieten to the meeting */
					verzamelOverleg.release(sim.getCollectCount());
					/* invite a black pete to the meeting */
					blackPete.release();
					
					sim.setBlackCount(0);
					sim.setCollectCount(0);
					sim.setWorkCount(0);

				} else if ((sim.getWorkCount() + sim.getBlackCount()) > 2) {

					System.out.println("DO WerkOverleg");

					werkHasHappend = true;
					doMeeting();

					blackPete.release(sim.getBlackCount());
					/* Invite 3 werkPieten to the meeting */
					werkOverleg.release(sim.getWorkCount());
					sim.setBlackCount(0);
					sim.setWorkCount(0);
				} else {
					submitMutex.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Do the stuff the sint does
	 */
	private void doSintStuff() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simulate the meeting
	 */
	private void doMeeting() {
		try {
			/* Set my status on busy, i'm in a meeting */
			isBusy = true;
			submitMutex.release();
			
			Thread.sleep(8000);
			
			submitMutex.acquire();
			isBusy = false;
			submitMutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isBusy() {
		return isBusy;
	}

	
	/* THE FOLLOWING METHODS ARE TEST METHODS */
	public boolean isVerzameHasHappend() {
		return verzameHasHappend;
	}

	public boolean isWerkHasHappend() {
		return werkHasHappend;
	}
	
	public void doCollectMeeting() {
		// MeetingIsGoingOn.aquire()
		// everyone is here aquire
		
		// meeting.releaseall()
		// MeetingIsGoingOn.release()
	}
	
	public void doWorkMeeting() {
		Semaphore meeting = new Semaphore(0, true);
		Semaphore invitation = new Semaphore(0, true);
		Semaphore workMeeting = new Semaphore(3, true);
		
		/* The meeting has started, no-one can enter */
		meeting.drainPermits();
		
		assert meeting.availablePermits() == 0: "Meeting has started and petes can still apply";
		
		// releaseAll werkPieten
		// First 3 aquiren
		while(true) {
			invitation.aquire();
			if (/* Everyone is here */) {
				break;
			}
		}
		// Invitation.release() : Het kan beginnen; iedereen is er
		
		/* Simulate the meeting */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Release the workPete's that did this meeting
		workMeeting.release(3);
		
		meeting.release(20); // TODO the all pete count
	}
}
