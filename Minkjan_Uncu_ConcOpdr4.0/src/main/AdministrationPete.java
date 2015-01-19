package main;

import akka.actor.ActorRef;
import main.Message.MessageType;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdministrationPete extends Pete {

    private boolean meetingGoingOn;
    private final ArrayList<ActorRef> peteList;
    private final ActorRef sint;
    private final LinkedList<ActorRef> workerList, collectorList, blackWorkerList;

    AdministrationPete(String name, String color, ActorRef sint) {
        super(name, color);
        this.peteList = new ArrayList<ActorRef>();
        this.sint = sint;
        this.workerList = new LinkedList<ActorRef>();
        this.collectorList = new LinkedList<ActorRef>();
        this.blackWorkerList = new LinkedList<ActorRef>();
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

            System.out.println("Admin " + receivedMessage);

            switch (receivedMessage.getType()) {
                case APPLY_FOR_MEETING:
                    ApplyMessage applyMessage = (ApplyMessage) receivedMessage;

                    if (applyMessage.isWorkPete() && !meetingGoingOn) {
                        if (!meetingGoingOn) {
                        /* WorkPete */
                            if (applyMessage.isBlack()) {
                                blackWorkerList.add(getSender());
                            } else {
                                workerList.add(getSender());
                            }
                            getSender().tell(new Message(MessageType.ACCEPTED),
                                    getSelf());

                            checkIfMeetingPossible();
                        } else {
                            getSender().tell(new Message(MessageType.DECLINED),
                                    getSelf());
                        }

                        checkIfMeetingPossible();
                    } else if (!applyMessage.isWorkPete()) {
                        /* CollectPete */
                        collectorList.add(getSender());

                        getSender().tell(new Message(MessageType.ACCEPTED),
                                getSelf());

                        checkIfMeetingPossible();
                    }
                    break;
                case MEETING_DONE:
                    meetingGoingOn = false;
                    break;
                default:
                    System.out.println("I shouldn't get this type of message: " + receivedMessage.getType());
                    break;
            }
        }
    }

    private boolean checkIfMeetingPossible() {
        System.out.println("worksize: " + workerList.size() + " - blacksize: " + blackWorkerList.size() + " - collectsize: " + collectorList.size() + "\n");

        if (collectorList.size() > 2 && !blackWorkerList.isEmpty()) {
            System.out.println("Admin: start collectoverleg");
            meetingGoingOn = true;
            /* 3+ CollectPete and 1 Black WorkPete */

            ArrayList<ActorRef> tempList = new ArrayList<ActorRef>();
            tempList.addAll(collectorList);
            collectorList.clear();

            /* Add one Black Work Pete */
            tempList.add(blackWorkerList.pop());

            sint.tell(new PurposeMessage((ArrayList<ActorRef>) tempList.clone()), getSelf());

            /* Tell the rest: go back to work */
            for (ActorRef actor : blackWorkerList) {
                actor.tell(new Message(MessageType.DECLINED),
                        getSelf());
            }
            for (ActorRef actor : workerList) {
                actor.tell(new Message(MessageType.DECLINED),
                        getSelf());
            }

            blackWorkerList.clear();
            workerList.clear();

            return true;
        } else if (workerList.size() + blackWorkerList.size() > 2) {
            System.out.println("Admin: start workoverleg");
            meetingGoingOn = true;

            ArrayList<ActorRef> tempList = new ArrayList<ActorRef>();
            tempList.addAll(workerList);
            workerList.clear();
            tempList.addAll(blackWorkerList);
            blackWorkerList.clear();

            sint.tell(new PurposeMessage((ArrayList<ActorRef>) tempList.clone()), getSelf());
            return true;
        }
        return false;
    }

    /**
     * Checks which meeting can be held.
     *
     * @return true, if collectersmeeting can be done, else false; do workmeeting
     */
    private boolean whichMeetingPossible() {


        if (!collectorList.isEmpty() && workerList.size() >= 1) {

            if (isBlack()) {

            }
        }
        return false;
    }

    @Override
    public void applyForMeeting() {

    }


}
