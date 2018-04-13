package hello.util;

import hello.pojo.Activity;
import java.util.logging.Logger;

/**
 *
 */
public class ConversationUrlHandler {

    private static final Logger LOGGER = Logger.getLogger(ConversationUrlHandler.class.getName());

    public static String getReplyUrl(Activity activity) {
        
        String baseUrl = activity.getServiceUrl();

        if (baseUrl.endsWith("/")) {
            // remove trailing slash
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        String url = baseUrl
                + "/v3/conversations/" + activity.getConversation().getId()
                + "/activities/" + activity.getId();

        return url;
    }
    
     public static String createConversationUrl(Activity activity) {
         
         String baseUrl = activity.getServiceUrl();

        if (baseUrl.endsWith("/")) {
            // remove trailing slash
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        String url = baseUrl
                + "/v3/conversations/" + activity.getConversation().getId()
                + "/activities";

        return url;
    }

}
