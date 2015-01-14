package main;

import akka.actor.ActorRef;
import main.Message.MessageType;

import java.util.ArrayList;

public class AdministrationPete extends Pete {

    private boolean meetingGoingOn;
    private final ArrayList<ActorRef> peteList;
    private final ActorRef sint;

    AdministrationPete(String name, String color, ActorRef sint) {
        super(name, color);
        this.peteList = new ArrayList<ActorRef>();
        this.sint = sint;
    }

    @Override
    public void doTask() throws InterruptedException {
        System.out.println(getPeteName() + " is Administrating");
        Thread.sleep(1000);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message receivedMessage = (Message) message;

            switch (receivedMessage.getType()) {
                case APPLY_FOR_MEETING:
                    if (!meetingGoingOn) {
                        getSender().tell(new Message(MessageType.ACCEPTED),
                                getSelf());
                        peteList.add(getSender());
                        if (checkIfMeetingPossible()) {
                            meetingGoingOn = true;

						/* TODO is deze clone goed? */
                            sint.tell(new PurposeMessage((ArrayList<ActorRef>) peteList.clone()), getSelf());
                        }
                    } else {
                        getSender().tell(new Message(MessageType.DECLINED),
                                getSelf());
                    }
                    break;
                case MEETING_DONE:
                    meetingGoingOn = false;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void applyForMeeting() {
        /* DO nothing */
    }

    private boolean checkIfMeetingPossible() {

        return false;
    }
}
