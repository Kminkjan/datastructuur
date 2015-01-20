package main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by Kris on 14-1-2015.
 */
public class Simulation {

    private static final int WORKPETE_COUNT = 3;
    private static final int COLLECTPETE_COUNT = 3;

    public Simulation() {
        ActorSystem system  = ActorSystem.create("HIApp");
        ActorRef sint= system.actorOf(Props.create(Sint.class));
        ActorRef admin= system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < WORKPETE_COUNT; i++) {
            ActorRef temp = system.actorOf(Props.create(WorkPete.class, "nr" + i, "black", admin));
        }

        for (int i = WORKPETE_COUNT; i < WORKPETE_COUNT + COLLECTPETE_COUNT; i++) {
            ActorRef temp = system.actorOf(Props.create(CollectPete.class, "nr" + i, "black", admin));
        }
    }
}
