package hello;

import java.util.Random;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    @RequestMapping("/message/v3/conversations/{conversationId}/activities/{activityId}")
    public Payload message(HttpServletRequest request,
            @PathVariable(value = "conversationId") final String conversationId,
            @PathVariable(value = "activityId") final String activityId) {
        
        LOGGER.setLevel(Level.INFO);

        LOGGER.info("url: " + request.getRequestURI());
        LOGGER.info("conversationId: " + conversationId);
        LOGGER.info("activityId: " + activityId);

        Payload payload = new Payload();

        payload.setMessages(getJoke1());

        return payload;
    }

    private Messages[] getJoke1() {
        Messages[] messages = new Messages[1];

        messages[0] = new Messages(getRandomElement());

        return messages;
    }

    private String getRandomElement() {
        int randomInt = new Random().nextInt(arrayStrings.length);

        return arrayStrings[randomInt];
    }

    private String[] arrayStrings = {
        "Farts have been clocked at a speed of 10 feet per second.",
        "Donkeys kill more people annually than plane crashes.",
        "The longest recorded flight of a chicken is thirteen seconds.",
        "40,000 Americans are injured by toilets each year.",
        "Banging your head against a wall uses 150 calories a hour.",
        "Vending Machines Kill More People per year than sharks.",
        "There are three golf balls sitting on the moon!",
        "Like fingerprints, everyone's tongue print is different.",
        "Butterflies taste with their feet.",
        "In France, it is legal to marry a dead person.",
        "To produce a single pound of honey, a single bee would have to visit 2 million flowers.",
        "You are born with 300 bones, by the time you are an adult you will have 206.",
        "A cockroach will live for weeks without its head before it starves to death",
        "A pig's orgasm lasts 30 minutes.",
        "No word in the English language rhymes with month, orange, silver or purple.",
        "In Tokyo, a bicycle is faster than a car for most trips of less than 50 minutes!",
        "An ostrich’s eye is bigger than its brain",
        "Every year, kids in North America spend close to half a billion dollars on chewing gum!",
        "Ketchup was sold in the 1830s as medicine."};

}
