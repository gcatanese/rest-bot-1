
package hello;

import hello.pojo.Activity;

/**
 *
 * @author gcatanese
 */
public interface AbstractBot {
    
    void sendMessage(Activity input, String value);
    
    void sendButtons(Activity input, String[] values);
    
}
