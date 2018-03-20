package hello.comm.dto;

import hello.pojo.Actions;
import hello.pojo.Activity;
import hello.pojo.Attachment;
import hello.pojo.Button;
import hello.pojo.Content;
import hello.pojo.SuggestedActions;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents outgoing Output to the USER
 */
public class Output {

    private Activity activity = null;

    private String text;
    private List<Actions> actions = new ArrayList<>();

    private String jwt;

    public Output() {

    }

    // finalises Output object before sending it
    public void build() {
        
        if (getText() != null) {
            activity.setText(getText());
        }

        SuggestedActions suggestedActions = new SuggestedActions();
        suggestedActions.setActions(actions);

        List<Attachment> list = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setContentType("application/vnd.microsoft.card.hero");

        Content content = new Content();
        content.setTitle("title");
        content.setText("text");

        Button b1 = new Button();
        b1.setType("imBack");
        b1.setTitle("Button 1");
        b1.setValue("Val1");

        Button b2 = new Button();
        b2.setType("imBack");
        b2.setTitle("Button 2");
        b2.setValue("Val2");

        content.getButtons().add(b1);
        content.getButtons().add(b2);

        attachment.setContent(content);

        list.add(attachment);

        activity.setAttachments(list);

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setText(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void addButton(String title, String value) {

        Actions action = new Actions();
        action.setTitle(title);
        action.setValue(value);
        action.setType("imBack");

        this.actions.add(action);

    }

    @Override
    public String toString() {
        return "Output["
                + ", activity:" + getActivity()
                + "]";
    }

}
