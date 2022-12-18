package edu.nyu.nsg2057.webscraper.service.smtp;


import edu.nyu.nsg2057.webscraper.model.EmailDetails;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${spring.mail.secondary.username}")
    private String secSender;
    @Value("${spring.mail.primary.username}")
    private String priSender;
    private JavaMailSender primarySender;
    private JavaMailSender secondarySender;

    public EmailService (
            @Qualifier("primarySender") JavaMailSender primarySender,
            @Qualifier("secondarySender") JavaMailSender secondarySender) {
        this.primarySender = primarySender;
        this.secondarySender = secondarySender;
    }

    public boolean sendPrimaryEmail(EmailDetails ed){

        try {
            primarySender.send(getSimpleMailMessage(ed,priSender));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sendSecondaryEmail(ed);
            return false;
        }

    }


    public boolean sendSecondaryEmail(EmailDetails ed)
    {

        try {
            secondarySender.send(getSimpleMailMessage(ed,secSender));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private SimpleMailMessage getSimpleMailMessage(EmailDetails ed, String sender) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(ed.getRecipient());
        message.setSubject(ed.getSubject());
        message.setText(ed.getMsgBody());
        return message;
    }
}
