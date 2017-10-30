package hello.pojo;

public class Activity {

    private String summary;

    private String locale;

    private SuggestedActions suggestedActions;

    private String attachmentLayout;

    private From from;

    private String type;

    private Recipient recipient;

    private String timestamp;

    private String id;

    private String[] membersAdded;

    private String localTimestamp;

    private String textFormat;

    private String name;

    private String value;

    private String action;

    private Conversation conversation;

    private Attachments[] attachments;

    private String historyDisclosed;

    private ChannelData channelData;

    private String topicName;

    private String replyToId;

    private String text;

    private String channelId;

    private String relatesTo;

    private String speak;

    private String code;

    private String serviceUrl;

    private Entities[] entities;

    private String inputHint;

    private String[] membersRemoved;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public SuggestedActions getSuggestedActions() {
        return suggestedActions;
    }

    public void setSuggestedActions(SuggestedActions suggestedActions) {
        this.suggestedActions = suggestedActions;
    }

    public String getAttachmentLayout() {
        return attachmentLayout;
    }

    public void setAttachmentLayout(String attachmentLayout) {
        this.attachmentLayout = attachmentLayout;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getMembersAdded() {
        return membersAdded;
    }

    public void setMembersAdded(String[] membersAdded) {
        this.membersAdded = membersAdded;
    }

    public String getLocalTimestamp() {
        return localTimestamp;
    }

    public void setLocalTimestamp(String localTimestamp) {
        this.localTimestamp = localTimestamp;
    }

    public String getTextFormat() {
        return textFormat;
    }

    public void setTextFormat(String textFormat) {
        this.textFormat = textFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Attachments[] getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments[] attachments) {
        this.attachments = attachments;
    }

    public String getHistoryDisclosed() {
        return historyDisclosed;
    }

    public void setHistoryDisclosed(String historyDisclosed) {
        this.historyDisclosed = historyDisclosed;
    }

    public ChannelData getChannelData() {
        return channelData;
    }

    public void setChannelData(ChannelData channelData) {
        this.channelData = channelData;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRelatesTo() {
        return relatesTo;
    }

    public void setRelatesTo(String relatesTo) {
        this.relatesTo = relatesTo;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Entities[] getEntities() {
        return entities;
    }

    public void setEntities(Entities[] entities) {
        this.entities = entities;
    }

    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public String[] getMembersRemoved() {
        return membersRemoved;
    }

    public void setMembersRemoved(String[] membersRemoved) {
        this.membersRemoved = membersRemoved;
    }

    @Override
    public String toString() {
        return "id:" + id
                + ", text:" + text
                + ", channelId:" + channelId
                + ", conversation.id:" + (conversation == null ? "null" : conversation.getId())
                + ", serviceUrl:" + serviceUrl;
    }

}
