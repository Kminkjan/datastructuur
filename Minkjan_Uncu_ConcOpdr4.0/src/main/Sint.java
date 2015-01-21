package main;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kris on 14-1-2015.
 */
public class Sint extends UntypedActor {

    /**
     * The list of petes that are IN the meeting
     */
    private final List<ActorRef> petesInMeeting;
    private int expectedPetes = 0;
    private ActorRef adminPete;

    /**
     * Creates the Sint
     */
    public Sint() {
        this.petesInMeeting = new ArrayList<ActorRef>();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message receivedMessage = (Message) message;

            System.out.println("Sint: " + receivedMessage.getType());

            switch (receivedMessage.getType()) {
                case PURPOSE_MEETING:
                    this.adminPete = getSender();

                    /* Tell everyone that they're invited to the Meeting */
                    for (ActorRef actor : ((PurposeMessage) receivedMessage).getAvailablePeteList()) {
                        actor.tell(new Message(Message.MessageType.INVITE_TO_MEETING), getSelf());
                        ++expectedPetes;
                    }

                    break;
                case ARRIVED_IN_MEETING:
                    petesInMeeting.add(getSender());

                    System.out.println("Sint: size: " + petesInMeeting.size() + " expected: " + expectedPetes);

                    /* Everyone is in the meeting */
                    if (petesInMeeting.size() == expectedPetes) {

                        System.out.println("\nSint: \"The meeting can start!\" \n");

                        /* Start the meeting */
                        Thread.sleep(2000);

                        /* Tell every attending Pete to resume with their wokr */
                        for (ActorRef actor : petesInMeeting) {
                            actor.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());
                        }

                        /* Everyone has gone, reset the data */
                        petesInMeeting.clear();
                        expectedPetes = 0;

                        /* Tell the admin the meeting has finished */
                        adminPete.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());
                    }
                    break;
                default:
                    System.out.println("SINT: I shouldn't get this type of MESSAGE: " + receivedMessage.getType());
                    break;
            }
        }
        uiltjeKnappen();
    }

    /**
     * When the Sint has nothing to do, he slacks of a little
     */
    private void uiltjeKnappen() {
        System.out.println("Sint knaps an owl");
    }
}
