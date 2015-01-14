package main;

import java.util.List;

public class Message {
	public enum MessageType {
		APPLY_FOR_MEETING, INVITE_TO_MEETING, PURPOSE_MEETING, ACCEPTED, DECLINED, MEETING_DONE
	}
	
	private final MessageType type;
	
	public Message(MessageType type) {
		this.type = type;
	}
	
	public Message(List<Pete> peteList) {
		this.type = MessageType.PURPOSE_MEETING;
	}
	
	public MessageType getType() {
		return this.type;
	}

}
