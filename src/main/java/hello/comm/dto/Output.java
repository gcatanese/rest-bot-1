package hello.comm.dto;

import hello.pojo.Actions;
import hello.pojo.Activity;
import hello.pojo.SuggestedActions;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents outgoing Output to the USER
 */
public class Output {

    private Activity activity = null;

    private String urlEndPoint;
    private String text;
    private List<Actions> actions = new ArrayList<>();

    private String jwt;

    public Output() {

    }

    // finalises Output object before sending it
    public void build() {
        activity.setText(getText());

        SuggestedActions suggestedActions = new SuggestedActions();
        suggestedActions.setActions(actions);

        activity.setSuggestedActions(suggestedActions);

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getUrlEndPoint() {
        return urlEndPoint;
    }

    public void setUrlEndPoint(String urlEndPoint) {
        this.urlEndPoint = urlEndPoint;
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
                + "getUrlEndPoint:" + getUrlEndPoint()
                + ", activity:" + getActivity()
                + "]";
    }

}
