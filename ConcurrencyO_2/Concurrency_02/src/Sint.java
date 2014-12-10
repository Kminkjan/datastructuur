import java.util.concurrent.Semaphore;

public class Sint extends Thread {
	private Semaphore wakeSint, werkOverleg, verzamelOverleg;

	public Sint(Semaphore wakeSint, Semaphore werkOverleg, Semaphore verzamelOverleg) {
		assert wakeSint != null : "wakeSint is null";
		assert wakeSint.availablePermits() == 0 : "wakeSint has initial permits";
		assert werkOverleg != null : "wakeSint is null";
		assert werkOverleg.availablePermits() == 0 : "werkOverleg has initial permits";
		assert verzamelOverleg != null : "wakeSint is null";
		assert verzamelOverleg.availablePermits() == 0 : "verzamelOverleg has initial permits";
		
		this.wakeSint = wakeSint;
		this.werkOverleg = werkOverleg;
		this.verzamelOverleg = verzamelOverleg;
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.sleep(1000);
				wakeSint.acquire(); /* Sleep till the situation has changed */
				if (verzamelOverleg.getQueueLength() > 3 && false /* piet is black */ ) {
					System.out.println("DO VerzamelOverleg");
					this.sleep(400);
					verzamelOverleg.release(3); /* Invite 3 werkPieten to the meeting */
				} else if (werkOverleg.getQueueLength() > 3) {
					System.out.println("DO WerkOverleg");
					this.sleep(400);
					werkOverleg.release(3); /* Invite 3 werkPieten to the meeting */
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
