package edu.nyu.nsg2057.webscraper.service.smtp;

import edu.nyu.nsg2057.webscraper.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
}
