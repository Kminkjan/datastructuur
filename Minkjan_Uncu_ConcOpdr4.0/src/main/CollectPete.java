package main;

public class CollectPete extends Pete {

	CollectPete(String name, String color) {
		super("CollectPete " + name, color);
	}

	@Override
	public void doTask() throws InterruptedException {
		// COLLECT WISHLIST
		System.out.println(getPeteName() + " does Collecting");
		Thread.sleep(1000);
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
	}
}
