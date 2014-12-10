import java.util.concurrent.Semaphore;

public class WerkPiet extends Piet {
	public WerkPiet(String name, String color, Semaphore meeting, Semaphore wakeSint, Sint sint) {
		super(name, color, meeting, wakeSint, sint);
	}

	@Override
	public void doTask() {
		// Work
		try {
			System.out.println(name + " does Working");
			this.sleep((long) (Math.random() * 8000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
