//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.util;

import hello.pojo.Activity;
import hello.pojo.ChannelAccount;
import hello.pojo.Conversation;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author gcatanese
 */
@Service
public class BotCore {

    private static final Logger LOGGER = Logger.getLogger(BotCore.class.getName());

    // create output Activity
    public Activity prepareOutput(Activity input) {

        Activity output = new Activity();
        output.setConversation(input.getConversation());

        output.setChannelId(input.getChannelId());
        
        output.setTimestamp(input.getTimestamp());
        output.setLocalTimestamp(input.getLocalTimestamp());

        ChannelAccount from = new ChannelAccount();
        from.setId("99");
        from.setName("BOT");
        output.setFrom(from);

        ChannelAccount recipient = new ChannelAccount();
        recipient.setId(input.getFrom().getId());
        recipient.setName(input.getFrom().getName());
        output.setRecipient(recipient);

        output.setReplyToId(input.getId());

        output.setServiceUrl(input.getServiceUrl());

        output.setType("message");

        return output;

    }

    public Conversation createConversation() {
        String url = "https://smba.trafficmanager.net/apis/v3/conversations";

        Conversation conversation = new Conversation();

        conversation.setTopicName("New chat");

        ChannelAccount bot = new ChannelAccount();
        bot.setId("msteams");
        conversation.setBot(bot);

        ChannelAccount member = new ChannelAccount();
        member.setId("29:18Pp6Dllk24UGXU_9T4DUUKTN_F1emNWv8mWO1w39Izc");
        member.setName("Giuseppe Catanese");

        ChannelAccount[] members = {member};
        conversation.setMembers(members);

        Activity activity = new Activity();
        activity.setChannelId("msteams");
        activity.setText("Heeeeeeeeeeeeyyyyyyyyy!");
        conversation.setActivity(activity);

        return conversation;

    }
    
}
