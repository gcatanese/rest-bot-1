package hello;

import hello.comm.dto.Output;
import hello.pojo.Activity;
import hello.pojo.security.JWTWrapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
public class Publisher {

    private static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());
    
    @Autowired
    private SecurityAgent securityAgent;

    // generate reply as POST to the given URL
    public void send(final Output output) {

        Thread t = new Thread(new Runnable() {
            public void run() {
                doRun(output);
            }
        });
        t.start();
    }

    private void doRun(Output output) {

        LOGGER.log(Level.INFO, "urlEndPoint:{0}", output.getUrlEndPoint());

        
        
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getToken());

        restTemplate = new RestTemplate();
        
        HttpEntity<Activity> entity = new HttpEntity<>(output.getActivity(), headers);

        Activity result = restTemplate.postForObject(output.getUrlEndPoint(), entity, Activity.class);

        LOGGER.log(Level.FINE, "Publisher response id:{0}", result.getId());

    }
    
    private String getToken() {
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token";
        String param = "grant_type=client_credentials&client_id="
                + "4ba19af6-850c-4fa1-8ae6-62e8edad2e38" //MICROSOFT-APP-ID"
                + "&client_secret="
                + "iczKWT$jvfsLKVP46279#_}" //MICROSOFT-APP-PASSWORD"
                + "&scope=https%3A%2F%2Fapi.botframework.com%2F.default";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Host", "login.microsoftonline.com");

        HttpEntity<String> entity = new HttpEntity<>(param, headers);

        JWTWrapper result = restTemplate.postForObject(url, entity, JWTWrapper.class);
        LOGGER.log(Level.INFO, "getAccess_token:{0}", result.getAccess_token());
        
        return result.getAccess_token();

    }

    public SecurityAgent getSecurityAgent() {
        return securityAgent;
    }

    public void setSecurityAgent(SecurityAgent securityAgent) {
        this.securityAgent = securityAgent;
    }
    
    

}
