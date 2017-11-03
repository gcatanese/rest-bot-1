package hello;

import hello.pojo.security.JWK;
import hello.pojo.security.Keys;
import hello.pojo.security.OpenIDMetadataDocument;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
public class SecurityAgent {

    private static final Logger LOGGER = Logger.getLogger(SecurityAgent.class.getName());

    public void auth(String jwt) {
        
        Thread t = new Thread(new Runnable() {
            public void run() {
                String s = getKeys(getOpenIDMetadataDocument());
            }
        });
        t.start();
        
        
    }
    
    private String getOpenIDMetadataDocument() {

        String endPoint = "https://login.botframework.com/v1/.well-known/openidconfiguration";
        String s = null;

        LOGGER.log(Level.INFO, "getOpenIDMetadataDocument urlEndPoint:{0}", endPoint);

        RestTemplate restTemplate = new RestTemplate();

        OpenIDMetadataDocument result = restTemplate.getForObject(endPoint, OpenIDMetadataDocument.class);

        LOGGER.log(Level.INFO, "getOpenIDMetadataDocument response id:{0}", result);
        
        s = result.getJwks_uri();
        
        return s;

    }
    
    private String getKeys(String jwks_uri) {
        String s = null;
        
        LOGGER.log(Level.INFO, "getKeys jwks_uri:{0}", jwks_uri);

        RestTemplate restTemplate = new RestTemplate();

        String resultAsString = restTemplate.getForObject(jwks_uri, String.class);
        LOGGER.log(Level.INFO, "getKeys resultAsString id:{0}", resultAsString);
        
        Keys result = restTemplate.getForObject(jwks_uri, Keys.class);

        LOGGER.log(Level.INFO, "getKeys response id:{0}", result);
        
        return s;
    }
    
    private void printListEndorsedChannels(Keys keys) {
        
        for(JWK jwk : keys.getKeys()) {
            LOGGER.log(Level.INFO, "endosedChannels id:{0}", jwk.getEndorsements());
        }
        
        
    }

}
