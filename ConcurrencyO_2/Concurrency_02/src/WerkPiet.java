import java.util.ArrayList;


public class WerkPiet extends Piet{
	private ArrayList<String> taskList;
	public WerkPiet(String name, String color) {
		super(name, color);
		
	}
	@Override
	public void run() {
		while(!taskList.isEmpty()){
			work(taskList);
		}
	}
	
	public void work(ArrayList<String> tasks){
		for (int i = 0; i < tasks.size(); i++) {
			tasks.remove(i);
		}
	}
	
	public boolean isBlack(){
		return super.color.equals("black");
	}
}
