package test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import main.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PietTest {

    private static final int TIMEOUT = 160000;

    @Test
    public void test() {
        /* Test 3 workpetes, noone should be declined */
        /* Test 1 black work and 3 collect, no-one should be declined */
    	
    	// TODO moet nog nagekeken en getest worden.
    	
        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < 10; i++) {
            ActorRef temp = system.actorOf(Props.create(WorkPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        for (int i = 0; i < 10; i++) {
            ActorRef temp = system.actorOf(Props.create(TestCollectPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnlyCollectPetes() {
        /* Should never get an invite */

        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < 3; i++) {
            ActorRef temp = system.actorOf(Props.create(TestCollectPete2.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnlyCollectMeeting() {
        /* Only collectmeetings can happen, and no-one gets declined */

        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        ActorRef temp = system.actorOf(Props.create(TestWorkPete.class, "nr" + 99, "black", admin));
        actorList.add(temp);

        for (int i = 0; i < 3; i++) {
            temp = system.actorOf(Props.create(TestCollectPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testNoMeetingHappens() {
        /* 2 WorkPetes and 2 CollectPetes, nothing should happen */

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(TestSint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        ActorRef temp1 = system.actorOf(Props.create(WorkPete.class, "nr" + 1, "black", admin));
        ActorRef temp2 = system.actorOf(Props.create(WorkPete.class, "nr" + 2, "black", admin));
        ActorRef temp3 = system.actorOf(Props.create(CollectPete.class, "nr" + 3, "black", admin));
        ActorRef temp4 = system.actorOf(Props.create(CollectPete.class, "nr" + 4, "black", admin));

    }

    @Test
    public void testCollectNeverDeclined() {
        /* A 4 WorkPetes and 3 CollectPetes, but the CollectPetes should never get declined */

        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < 4; i++) {
            ActorRef temp = system.actorOf(Props.create(WorkPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        for (int i = 0; i < 3; i++) {
            ActorRef temp = system.actorOf(Props.create(TestCollectPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnlyWorkPetes() {
        /* Only 3 WorkPetes, the petes should never be DECLINED */
        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));

        for (int i = 0; i < 3; i++) {
            ActorRef temp = system.actorOf(Props.create(TestWorkPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testNoWorkmeeting(){
        /* Only 1 WorkPete, There should only be collect meetings */
        List<ActorRef> actorList = new ArrayList<ActorRef>();

        ActorSystem system = ActorSystem.create("HIApp");
        ActorRef sint = system.actorOf(Props.create(Sint.class));
        // administration pete.
        ActorRef admin = system.actorOf(Props.create(AdministrationPete.class, "Admin", "black", sint));
        // the only work pete.
        ActorRef workPete = system.actorOf(Props.create(TestWorkPete.class, "nr" + 1, "black", admin));
        // create all the collect petes
        for (int i = 0; i < 10; i++) {
            ActorRef temp = system.actorOf(Props.create(TestCollectPete.class, "nr" + i, "black", admin));
            actorList.add(temp);
        }

        
        try {
            Thread.sleep(TIMEOUT);
            system.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}