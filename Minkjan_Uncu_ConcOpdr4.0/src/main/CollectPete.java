package main;

import akka.actor.ActorRef;

public class CollectPete extends Pete {


    /**
     * Creates a new Piet
     *
     * @param name               The name of the Piet
     * @param color              The color of the Piet
     * @param administrationPete
     */
    CollectPete(String name, String color, ActorRef administrationPete) {
        super("CollectPete " + name, color, administrationPete);
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
