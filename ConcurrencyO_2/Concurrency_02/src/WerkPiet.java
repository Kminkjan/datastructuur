import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WerkPiet extends Piet {
	private ArrayList<String> taskList;
	private Semaphore meeting, wakeSint;

	public WerkPiet(String name, String color, Semaphore s, Semaphore w) {
		super(name, color);
		this.meeting = s;
		this.wakeSint = w;

	}

	@Override
	public void run() {
		while (true) {
			try {
				doTask();
				System.out.println(name + " waiting for meeting");
				// meetingQ.acquire();
				wakeSint.release();
				meeting.acquire();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ACUIRE SINTOVERLEG || KIJK NAAR VERZAMELOVERLEG
			// IF ZWART EN GENOEG VERZAMELPIETEN
			// meeting.aquire();
			// AQUIRE
			// MEETING
			// DO TASK
		}
	}

	public boolean isBlack() {
		return super.color.equals("black");
	}

	@Override
	public void doTask() {
		try {
			System.out.println(name + " does task");
			this.sleep((long) (Math.random() * 8000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
