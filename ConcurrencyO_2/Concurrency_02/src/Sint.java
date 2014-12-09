import java.util.concurrent.Semaphore;

public class Sint extends Thread {
	private Semaphore wakeSint, meeting;

	public Sint(Semaphore wakeSint, Semaphore meeting) {
		this.wakeSint = wakeSint;
		this.meeting = meeting;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Sint engage meeting");
				this.sleep(1000);
				wakeSint.acquire();
				if (meeting.getQueueLength() > 3) {

					System.out.println("DO MEETING");

					this.sleep(400);

					meeting.release(3);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void report() {

	}
}
