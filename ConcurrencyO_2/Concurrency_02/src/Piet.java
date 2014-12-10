import java.util.concurrent.Semaphore;

/**
 * The Piet is a helper of the sint, it works and goes to meetings
 * @author Kris & Artemis
 *
 */
public abstract class Piet extends Thread {
	protected String name;
	private String color;
	private Semaphore meeting, wakeSint;
	private Sint sint;

	/**
	 * Creates a new Piet
	 * @param name	 The name of the Piet
	 * @param color	The color of the Piet
	 * @param meeting	The meeting Semaphore
	 * @param wakeSint	The Sint waking Semaphore
	 * @param sint		The almighty Sint
	 */
	Piet(String name, String color, Semaphore meeting, Semaphore wakeSint,
			Sint sint) {
		assert name != null : "name is null";
		assert !name.isEmpty() : "name is empty";
		assert color != null : "color is null";
		assert !color.isEmpty() : "color is empty";
		assert meeting != null : "meeting Semaphore is null";
		assert wakeSint != null : "meeting Semaphore is null";
		assert sint != null : "sint is null";
		
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
