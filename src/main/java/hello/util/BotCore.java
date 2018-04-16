//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.util;

import hello.pojo.Activity;
import hello.pojo.ChannelAccount;
import hello.pojo.ChannelData;
import hello.pojo.Conversation;
import hello.pojo.ConversationAccount;
import hello.pojo.Tenant;
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

        final String SKYPE_USER_ID = "29:18Pp6Dllk24UGXU_9T4DUUKTN_F1emNWv8mWO1w39Izc";
        final String SKYPE_USER_NAME = "Beppe Catanese";

        final String MSTEAMS_USER_ID = "29:1_lbAh47KNZHntCEuYBnV6iy1Csvef1OJqZEuuMOU-cxHWuZ_2drhFKLq1HqVumWiibH7Kwz8Fg0GOGho9UvhmA";
        final String MSTEAMS_USER_NAME = "Giuseppe Catanese";

        String userId = MSTEAMS_USER_ID;
        String username = MSTEAMS_USER_NAME;

        Conversation conversation = new Conversation();

        conversation.setTopicName("New chat");
        conversation.setIsGroup(false);

        ChannelAccount bot = new ChannelAccount();
        bot.setId("99");
        bot.setName("BOT");
        conversation.setBot(bot);

        ChannelAccount member = new ChannelAccount();
        member.setId(userId);
        member.setName(username);

        ChannelAccount[] members = {member};
        conversation.setMembers(members);

        Activity activity = new Activity();

        activity.setType("message");

        ConversationAccount conversationAccount = new ConversationAccount();
        conversationAccount.setType("personal");

        activity.setConversation(conversationAccount);

        activity.setFrom(bot);

        //activity.setChannelId("msteams");
        activity.setText("Heeeeeeeeeeeeyyyyyyyyy!");

        activity.setRecipient(member);

        activity.setServiceUrl(url);

        ChannelData channelData = new ChannelData();
        Tenant tenant = new Tenant();
        tenant.setId("78680bdb-24e1-4334-b94b-ab9e88f0c898");

        channelData.setTenant(tenant);

        activity.setChannelData(channelData);

        conversation.setActivity(activity);

        return conversation;

    }

}
