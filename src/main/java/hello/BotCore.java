package hello;

import hello.comm.dto.Input;
import hello.comm.dto.Message;
import hello.comm.dto.Output;
import hello.pojo.Activity;
import hello.pojo.ChannelAccount;
import hello.pojo.Conversation;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotCore {

    private static final Logger LOGGER = Logger.getLogger(BotCore.class.getName());

    @Autowired
    private Publisher publisher;
    
    public static String serviceUrl;
    
    /**
     * Process the Activity received from the user
     *
     * @param activity
     */
    public void process(Activity activity) {

        Input input = processInput(activity);
        
        Output output = prepareOutput(input);
        
        

        if (input.isTextMessage()) {
            // dealing with Text message
            
            output.setText("Yo!");
            
            output.addButton("Blue", "Blue");
            output.addButton("Red", "Red");
            
            
        } else {
            LOGGER.warning("Unknown message type");
        }
        
        if(input.getMessage().equalsIgnoreCase("new")) {
            LOGGER.info("create new conv");
            this.createConversation();
        }
        
        
        output.setText("Yo!");
        
        processOutput(output);
        
        LOGGER.info(output.toString());
        
        send(output);

    }

    // create Input object (from Activity)
    private Input processInput(Activity activity) {
        return new Input(activity);

    }
    
    // create Output object (from Input)
    private Output prepareOutput(Input input) {
        return new Message().getMessage(input);

    }
    
    // create Output object (from Input)
    private void processOutput(Output output) {
        output.build();

    }

    private void send(Output output) {
        getPublisher().send(output);
    }
    
    public void createConversation() {
        String url = this.getServiceUrl() + "/v3/conversations";
        
        Conversation conversation = new  Conversation();
        
        conversation.setTopicName("New chat");
        
        ChannelAccount bot = new ChannelAccount();
        bot.setId("msteams");
        conversation.setBot(bot);
        
        ChannelAccount member = new ChannelAccount();
        member.setId("29");
        
        ChannelAccount[] members = {member};
        conversation.setMembers(members);
        
        Activity activity = new Activity();
        activity.setText("Hello");
        conversation.setActivity(activity);
        
        Output output = new Output();
        output.setUrlEndPoint(url);
        output.setActivity(activity);
        
        getPublisher().send(output);
        
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
