package main;

import akka.actor.UntypedActor;
import main.Message.MessageType;

/**
 * The Piet is a helper of the sint, it works and goes to meetings
 *
 * @author Kris & Artemis
 */
public abstract class Pete extends UntypedActor {
    private final String name, color;

    /**
     * Creates a new Piet
     *
     * @param name  The name of the Piet
     * @param color The color of the Piet
     */
    public Pete(String name, String color) {
        assert name != null : "name is null";
        assert !name.isEmpty() : "name is empty";
        assert color != null : "color is null";
        assert !color.isEmpty() : "color is empty";

        this.name = name;
        this.color = color;
    }

    @Override
    public void preStart() throws Exception {
        doTask();
        applyForMeeting();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        assert message != null : "Message is null";
        assert message instanceof Message : "message is not a Message";

        if (message instanceof Message) {
            Message receivedMessage = (Message) message;
            System.out.println(name + " " + receivedMessage);

            switch (receivedMessage.getType()) {
                case ACCEPTED:
                    /* My application is accepted, now wait till i receive an invitation */
                    break;
                case DECLINED:
                    /* I can't */
                    doTask();
                    applyForMeeting();
                    break;
                case INVITE_TO_MEETING:
                    /* Go to the meeting */
                    getSender().tell(new Message(MessageType.ARRIVED_IN_MEETING), getSelf());
                    break;
                case MEETING_DONE:
                    /* Sint tells me the meeting is DONE, i can get back to work */
                    doTask();
                    applyForMeeting();
                    break;
                default:
                    System.out.println("I shouldn't get this type of Messages");
                    break;
            }
        }
    }

    /**
     * Apply for a meeting, should be handled in subclasses
     */
    public abstract void applyForMeeting();

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

    /**
     * Return the name of the Pete
     *
     * @return The name of the pete
     */
    public final String getPeteName() {
        return this.name;
    }
}
