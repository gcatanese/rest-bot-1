//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.QueueReference;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author gcatanese
 */
@Service
public class DeploymentService {

    private static final Logger LOGGER = Logger.getLogger(DeploymentService.class.getName());

    final String JENKINS_URL = "https://svnnl.sapienzaconsulting.com/jenkins";
    final String JOB_NAME = "DEPLOY_BOT";
    final String USERNAME = "jenkins";
    final String PASSWORD = "hell0";

    public void deployBot(String server, String job, String environment) {
        LOGGER.info("deployBot server: " + server);

        setDefaults(server, job, environment);

        try {
            JenkinsServer jenkins = new JenkinsServer(new URI(JENKINS_URL), USERNAME, PASSWORD);

            String jsonParameter = "{\"parameter\": [{\"name\":\"SERVER\", \"value\":\"" + server + "\"}, {\"name\":\"JOB\", \"value\":\"" + job + "\"}, {\"name\":\"ENVIRONMENT\", \"value\":\"" + environment + "\"}]}";
            Map<String, String> params = new HashMap<>();
            params.put("json", jsonParameter);

            QueueReference queueReference = jenkins.getJob(JOB_NAME).build(params);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public String getStatus() {
        LOGGER.info("getStatus");

        String result = null;

        try {
            JenkinsServer jenkins = new JenkinsServer(new URI(JENKINS_URL), USERNAME, PASSWORD);

            Build lastBuild = jenkins.getJob(JOB_NAME).getLastBuild();

            result = lastBuild.details().getResult().name();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        LOGGER.info("getStatus result: " + result);

        return result;

    }

    private void setDefaults(String server, String job, String environment) {
        if (server == null) {
            server = "scacc.sapienzaconsulting.com";
        }
        if (job == null) {
            job = "svc";
        }
        if (environment == null) {
            environment = "staging";
        }
    }

}
