import java.util.concurrent.Semaphore;

public class VerzamelPiet extends Piet {
	public VerzamelPiet(String name, String color, Semaphore meeting,
			Semaphore wakeSint) {
		super(name, color, meeting, wakeSint);
	}

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
