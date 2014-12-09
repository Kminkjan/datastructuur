
public class Sint extends Thread  {
	private Meeting meeting;
	public Sint(){
		this.meeting = new Meeting();
	}
	@Override
	public void run() {
		try {
			this.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void report(){
		
	}
}
