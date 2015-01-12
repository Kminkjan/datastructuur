import java.util.concurrent.Semaphore;

public class VerzamelPiet extends Piet {

	private SintSim sim;

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
	 * @param submitMutex
	 *            The submitMutex, used for submiting for a meeting
	 * @param sim
	 *            This pete's simulation
	 */
	public VerzamelPiet(String name, String color, Semaphore meeting,
			Semaphore wakeSint, Sint sint, Semaphore submitMutex, SintSim sim) {
		/* asserts are handled in the super class */
		super(name, color, meeting, wakeSint, sint, submitMutex);
		assert sim != null;
		this.sim = sim;
	}

	@Override
	public void doTask() {
		// COLLECT WISHLIST
		try {
			System.out.println(name + " does Collecting");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCount() {
		sim.setCollectCount(sim.getCollectCount() + 1);
	}
	
	@Override
	public void subCount() {
		sim.setCollectCount(sim.getBlackCount() - 1);	
	}
}
