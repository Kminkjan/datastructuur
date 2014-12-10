import java.util.concurrent.Semaphore;

public class VerzamelPiet extends Piet {

	/**
	 * Creates a new Piet
	 * 
	 * @param name
	 *            The name of the Piet
	 * @param color
	 *            The color of the Piet
	 * @param meeting
	 *            The meeting Semaphore
	 * @param wakeSint
	 *            The Sint waking Semaphore
	 * @param sint
	 *            The almighty Sint
	 */
	public VerzamelPiet(String name, String color, Semaphore meeting,
			Semaphore wakeSint, Sint sint) {
		/* asserts are handled in the super class */
		super(name, color, meeting, wakeSint, sint);
	}

	@Override
	public void doTask() {
		// COLLECT WISHLIST
		try {
			System.out.println(name + " does Collecting");
			this.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
