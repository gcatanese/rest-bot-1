package hello;

import hello.pojo.Activity;
import java.util.logging.Logger;

/**
 *
 */
public class ConversationUrlHandler {

    private static final Logger LOGGER = Logger.getLogger(ConversationUrlHandler.class.getName());

    public static String getReplyUrl(Activity activity) {

        String url = activity.getServiceUrl() + "/"
                + "/v3/conversations/" + activity.getConversation().getId()
                + "/activities/" + activity.getReplyToId();

        

        return url;
    }

}
