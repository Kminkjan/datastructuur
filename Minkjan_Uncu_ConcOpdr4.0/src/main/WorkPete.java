package main;

public class WorkPete extends Pete {

	WorkPete(String name, String color) {
		super("WorkPete " + name, color);
	}

	@Override
	public void doTask() throws InterruptedException {
		// Work
		System.out.println(getPeteName() + " does Working");
		Thread.sleep((long) (Math.random() * 8000));

	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
