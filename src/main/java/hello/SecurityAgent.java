package hello;

import hello.pojo.security.JWK;
import hello.pojo.security.JWTWrapper;
import hello.pojo.security.Keys;
import hello.pojo.security.OpenIDMetadataDocument;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
public class SecurityAgent {

    private static final Logger LOGGER = Logger.getLogger(SecurityAgent.class.getName());

    public String getJwt() {
        
        String jwt = null;

        Thread t = new Thread(new Runnable() {
            public void run() {
                String token = getToken();
            }
        });
        t.start();
        
        return jwt;

    }
    
    public void auth(String jwt) {

        Thread t = new Thread(new Runnable() {
            public void run() {
                String s = getKeys(getOpenIDMetadataDocument());

                String token = getToken();
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
        //LOGGER.log(Level.INFO, "getKeys resultAsString id:{0}", resultAsString);

        Keys result = restTemplate.getForObject(jwks_uri, Keys.class);

        

        return s;
    }

    private void printListEndorsedChannels(Keys keys) {

        for (JWK jwk : keys.getKeys()) {
            LOGGER.log(Level.INFO, "endosedChannels id:{0}", jwk.getEndorsements());
        }

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
        //LOGGER.log(Level.INFO, "getToken response id:{0}", result);
        
        return result.getAccess_token();

    }

}
