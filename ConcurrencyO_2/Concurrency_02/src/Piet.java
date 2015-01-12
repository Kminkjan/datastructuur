import java.util.concurrent.Semaphore;

/**
 * The Piet is a helper of the sint, it works and goes to meetings
 * @author Kris & Artemis
 *
 */
public abstract class Piet extends Thread {
	protected String name;
	private String color;
	private Semaphore meeting, wakeSint, submitMutex;
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
			Sint sint, Semaphore submitMutex) {
		assert name != null : "name is null";
		assert !name.isEmpty() : "name is empty";
		assert color != null : "color is null";
		assert !color.isEmpty() : "color is empty";
		assert meeting != null : "meeting Semaphore is null";
		assert wakeSint != null : "meeting Semaphore is null";
		assert sint != null : "sint is null";
		assert submitMutex != null : "submitMutex = null";
		
		this.name = name;
		this.color = color;
		this.meeting = meeting;
		this.wakeSint = wakeSint;
		this.submitMutex = submitMutex;
		this.sint = sint;
	}

	@Override
	public void run() {
		while (true) {
			try {
				doTask();
				/* Check if the sint is busy with a meeting */
				submitMutex.acquire();
				if (!sint.isBusy()) {
					addCount();
					submitMutex.release();
					/* Notify the Sint the situation has changed */
					wakeSint.release();
					System.out.println(name + " waiting for meeting");
					/* Try to acquire / get in the meeting */
					meeting.acquire();
					
					// TODO sub the count
				} else {
					System.out.print(name + " they don't want me! ");
					submitMutex.release();
				}
				
				// TODO sub the count
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Add this pete to the Simulation's count
	 */
	public abstract void addCount();
	public abstract void subCount();

	/**
	 * Do the task this Pete has.
	 */
	public abstract void doTask();

	/**
	 * @return true if the Pete is black.
	 */
	public boolean isBlack() {
		return color.equals("black");
	}
	
	public void domeeting() {
		// tryAquire meetingspots()
		// if gelukt && if meetingspots.permits == 0
	}
}
