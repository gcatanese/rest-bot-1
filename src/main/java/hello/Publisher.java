package hello;

import hello.comm.dto.Output;
import hello.pojo.Activity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
public class Publisher {

    private static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());

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

        Activity result = restTemplate.postForObject(output.getUrlEndPoint(), output.getActivity(), Activity.class);

        LOGGER.log(Level.FINE, "Publisher response id:{0}", result.getId());

    }

}
