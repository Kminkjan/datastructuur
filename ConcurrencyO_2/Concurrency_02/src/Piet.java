import java.util.concurrent.Semaphore;

public abstract class Piet extends Thread {
	protected String name;
	private String color;
	private Semaphore meeting, wakeSint;
	private Sint sint;

	Piet(String name, String color, Semaphore meeting, Semaphore wakeSint,
			Sint sint) {
		this.name = name;
		this.color = color;
		this.meeting = meeting;
		this.wakeSint = wakeSint;
		this.sint = sint;
	}

	@Override
	public void run() {
		while (true) {
			try {
				doTask();
				if (!sint.isBusy()) {
					/* Notify the Sint the situation has changed */
					wakeSint.release();
					System.out.println(name + " waiting for meeting");
					/* Try to acquire / get in the meeting */
					meeting.acquire();
				} else {
					System.out.println(name + " they don't want me!");
				}
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
