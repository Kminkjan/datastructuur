import java.util.ArrayList;


public class VerzamelPiet extends Piet{
	private ArrayList<String> list;
	private Sint sint;
	public VerzamelPiet(String name, String color, Sint sint) {
		super(name, color);
		this.sint = sint;
		this.list = new ArrayList<String>();
	}
	@Override
	public void run() {
	}
	
	public ArrayList<String> getList(){
		return this.list;
	}
	public void addTask(String s){
		this.list.add(s);
	}
	
}
