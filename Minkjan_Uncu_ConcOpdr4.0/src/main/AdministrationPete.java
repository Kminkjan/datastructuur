package main;

import akka.actor.ActorRef;
import main.Message.MessageType;

import java.util.ArrayList;

public class AdministrationPete extends Pete {

    private boolean meetingGoingOn;
    private final ArrayList<ActorRef> peteList;
    private final ActorRef sint;

    AdministrationPete(String name, String color, ActorRef administrationPete, ActorRef sint, ActorRef adminPete) {
        super(name, color, administrationPete);
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
	
	private boolean checkIfMeetingPossible() {
		if(peteList.size() >=3 && !meetingGoingOn){
//			restrictions to meet?? 3 - 4 pete?
			return true;
		}
		return false;
	}
	
	/** Checks which meeting can be held.
	 * @return true, if collectersmeeting can be done, else false; do workmeeting
	 */
	private boolean whichMeetingPossible(){
		int collectors = 0;
		int workers = 0;
		WorkPete blackWorker;
		// loop through the list
		for(ActorRef p : peteList){
			// if instance of workpete add 1 to the counter and check if it is a black pete.
			if (p instanceof WorkPete){
				++workers;
				// if it is a black pete save it in blackWorker.
				if(((WorkPete) p).isBlack() && blackWorker = null){
					blackWorker = p;
				}
			}else{
//				must be instance of CollectorPete
				++collectors;
			}
		}
//		when there are collectors and workers check if there is an Black WorkPete.
		if(collectors != 0 && workers >=1){
			if(blackWorker!=null){
//			if there is an blackworker, an collectorsmeeting can be held.
				return true;
			}
		}
		return false;
	}

}
