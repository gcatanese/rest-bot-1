//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.util;

import hello.pojo.Activity;
import hello.pojo.ChannelAccount;
import hello.pojo.Conversation;
import hello.pojo.ConversationAccount;
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

    public Conversation createConversation(String url) {

        Conversation conversation = new Conversation();
        
        conversation.setTopicName("New chat");

        ChannelAccount bot = new ChannelAccount();
        bot.setId("99");
        bot.setName("BOT");
        conversation.setBot(bot);

        ChannelAccount member = new ChannelAccount();
        member.setId("29:1_lbAh47KNZHntCEuYBnV6iy1Csvef1OJqZEuuMOU-cxHWuZ_2drhFKLq1HqVumWiibH7Kwz8Fg0GOGho9UvhmA");
        member.setName("Giuseppe Catanese");

        ChannelAccount[] members = {member};
        conversation.setMembers(members);

        Activity activity = new Activity();
        
        ConversationAccount conversationAccount = new ConversationAccount();
        conversationAccount.setType("personal");
        
        activity.setConversation(conversationAccount);
        
        activity.setChannelId("msteams");
        activity.setText("Heeeeeeeeeeeeyyyyyyyyy!");
        
        activity.setRecipient(member);
        
        activity.setServiceUrl(url);
        
        //conversation.setActivity(activity);

        return conversation;

    }
    
}
