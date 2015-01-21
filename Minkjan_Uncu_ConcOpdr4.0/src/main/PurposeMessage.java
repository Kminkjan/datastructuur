package main;

import akka.actor.ActorRef;

import java.util.List;

/**
 * Created by Kris on 14-1-2015.
 */
public final class PurposeMessage extends Message {

    private List<ActorRef> availablePeteList;

    public PurposeMessage(List<ActorRef> availablePeteList) {
        super(MessageType.PURPOSE_MEETING);
        assert availablePeteList != null : "availablePeteList is null";
        assert !availablePeteList.isEmpty() : "availablePeteList is empty";

        this.availablePeteList = availablePeteList;
    }

    public List<ActorRef> getAvailablePeteList() {
        return this.availablePeteList;
    }
}
