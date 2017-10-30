package hello;

import hello.pojo.Activity;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    @Autowired
    private BotCore botCore;

    @RequestMapping(value = "/test", method = GET)
    public String test(HttpServletRequest request) {
        return "test";
    }

    @RequestMapping(value = "/bot", method = POST)
    @ResponseBody
    public String handler(HttpServletRequest request, @RequestBody Activity activity) {

        LOGGER.setLevel(Level.INFO);

        LOGGER.info("url: " + request.getRequestURI());
        LOGGER.info("body: " + activity);

        getBotCore().process(activity);

        return getAck();

    }

    private String getAck() {
        String id = "999";

        // id:XXX as JSON
        return "{\n\"id\":\""
                + id
                + "\"\n}";
    }

    public BotCore getBotCore() {
        return botCore;
    }

    public void setBotCore(BotCore botCore) {
        this.botCore = botCore;
    }

}
