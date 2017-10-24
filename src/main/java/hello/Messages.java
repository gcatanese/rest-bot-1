package hello;

public class Messages {

    private String text;
    
    public Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ClassPojo [text = " + text + "]";
    }
}
