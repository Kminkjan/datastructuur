package test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import main.AdministrationPete;
import main.Message;
import main.Sint;
import main.WorkPete;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PietTest {

    @Test
    public void test() {
        /* Test 3 workpetes, noone should be declined */
        /* Test 1 black work and 3 collect, no-one should be declined */
    }

    @Test
    public void testOnlyWorkPetes() {
        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < 3; i++) {
            ActorRef temp = system.actorOf(Props.create(TestWorkPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

    }

    private class TestWorkPete extends WorkPete {
        private boolean isDeclined;

        /**
         * Creates a new Piet
         *
         * @param name               The name of the Piet
         * @param color              The color of the Piet
         * @param administrationPete
         */
        TestWorkPete(String name, String color, ActorRef administrationPete) {
            super(name, color, administrationPete);
        }

        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof Message && ((Message) message).getType() == Message.MessageType.DECLINED) {
                isDeclined = true;
            }
            super.onReceive(message);
        }
    }

}