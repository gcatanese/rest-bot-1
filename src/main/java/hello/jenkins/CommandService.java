//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.jenkins;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author gcatanese
 */
@Service
public class CommandService {
    
    private static final Logger LOGGER = Logger.getLogger(CommandService.class.getName());
    
    private static final Set<String> SERVERS = new HashSet();
    
    public String validate(String command) {
        String result = null;
        
        if(command == null) {
            return "No parameters are defined";
        }
        
        command = command.trim();
        String[] parts = command.split(" ");
        
        if(parts == null) {
            return "No parameters are defined"; 
        }
        
        if(parts.length != 3) {
            return "Invalid number of parameters"; 
        }
        
        if(!getServers().contains(parts[0])){
            return "Server param is not one of " + getServers(); 
        }
        
        if(!getApps().contains(parts[1])){
            return "App param is not one of " + getApps(); 
        }
        
        if(!getEnvironments().contains(parts[2])){
            return "Environment param is not one of " + getServers(); 
        }
        
        return result;
    }
    
    private Set getServers() {
        Set<String> servers = new HashSet();
        
        servers.add("scacc.sapienzaconsulting.com");
        
        return servers;
    }

    private Set getApps() {
        Set<String> servers = new HashSet();
        
        servers.add("svc");
        servers.add("fh");
        
        return servers;
    }

    private Set getEnvironments() {
        Set<String> servers = new HashSet();
        
        servers.add("staging");
        servers.add("prod");
        
        return servers;
    }
    
}
