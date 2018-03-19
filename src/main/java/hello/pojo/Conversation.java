//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.pojo;

/**
 *
 * @author gcatanese
 */
public class Conversation {
    
    private Activity activity;
    private String topicName;
    private ChannelAccount bot;
    private ChannelAccount[] members;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ChannelAccount getBot() {
        return bot;
    }

    public void setBot(ChannelAccount bot) {
        this.bot = bot;
    }

    public ChannelAccount[] getMembers() {
        return members;
    }

    public void setMembers(ChannelAccount[] members) {
        this.members = members;
    }
    
    
}
