import java.util.concurrent.Semaphore;

public abstract class Piet extends Thread {
	String name, color;
	Semaphore meeting, wakeSint;

	Piet(String name, String color, Semaphore meeting, Semaphore wakeSint) {
		this.name = name;
		this.color = color;
		this.meeting = meeting;
		this.wakeSint = wakeSint;
	}

	@Override
	public void run() {
		while (true) {
			try {
				doTask();
				wakeSint.release(); /* Notify the Sint the situation has changed */
				System.out.println(name + " waiting for meeting");
				meeting.acquire(); /* Try to acquire / get in the meeting */
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Do the task this Piet has.
	 */
	public abstract void doTask();

	/**
	 * @return true if the Piet is black.
	 */
	public boolean isBlack() {
		return color.equals("black");
	}
}
