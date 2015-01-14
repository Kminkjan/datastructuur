package main;

import akka.actor.ActorRef;

public class WorkPete extends Pete {

	private final ActorRef adminPete;

	/**
	 * Creates a new Piet
	 *
	 * @param name               The name of the Piet
	 * @param color              The color of the Piet
	 * @param administrationPete
	 */
	WorkPete(String name, String color, ActorRef administrationPete) {
		super("WorkPete " + name, color);
		assert administrationPete != null : "administrationPete is null";

		this.adminPete = administrationPete;
	}

	@Override
	public void doTask() throws InterruptedException {
		// Work
		System.out.println(getPeteName() + " does Working");
		Thread.sleep((long) (Math.random() * 8000));

	}

	@Override
	public void applyForMeeting() {
		adminPete.tell(new ApplyMessage(true), getSelf());
	}
}
