import java.util.concurrent.Semaphore;

public class WerkPiet extends Piet {
	public WerkPiet(String name, String color, Semaphore m, Semaphore w, Sint sint) {
		super(name, color, m, w, sint);
	}

	@Override
	public void doTask() {
		try {
			System.out.println(name + " does Working");
			this.sleep((long) (Math.random() * 8000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
