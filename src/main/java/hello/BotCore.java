package hello;

import hello.comm.dto.Input;
import hello.comm.dto.Message;
import hello.comm.dto.Output;
import hello.pojo.Activity;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotCore {

    private static final Logger LOGGER = Logger.getLogger(BotCore.class.getName());

    @Autowired
    private Publisher publisher;
    
    /**
     * Process the Activity received from the user
     *
     * @param activity
     */
    public void process(Activity activity) {

        Input input = processInput(activity);
        
        LOGGER.info(input.toString());
        
        Output output = prepareOutput(input);

        if (input.isTextMessage()) {
            // dealing with Text message
            
            output.setText("Yo!");
            
            output.addButton("Blue", "Blue");
            output.addButton("Red", "Red");
            
            
        } else {
            LOGGER.warning("Unknown message type");
        }
        
        
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    
}
