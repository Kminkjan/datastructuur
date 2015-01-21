package main;

import akka.actor.ActorRef;

public class CollectPete extends Pete {

    private final ActorRef adminPete;

    /**
     * Creates a new Piet
     *
     * @param name               The name of the Piet
     * @param color              The color of the Piet
     * @param administrationPete
     */
    public CollectPete(String name, String color, ActorRef administrationPete) {
        super("CollectPete " + name, color);
        assert administrationPete != null : "administrationPete is null";

        this.adminPete = administrationPete;
    }

    @Override
    public void doTask() throws InterruptedException {
        /* COLLECT WISHLIST */
        System.out.println(getPeteName() + " does Collecting");
        Thread.sleep((long) (Math.random() * 8000));
    }

    @Override
    public void applyForMeeting() {
        adminPete.tell(new ApplyMessage(false, super.isBlack()), getSelf());
    }
}
