package hello;

import hello.util.Publisher;
import hello.util.ConversationUrlHandler;
import hello.jenkins.CommandService;
import hello.jenkins.DeploymentService;
import hello.pojo.Activity;
import hello.pojo.Attachment;
import hello.pojo.Button;
import hello.pojo.ChannelAccount;
import hello.pojo.Content;
import hello.pojo.Conversation;
import hello.pojo.ConversationAccount;
import hello.util.BotCore;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    private static final Logger LOGGER = Logger.getLogger(BotService.class.getName());

    @Autowired
    private DeploymentService deploymentService;

    @Autowired
    private CommandService commandService;

    @Autowired
    private BotCore botCore;

    @Autowired
    private Publisher publisher;

    /**
     * ReplyTo user
     *
     * @param input
     */
    public void reply(Activity input) {

        String error = null;

        Activity output = getBotCore().prepareOutput(input);

        String command = input.getText();
        LOGGER.log(Level.INFO, "command-> {0}", command);

        error = getCommandService().validate(command);

        if (error != null) {
            // cannot run
            output.setText("Opsss... something went wrong (" + error + ")");
            
            Attachment attachment = new Attachment();
            attachment.setContentType("application/vnd.microsoft.card.hero");

            Content content = new Content();
            content.setTitle("Done!");
            content.setText("Text...");
            
            Button button = new Button();
            button.setType("imBack");
            button.setTitle("title");
            button.setValue("value");

            Button button2 = new Button();
            button2.setType("imBack");
            button2.setTitle("title2");
            button2.setValue("value2");
            
            content.getButtons().add(button);
            content.getButtons().add(button2);

            List<Attachment> list = new ArrayList<>();
            
            attachment.setContent(content);
            
            list.add(attachment);
            output.setAttachments(list);
            

        } else {

            String[] parts = command.split(" ");

            getDeploymentService().deployBot(parts[0], parts[1], parts[2]);

            output.setText("Done!");
        }

        LOGGER.info(output.toString());

        // send Reply
        getPublisher().send(ConversationUrlHandler.getReplyUrl(input), output);

        if (error != null) {
            // error: send additional message
            Activity secondMessage = getBotCore().prepareOutput(input);
            secondMessage.setText("Try something like---> scacc.sapienzaconsulting.com svc staging");

            getPublisher().send(ConversationUrlHandler.getReplyUrl(input), secondMessage, 1000);

        } else {
            // success: check status
            String status = getDeploymentService().getStatus();

            Activity checkStatus = getBotCore().prepareOutput(input);
            checkStatus.setText("Status of the job is " + status);

            getPublisher().send(ConversationUrlHandler.getReplyUrl(checkStatus), output, 5000);

        }

    }

    public void createConversation() {
        String url = "https://smba.trafficmanager.net/emea-client-ss.msg/";
        //String url = "https://smba.trafficmanager.net/apis";
        
        url = ConversationUrlHandler.createConversationUrl(url);

        Conversation conversation = getBotCore().createConversation(url);
        
        LOGGER.info("conversation---> " + conversation);

        getPublisher().send(url, conversation, 1000);
        
//        Activity activity = new Activity();
//        
//        ConversationAccount conversationAccount = new ConversationAccount();
//        conversationAccount.setType("personal");
//        conversationAccount.setId("8:beppe-catanese");
//        
//        activity.setConversation(conversationAccount);
//        
//        ChannelAccount bot = new ChannelAccount();
//        bot.setId("99");
//        bot.setName("BOT");
//        conversation.setBot(bot);
//        
//        activity.setFrom(bot);
//        
//        //activity.setChannelId ("msteams");
//        activity.setText("Heeeeeeeeeeeeyyyyyyyyy!");
//        
//        ChannelAccount member = new ChannelAccount();
//        member.setId("29:18Pp6Dllk24UGXU_9T4DUUKTN_F1emNWv8mWO1w39Izc");
//        member.setName("Beppe Catanese");
//        
//        activity.setRecipient(member);
//        
//        activity.setServiceUrl(url);      
//        
//        LOGGER.info("activity---> " + activity);
//        
//        url = ConversationUrlHandler.getReplyUrl(activity);
//        
//        getPublisher().send(url, activity, 5000);

    }

    public DeploymentService getDeploymentService() {
        return deploymentService;
    }

    public void setDeploymentService(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    public CommandService getCommandService() {
        return commandService;
    }

    public void setCommandService(CommandService commandService) {
        this.commandService = commandService;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BotCore getBotCore() {
        return botCore;
    }

    public void setBotCore(BotCore botCore) {
        this.botCore = botCore;
    }

}
