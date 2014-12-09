import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Meeting {
	private String type;
	private ArrayList<Piet> pieten;
	public Meeting() {
//		Semaphore semaphore = new Semaphore(permits);
		pieten = new ArrayList<Piet>();
	}
	public String getType(){
		return this.type;
	}
	public void dismissMeeting(){
		this.type ="";
		assert !pieten.isEmpty(): "Pieten is already empty";
	}
	public void startMeeting(String type){
		this.type = type;
//		Acquire, hier mag niemand meer joinen wanneer hij gestart is.
//		sleep(? seconden)
//		Wanneer de meeting voorbij is mogen er weer pieten aamelden voor een meeting.
		
	}
}
