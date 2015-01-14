package main;

import java.util.ArrayList;
import java.util.List;

import main.Message.MessageType;
import akka.actor.ActorRef;

public class AdministrationPete extends Pete {

    private boolean meetingGoingOn;
    private final ArrayList<ActorRef> peteList;
    private final ActorRef sint;
    private List<ActorRef> workerList;
    private List<ActorRef> collectorList;
    
    AdministrationPete(String name, String color, ActorRef sint) {
        super(name, color);
        this.peteList = new ArrayList<ActorRef>();
        this.sint = sint;
        this.workerList = new ArrayList<ActorRef>();
        this.collectorList = new ArrayList<ActorRef>();
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
                        if(((ApplyMessage) receivedMessage).isWorkPete()){
                        	workerList.add(getSender());
                        }else{
                        	collectorList.add(getSender());
                        }
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

		
		if(!collectorList.isEmpty() && workerList.size()>=1){
	
				if(isBlack()){
					
			}
		}


		return false;
	}

	@Override
	public void applyForMeeting() {
		// TODO Auto-generated method stub
		
	}


}
