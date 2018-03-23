//Sapienza Consulting Copyright 2017
//www.sapienzaconsulting.com
package hello.jenkins;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author gcatanese
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandServiceTest {
    
    private CommandService cmd = new CommandService();
    
    @Test
    public void deploy() throws Exception {
        
        String COMMAND = "scacc.sapienzaconsulting.com svc staging";
        
        assertNull(cmd.validate(COMMAND));
    }

    @Test
    public void deployWrongServer() throws Exception {
        
        String COMMAND = "xxx svc staging";
        
        assertEquals("Server param is not one of [scacc.sapienzaconsulting.com]", cmd.validate(COMMAND));
    }
    
}
