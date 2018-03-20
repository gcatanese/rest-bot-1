package hello;

import hello.comm.dto.Input;
import hello.comm.dto.Output;
import hello.pojo.Activity;
import hello.pojo.ChannelAccount;
import hello.pojo.Conversation;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class BotCore {

    private static final Logger LOGGER = Logger.getLogger(BotCore.class.getName());

    public static String serviceUrl;

    /**
     * Process the Activity received from the user
     *
     * @param activity
     * @return
     */
    public Output reply(Activity activity) {

        Input input = processInput(activity);

        Output output = prepareOutput(input);
        
        // customise reply
        if (input.isTextMessage()) {
            // dealing with Text message

            output.setText("Yo!");

            output.addButton("Blue", "Blue");
            output.addButton("Red", "Red");

        } else {
            LOGGER.warning("Unknown message type..");
        }

        processOutput(output);

        LOGGER.info(output.toString());

        return output;

    }

    // create Input object (from Activity)
    private Input processInput(Activity activity) {
        return new Input(activity);

    }

    // create Output object (from Input)
    private Output prepareOutput(Input input) {
        Output output = new Output();

        Activity activity = new Activity();
        activity.setConversation(input.getActivity().getConversation());

        ChannelAccount from = new ChannelAccount();
//        from.setId(input.getActivity().getRecipient().getId());
//        from.setName(input.getActivity().getRecipient().getName());
        from.setId("99");
        from.setName("FantaAntonio");
        activity.setFrom(from);

        ChannelAccount recipient = new ChannelAccount();
        recipient.setId(input.getActivity().getFrom().getId());
        recipient.setName(input.getActivity().getFrom().getName());
        activity.setRecipient(recipient);

        activity.setReplyToId(input.getActivity().getId());

        activity.setServiceUrl(input.getActivity().getServiceUrl());

        activity.setType("message");

        output.setActivity(activity);

        return output;

    }

    // create Output object (from Input)
    private void processOutput(Output output) {
        output.build();

    }

    public void createConversation() {
        String url = this.getServiceUrl() + "/v3/conversations";

        Conversation conversation = new Conversation();

        conversation.setTopicName("New chat");

        ChannelAccount bot = new ChannelAccount();
        bot.setId("msteams");
        conversation.setBot(bot);

        ChannelAccount member = new ChannelAccount();
        member.setId("29:1_lbAh47KNZHntCEuYBnV6iy1Csvef1OJqZEuuMOU-cxHWuZ_2drhFKLq1HqVumWiibH7Kwz8Fg0GOGho9UvhmA");
        member.setName("Giuseppe Catanese");

        ChannelAccount[] members = {member};
        conversation.setMembers(members);

        Activity activity = new Activity();
        activity.setText("Hello");
        conversation.setActivity(activity);

        Output output = new Output();
        output.setActivity(activity);

    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(Activity activity) {
        String baseUrl = activity.getServiceUrl();

        if (baseUrl.endsWith("/")) {
            // remove trailing slash
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        BotCore.serviceUrl = baseUrl;
    }

}
