package test;

import akka.actor.ActorRef;
import main.CollectPete;
import main.Message;

import static junit.framework.TestCase.fail;

/**
 * Created by Kris on 21-1-2015.
 */
public class TestCollectPete2 extends CollectPete {
    /**
     * Creates a new Piet
     *
     * @param name               The name of the Piet
     * @param color              The color of the Piet
     * @param administrationPete
     */
    public TestCollectPete2(String name, String color, ActorRef administrationPete) {
        super(name, color, administrationPete);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message && (((Message) message).getType() == Message.MessageType.DECLINED || ((Message) message).getType() == Message.MessageType.INVITE_TO_MEETING)) {
                /* I have recieved a DECILNED message, this shouldn't happen */
            fail();
        }
        super.onReceive(message);
    }
}
