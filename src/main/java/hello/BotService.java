package hello;

import hello.util.Publisher;
import hello.util.ConversationUrlHandler;
import hello.jenkins.CommandService;
import hello.jenkins.DeploymentService;
import hello.pojo.Activity;
import hello.pojo.Conversation;
import hello.util.BotCore;
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
     * @return
     */
    public void reply(Activity input) {

        String error = null;

        Activity output = getBotCore().prepareOutput(input);

        String command = input.getText();
        LOGGER.info("command-> " + command);

        error = getCommandService().validate(command);

        if (error != null) {
            // cannot run
            output.setText("Opsss... something went wrong (" + error + ")");

        } else {

            String[] parts = command.split(" ");

            getDeploymentService().deployBot(parts[0], parts[1], parts[2]);

            output.setText("Done!");
        }

        LOGGER.info(output.toString());

        // send Reply
        getPublisher().send(ConversationUrlHandler.getReplyUrl(input), output);

        if (error != null) {
            // send additional message (if an error has occurred)
            Activity secondMessage = getBotCore().prepareOutput(input);
            secondMessage.setText("Try something like---> scacc.sapienzaconsulting.com svc staging");

            getPublisher().send(ConversationUrlHandler.getReplyUrl(input), secondMessage);

        } else {
            // check status

            try {
                LOGGER.warning("sleep 5 sec");
                Thread.sleep(5000);

                String status = getDeploymentService().getStatus();

                send(input, status);

            } catch (InterruptedException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void send(Activity activity, String status) {

        LOGGER.warning("send");

        Activity output = getBotCore().prepareOutput(activity);

        output.setText("Status of the job is " + status);

        LOGGER.info(output.toString());

        getPublisher().send(ConversationUrlHandler.createConversationUrl(activity), output, 1000);

    }

    public void createConversation() {
        String url = "https://smba.trafficmanager.net/apis/v3/conversations";

        Conversation conversation = getBotCore().createConversation();

        getPublisher().send(url, conversation.getActivity(), 5000);

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
