package edu.nyu.nsg2057.webscraper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;

    public EmailDetails(String recipient, String msgBody, String subject) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
    }
}