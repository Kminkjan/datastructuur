package main;

import akka.actor.ActorRef;

public class WorkPete extends Pete {


	/**
	 * Creates a new Piet
	 *
	 * @param name               The name of the Piet
	 * @param color              The color of the Piet
	 * @param administrationPete
	 */
	WorkPete(String name, String color, ActorRef administrationPete) {
		super("WorkPete " + name, color, administrationPete);
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
