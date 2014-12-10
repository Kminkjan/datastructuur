import java.util.concurrent.Semaphore;

public class Sint extends Thread {
	private Semaphore wakeSint, werkOverleg, verzamelOverleg, blackPete;
	private boolean isBusy, verzameHasHappend, werkHasHappend;
	

	public Sint(Semaphore wakeSint, Semaphore werkOverleg,
			Semaphore verzamelOverleg, Semaphore blackPete) {
		assert wakeSint != null : "wakeSint is null";
		assert wakeSint.availablePermits() == 0 : "wakeSint has initial permits";
		assert werkOverleg != null : "wakeSint is null";
		assert werkOverleg.availablePermits() == 0 : "werkOverleg has initial permits";
		assert verzamelOverleg != null : "wakeSint is null";
		assert verzamelOverleg.availablePermits() == 0 : "verzamelOverleg has initial permits";
		assert blackPete != null : "blackPete is null";
		assert blackPete.availablePermits() == 0 : "blackPete has initial permits";

		this.wakeSint = wakeSint;
		this.werkOverleg = werkOverleg;
		this.verzamelOverleg = verzamelOverleg;
		this.blackPete = blackPete;
	}
	
	

	@Override
	public void run() {
		while (true) {
			try {
				doSintStuff();
				wakeSint.acquire(); /* Sleep till the situation has changed */
				if (verzamelOverleg.getQueueLength() > 3
						&& blackPete.hasQueuedThreads()) {
					
					werkOverleg.release(werkOverleg.getQueueLength());
					if (blackPete.getQueueLength() > 1) {
						blackPete.release(blackPete.getQueueLength() - 1);
					}
					verzameHasHappend = true;
					doMeeting();
					
					/* Invite verzamelPieten to the meeting */
					verzamelOverleg.release(verzamelOverleg.getQueueLength()); 
					/* invite a black pete to the meeting */
					blackPete.release();
				} else if ((werkOverleg.getQueueLength() + blackPete
						.getQueueLength()) > 2) {
					
					System.out.println("DO WerkOverleg");
					
					werkHasHappend = true;
					doMeeting();
					
					blackPete.release(blackPete.getQueueLength());
					/* Invite 3 werkPieten to the meeting */
					werkOverleg.release(werkOverleg.getQueueLength()); 
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void doSintStuff() {
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doMeeting() {
		try {
			isBusy = true;
			this.sleep(8000);
			System.out.println("DO VerzamelOverleg");
			isBusy = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isBusy() {
		return isBusy;
	}

	public boolean isVerzameHasHappend() {
		return verzameHasHappend;
	}

	public boolean isWerkHasHappend() {
		return werkHasHappend;
	}
}
