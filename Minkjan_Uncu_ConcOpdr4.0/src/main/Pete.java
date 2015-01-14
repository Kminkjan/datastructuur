package main;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import main.Message.MessageType;

/**
 * The Piet is a helper of the sint, it works and goes to meetings
 *
 * @author Kris & Artemis
 */
public abstract class Pete extends UntypedActor {
    private final String name;
    private final String color;
    private final ActorRef administrationPete;

    /**
     * Creates a new Piet
     *
     * @param name  The name of the Piet
     * @param color The color of the Piet
     */
    Pete(String name, String color, ActorRef administrationPete) {
        assert name != null : "name is null";
        assert !name.isEmpty() : "name is empty";
        assert color != null : "color is null";
        assert !color.isEmpty() : "color is empty";
        assert administrationPete != null : "administrationPete is null";

        this.name = name;
        this.color = color;
        this.administrationPete = administrationPete;
    }

    @Override
    public void preStart() throws Exception {
        doTask();
        administrationPete.tell(new Message(MessageType.APPLY_FOR_MEETING), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message receivedMessage = (Message) message;

            switch (receivedMessage.getType()) {
                case ACCEPTED:
                    /* My application is accepted, now wait till i receive an invitation */
                    break;
                case DECLINED:
                    /* I can't */
                    doTask();
                    administrationPete.tell(new Message(MessageType.APPLY_FOR_MEETING), getSelf());
                    break;
                case INVITE_TO_MEETING:
                    /* Go to the meeting */
                    getSender().tell(new Message(MessageType.ARRIVED_IN_MEETING), getSelf());
                    break;
                case MEETING_DONE:
                    doTask();
                    administrationPete.tell(new Message(MessageType.APPLY_FOR_MEETING), getSelf());
                    break;
                default:
                    System.out.println("I shouldn't get this type of Messages");
                    break;
            }
        }
    }


    /**
     * Do the task this Pete has.
     *
     * @throws InterruptedException
     */
    public abstract void doTask() throws InterruptedException;

    /**
     * @return true if the Pete is black.
     */
    public final boolean isBlack() {
        return color.equals("black");
    }

    public final String getPeteName() {
        return this.name;
    }
}
