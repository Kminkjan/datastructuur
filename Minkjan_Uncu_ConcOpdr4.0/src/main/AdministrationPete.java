package main;

import java.util.ArrayList;
import java.util.List;

import main.Message.MessageType;
import akka.actor.ActorRef;

public class AdministrationPete extends Pete {

	private boolean meetingGoingOn;
	private final List<ActorRef> peteList;
	
	AdministrationPete(String name, String color, ActorRef administrationPete) {
		super(name, color, administrationPete);
		this.peteList = new ArrayList<ActorRef>();
	}

	@Override
	public void doTask() throws InterruptedException {
		System.out.println(getPeteName() + " is Administrating");
		Thread.sleep(1000);
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Message) {
			Message recievedMessage = (Message) message;
			
			switch (recievedMessage.getType()) {
			case APPLY_FOR_MEETING:
				if (!meetingGoingOn) {
					getSender().tell(new Message(MessageType.ACCEPTED),
							getSelf());
					peteList.add(getSender());
					if (checkIfMeetingPossible()) {
						
					}
				} else {
					getSender().tell(new Message(MessageType.DECLINED),
							getSelf());
				}
				break;
			default:
				break;
			}
		}
	}
	
	private boolean checkIfMeetingPossible() {
		
		return false;
	}
}
