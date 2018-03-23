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

//        SuggestedActions suggestedActions = new SuggestedActions();
//        suggestedActions.setActions(actions);
//
//        List<Attachment> list = new ArrayList<>();
//
//        Attachment attachment = new Attachment();
//        attachment.setContentType("application/vnd.microsoft.card.hero");
//
//        Content content = new Content();
//        content.setTitle("Done!");
//        content.setText("Text...");
//
//        for (Actions a : actions) {
//            Button button = new Button();
//            button.setType("imBack");
//            button.setTitle(a.getTitle());
//            button.setValue(a.getValue());
//
//            content.getButtons().add(button);
//        }
//
//        attachment.setContent(content);
//
//        list.add(attachment);
//
//        activity.setAttachments(list);

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
