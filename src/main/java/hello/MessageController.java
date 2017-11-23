package hello;

import hello.pojo.Activity;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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

    @Autowired
    private SecurityAgent securityAgent;
    
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
        LOGGER.info("headers: " + getHeadersInfo(request));

        getBotCore().process(activity);
        
        String jwt = this.getJWT(request);
        LOGGER.info("jwt: " + jwt);
        
        getSecurityAgent().auth(jwt);

        return getAck();

    }

    private String getAck() {
        String id = "999";
        
        LOGGER.info("Sending 999");

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

    public SecurityAgent getSecurityAgent() {
        return securityAgent;
    }

    public void setSecurityAgent(SecurityAgent securityAgent) {
        this.securityAgent = securityAgent;
    }
    
    
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
    
    private String getJWT(HttpServletRequest request) {
        String jwt = null;
        
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            
            if(key.equalsIgnoreCase("authorization")) {
                jwt = request.getHeader(key);
                break;
            }
            
        }
        
        return jwt;
    }
    

}
