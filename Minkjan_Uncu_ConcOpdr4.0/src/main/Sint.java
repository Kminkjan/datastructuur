package main;

import akka.actor.Actor;
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
    private final ActorRef adminPete;


    public Sint(ActorRef adminPete) {
        petesInMeeting = new ArrayList<ActorRef>();
        this.adminPete = adminPete;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message recievedMessage = (Message) message;

            switch (recievedMessage.getType()) {
                case PURPOSE_MEETING:
                    for (ActorRef actor : ((PurposeMessage) recievedMessage).getAvailablePeteList()) {
                        actor.tell(new Message(Message.MessageType.INVITE_TO_MEETING), getSelf());
                    }
                    break;
                case ARRIVED_IN_MEETING:
                    petesInMeeting.add(getSender());
                    if (true) {
                        Thread.sleep(2000);
                        for (ActorRef actor : petesInMeeting) {
                            actor.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());
                        }
                        adminPete.tell(new Message(Message.MessageType.MEETING_DONE), getSelf());
                    }
                    break;
                case MEETING_DONE:

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
