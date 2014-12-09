import java.util.ArrayList;

public class Meeting {
	private String type;
	private ArrayList<Piet> pieten;
	public Meeting() {
		this.type = type;
		pieten = new ArrayList<Piet>();
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void dismissMeeting(){
		this.type ="";
		assert !pieten.isEmpty(): "Pieten is already empty";
	}
	public void startMeeting(){
		
	}
}
