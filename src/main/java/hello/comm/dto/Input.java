package hello.comm.dto;

import hello.pojo.Activity;

/**
 * Represents incoming Input to the BOT
 */
public class Input {

    private Activity activity = null;

    public Input(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean isTextMessage() {
        return getMessage() != null;
    }

    public String getMessage() {
        return activity.getText();
    }

    public String getUserName() {
        String userName = null;
        
        if(activity.getFrom() != null) {
            userName = activity.getFrom().getName();
        }
        
        return userName;
    }

    public String getUserId() {
        String userId = null;
        
        if(activity.getFrom() != null) {
            userId = activity.getFrom().getId();
        }
        
        return userId;
    }
    
    public String getConversationId() {
        String conversationId = null;
        
        if(activity.getConversation() != null) {
            conversationId = activity.getConversation().getId();
        }
        
        return conversationId;
        
    }

    @Override
    public String toString() {
        return "Input["
                + "getMessage:" + getMessage()
                + ", getUserName:" + getUserName()
                + ", getUserId:" + getUserId()
                + ", getConversationId:" + getConversationId()
                + ", activity.recipient:" + activity.getRecipient()
                + "]";
    }
  
}
