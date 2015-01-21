package main;

import static junit.framework.TestCase.fail;

/**
 * Created by Kris on 21-1-2015.
 */
public class TestSint extends Sint {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message && ((Message)message).getType() == Message.MessageType.PURPOSE_MEETING) {
            fail();
        }
        super.onReceive(message);
    }
}
