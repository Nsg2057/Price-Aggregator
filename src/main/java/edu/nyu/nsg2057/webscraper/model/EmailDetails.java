package edu.nyu.nsg2057.webscraper.model;

import java.util.Objects;

public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;

    public EmailDetails(String recipient, String msgBody, String subject) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailDetails)) return false;
        EmailDetails that = (EmailDetails) o;
        return Objects.equals(getRecipient(), that.getRecipient()) && Objects.equals(getMsgBody(), that.getMsgBody()) && Objects.equals(getSubject(), that.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipient(), getMsgBody(), getSubject());
    }

    @Override
    public String toString() {
        return "EmailDetails{" +
                "recipient='" + recipient + '\'' +
                ", msgBody='" + msgBody + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}