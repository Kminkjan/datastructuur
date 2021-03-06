package main;

import java.util.List;

public class Message {
	public enum MessageType {
		APPLY_FOR_MEETING, INVITE_TO_MEETING, PURPOSE_MEETING, ACCEPTED, DECLINED, MEETING_DONE, ARRIVED_IN_MEETING
	}
	
	private final MessageType type;
	
	public Message(MessageType type) {
		assert type != null : "MessageType is null";

		this.type = type;
	}
	
	public final MessageType getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "" + this.type;
	}
}
