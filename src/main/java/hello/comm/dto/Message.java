package hello.comm.dto;

import hello.ConversationUrlHandler;
import hello.pojo.Activity;
import hello.pojo.ChannelAccount;

/**
 *
 */
public class Message {

    public Output getMessage(Input input) {
        
        Output output = new Output();
        output.setUrlEndPoint(ConversationUrlHandler.getReplyUrl(input.getActivity()));
        
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
}
