package test;

import akka.actor.ActorRef;
import main.Message;
import main.WorkPete;

import static junit.framework.TestCase.fail;

/**
 * Created by Kris on 21-1-2015.
 */
public class TestWorkPete extends WorkPete {
    private boolean isDeclined;

    /**
     * Creates a new Piet
     *
     * @param name               The name of the Piet
     * @param color              The color of the Piet
     * @param administrationPete
     */
    public TestWorkPete(String name, String color, ActorRef administrationPete) {
        super(name, color, administrationPete);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message && ((Message) message).getType() == Message.MessageType.DECLINED) {
                /* I have recieved a DECILNED message, this shouldn't happen */
            isDeclined = true;
            fail();
        }
        super.onReceive(message);
    }
}