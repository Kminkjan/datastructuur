package main;

/**
 * Created by Kris on 14-1-2015.
 */
public final class ApplyMessage extends Message {

    private final boolean isWorkPete;

    public ApplyMessage(boolean isWorkPete) {
        super(MessageType.APPLY_FOR_MEETING);
        this.isWorkPete = isWorkPete;
    }

    /**
     * Check if the applicant is a {@link main.WorkPete}.
     * @return True if it is a {@link main.WorkPete}, false if it is a {@link main.CollectPete}
     */
    public boolean isWorkPete() {
        return this.isWorkPete;
    }
}
