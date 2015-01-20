package main;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kris on 14-1-2015.
 */
public class Sint extends UntypedActor {

    private final List<ActorRef> petesInMeeting;
    private int expectedPetes = 0;
    private ActorRef adminPete;


    public Sint() {
        this.petesInMeeting = new ArrayList<ActorRef>();
        this.adminPete = adminPete;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message receivedMessage = (Message) message;

            System.out.println("Sint: " + receivedMessage.getType());

            switch (receivedMessage.getType()) {
                case PURPOSE_MEETING:
                    this.adminPete = getSender();
                    for (ActorRef actor : ((PurposeMessage) receivedMessage).getAvailablePeteList()) {
                        actor.tell(new Message(Message.MessageType.INVITE_TO_MEETING), getSelf());
                        ++expectedPetes;
                    }
                    break;
                case ARRIVED_IN_MEETING:
                    petesInMeeting.add(getSender());

                    System.out.println("Sint: size: " + petesInMeeting.size() + " expected: " + expectedPetes);

                    if (petesInMeeting.size() == expectedPetes) {

                        System.out.println("\nSint: !!! MEETING TIME !!! \n");

                        /* Start the meeting */
                        Thread.sleep(2000);
                        for (ActorRef actor : petesInMeeting) {
                            actor.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());
                        }
                        adminPete.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());

                        /* Everyone has gone */
                        petesInMeeting.clear();
                        expectedPetes = 0;
                    }
                    break;
                default:
                    break;
            }
        }
        uiltjeKnappen();
    }

    private void uiltjeKnappen() {
        System.out.println("Sint knaps an owl");
    }
}
